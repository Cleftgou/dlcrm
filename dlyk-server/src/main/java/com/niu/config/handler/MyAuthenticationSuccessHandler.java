package com.niu.config.handler;

import com.niu.constant.Constants;
import com.niu.model.TUser;
import com.niu.result.R;
import com.niu.service.RedisService;
import com.niu.util.JSONUtils;
import com.niu.util.JWTUtils;
import com.niu.util.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //Authentication装的就是登录后的认证信息
        //登录成功，执行该方法，在该方法中返回json给前端，就行了
        TUser tUser = (TUser) authentication.getPrincipal();

        //用户转成json（这个用户包含用户表本身信息加上用户的角色信息（权限））
        String userJSON = JSONUtils.toJSON(tUser);

        //把用户作为负载数据用以生成jwt
        String jwt = JWTUtils.createJWT(userJSON);

        //写入redis（键值对的存储）
        redisService.setValue(Constants.REDIS_JWT_KEY + tUser.getId() , jwt);

        //设置jwt的过期时间（记住我为7天，否则30分钟过期）
        //拿到用户是否要记住我
        String rememberMe = request.getParameter("rememberMe");
        //勾选了就有值，就为true
        if (Boolean.parseBoolean(rememberMe)){
            redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.EXPIRE_TIME, TimeUnit.SECONDS);
        }else {
            redisService.expire(Constants.REDIS_JWT_KEY + tUser.getId(), Constants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        }

        //登录成功的统一结果，把jwt返回即可
        R result = R.OK(jwt);

        //把R对象转成json
        String resultJSON = JSONUtils.toJSON(result);

        //把R以json返回给前端的axios发出get请求的响应信息
        //这里之所以能拿到用户的权限是因为spring security在调用业务层接口时（loadUserByUsername）同时去查了权限（自己写了俩sql操作...，所以这里用户信息完整）
        //这个完整的用户就会生成一个完整的token，便于后续的权限控制（直接从token拿权限）
        //ResponseUtils.write(response, "用户身份：" + tUser.getRoleList());
        ResponseUtils.write(response, resultJSON);
    }
}
