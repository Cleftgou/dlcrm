package com.niu.config.filter;

import com.niu.constant.Constants;
import com.niu.model.TUser;
import com.niu.result.CodeEnum;
import com.niu.result.R;
import com.niu.service.RedisService;
import com.niu.util.JSONUtils;
import com.niu.util.JWTUtils;
import com.niu.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description token验证过滤器
 * 此外token的键名是唯一的，所以不必担心新旧token问题
 * @date 2024/4/24 10:39:05
 */
//OncePerRequestFilter表示每次的Request都会进行拦截，不管是资源的请求还是数据的请求
@Component
public class TokenVerifyFilter extends OncePerRequestFilter {

    //自定义的redis操作类（存放数据，设置token等等）
    @Resource
    private RedisService redisService;

    //框架提供的线程池bean对象，直接用
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getRequestURI().equals(Constants.LOGIN_URI)) {
            //登录请求就不需要验证了
            //进行下一个过滤器
            filterChain.doFilter(request, response);
        }else {
            //除了登录以外的其他操作
            //获取前端传过来的token
            String token = null;
            if (request.getRequestURI().equals(Constants.EXPORT_EXCEL_URI)) {
                //从请求路径的参数中获取token，导出excel
                token = request.getParameter("Authorization");
            } else {
                //其他请求都是从请求头中获取token
                token = request.getHeader("Authorization");
            }

            if (!StringUtils.hasText(token)) {
                //前端传过来的token为空，要返回表示失败的R对象（token为空）
                //统一的返回结果处理
                R result = R.FAIL(CodeEnum.TOKEN_IS_EMPTY);
                //把对象转成json
                String resultJSON = JSONUtils.toJSON(result);
                //把对象返回给前端界面
                ResponseUtils.write(response, resultJSON);
                return;
            }

            if (!JWTUtils.verifyJWT(token)) {
                //用户篡改了token，所以没通过
                R result = R.FAIL(CodeEnum.TOKEN_IS_ERROR);
                String resultJSON = JSONUtils.toJSON(result);
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //到这里说明有token，而且是动力云客官方给的，所以尝试拿里面的user对象
            TUser tUser = JWTUtils.parseUserFromJWT(token);
            //拿到这个user对象的token
            String redisToken = (String) redisService.getValue(Constants.REDIS_JWT_KEY + tUser.getId());

            if (!StringUtils.hasText(redisToken)) {
                //redis中拿到的token为空，要返回表示失败的R对象（token不存在（过期了））
                R result = R.FAIL(CodeEnum.TOKEN_IS_EXPIRED);
                //统一的操作
                String resultJSON = JSONUtils.toJSON(result);
                //返回给前端
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //到这里说明有redis中有token，但是与前端传过来的token不一样
            if (!redisToken.equals(token)) {
                //redis中的token与用户传过来的token不一样，用户可能恶意修改成了其他人的token
                R result = R.FAIL(CodeEnum.TOKEN_IS_NONE_MATCH);
                String resultJSON = JSONUtils.toJSON(result);
                ResponseUtils.write(response, resultJSON);
                return;
            }

            //到这里就是JWT（token）验证通过了，在spring security上下文环境中设置一下，表示这个人是登录过的，这以后不用拦截了
            //authenticationToken包含了用户的凭据信息（例如用户名和密码）以及可能与用户相关的其他信息（例如权限
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(tUser, tUser.getLoginAct(), tUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //刷新一下token（防止用户用着用着重新登陆）（开一个新线程）
            /*new Thread(() -> {
                //刷新token
                //设置jwt的过期时间（记住我为7天，否则30分钟过期）
                //拿到用户是否要记住我
                String rememberMe = request.getHeader("rememberMe");
                //勾选了就有值，就为true
                if (Boolean.parseBoolean(rememberMe)){
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
                }else {
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                }
            }).start();*/

            //更好的办法使用内置的线程池
            threadPoolTaskExecutor.execute(() -> {
                //刷新token
                //设置jwt的过期时间（记住我为7天，否则30分钟过期）
                //拿到用户是否要记住我
                String rememberMe = request.getHeader("rememberMe");
                //勾选了就有值，就为true
                if (Boolean.parseBoolean(rememberMe)){
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
                }else {
                    redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
                }
            });

            //验证通过了，执行接下来的filter
            filterChain.doFilter(request, response);
        }
    }
}
