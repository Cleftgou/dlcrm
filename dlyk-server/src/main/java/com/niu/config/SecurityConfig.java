package com.niu.config;

import com.niu.config.filter.TokenVerifyFilter;
import com.niu.config.handler.MyAccessDeniedHandler;
import com.niu.config.handler.MyAuthenticationFailureHandler;
import com.niu.config.handler.MyAuthenticationSuccessHandler;
import com.niu.config.handler.MyLogoutSuccessHandler;
import com.niu.constant.Constants;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description Spring Security的执行流程
 * 在 Spring Security 中，当用户提交用户名和密码进行登录时，UsernamePasswordAuthenticationToken 被创建并传递给 AuthenticationManager。
 * AuthenticationManager 接口的实现类(ProviderManager)会负责对 UsernamePasswordAuthenticationToken 进行身份认证。
 *
 * 在你提供的代码中，authenticate(authRequest) 方法会触发 Spring Security 的认证流程。在认证过程中，
 * AuthenticationManager 会使用配置好的 UserDetailsService 来获取用户信息(这个接口唯一的方法loadUserByUsername)，然后进行密码验证（密码比对交给框架）。
 *
 * 在你的代码中，UserDetailsService 的实现类（UserService继承UserDetailsService）是 UserServiceImpl，
 * 其中的 loadUserByUsername 方法用于根据用户名从数据库中加载用户信息。在认证过程中，Spring Security 会调用 loadUserByUsername 方法，
 * 并将用户提交的用户名作为参数传递进去。loadUserByUsername 方法负责根据用户名从数据库中查询用户信息，并返回一个 UserDetails 对象。
 *
 * 在你的示例中，TUserMapper 的 selectByLoginAct 方法用于根据用户名从数据库中查询用户信息，然后返回一个 TUser 对象。
 * 如果用户不存在，loadUserByUsername 方法会抛出 UsernameNotFoundException 异常。
 *
 * 最终，Spring Security 将 UserDetails 对象返回给 AuthenticationManager，
 * 并与用户提交的密码进行比较，如果密码匹配，则认证成功，否则认证失败。
 *
 * 当前这个类配置了很多配置类的信息（HttpSecurity，UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter）
 * @date 2024/4/19 18:55:38
 */
//最新批注，这个类是配置类，SpringSecurity更像是DispatcherServlet，负责把请求转发到各个Handler里（比如MyAuthenticationSuccessHandler）在对应的handler里作出数据处理，最终交给前端
/*
分析登录的流程：LoginView
    ->前端发出请求(axios.post('/api/login', formDate))
    ->SpringSecurity利用SecurityConfig的配置信息作出请求拦截(requestMatchers(Constants.LOGIN_URI))
    ->SpringSecurity拦截到请求后去执行登录方法UserServiceImpl(loadUserByUsername)，执行两个sql语句，可以拿到一个完整的用户对象tUser
    ->这个完整的用户对象tUser会被放在SpringSecurity的Authentication类(具体实现UsernamePasswordAuthenticationToken类)的实例化对象里
    ->SpringSecurity成功拿到Authentication(tUser对象)并放好后就会去执行MyAuthenticationSuccessHandler类(控制器)，在这个类中处理这个tUser对象(生成token)
    ->最终SpringSecurity将这个处理好的对象交给前端(ResponseUtils.write(response, resultJSON))
    ->前端拿到响应结果(.then(response => )，结果中包含状态码200以及用户的token
    ->状态码为200，跳转至登录后的界面(DashboardView)

分析后可知：
    这个用户本体一直在Authentication里放着(这样拿Authentication authentication = SecurityContextHolder.getContext().getAuthentication();)
    这个操作实际上拿用户token进行解析也能做到：
        从请求头里拿：Integer loginUserId = JWTUtils.parseUserFromJWT(userQuery.getToken()).getId();
        从redis也能拿：String redisToken = (String) redisService.getValue(Constants.REDIS_JWT_KEY + tUser.getId())
    或许可以通过这个拿到用户的信息，进而拿到用户的权限(角色)，然后给前端返回这个信息，进行用户个性化界面(管理员，普通用户)的区分
 */
@EnableMethodSecurity//开启方法级别的权限检查
@Configuration
public class SecurityConfig {

    @Resource
    private TokenVerifyFilter tokenVerifyFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //成功
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    //失败
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    //拒绝
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    //@Autowired方法注入可加可不加，spring自己会找
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource configurationSource) throws Exception {
        return http
                .formLogin(formLogin -> {
                    formLogin.loginProcessingUrl(Constants.LOGIN_URI)//登录处理地址，不需要写controller
                            .usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .successHandler(myAuthenticationSuccessHandler)
                            .failureHandler(myAuthenticationFailureHandler);
                })
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(Constants.LOGIN_URI).permitAll().anyRequest().authenticated())
                //禁用跨站请求伪造
                .csrf(AbstractHttpConfigurer::disable)
                //支持跨域请求
                .cors(c -> c.configurationSource(configurationSource))
                //自定义过滤器进行token验证（退出操作之前（包括退出时）都会经过这个过滤器）
                .addFilterBefore(tokenVerifyFilter, LogoutFilter.class)
                //退出登录操作
                .logout(logout -> logout.logoutUrl(Constants.LOGOUT_URL).logoutSuccessHandler(myLogoutSuccessHandler))
                //权限拒绝（无权限时的处理器）
                .exceptionHandling((exceptionHandling) -> {
                    exceptionHandling.accessDeniedHandler(myAccessDeniedHandler);
                })
                .build();
    }

    //配置跨域bean（固定写法）
    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        //允许的跨域请求发出的地址
        configuration.setAllowedOrigins(Arrays.asList("*"));//http://localhost:8081/
        //允许发出请求的方法类型
        configuration.setAllowedMethods(Arrays.asList("*"));//get，post
        //允许任何请求头信息
        configuration.setAllowedHeaders(Arrays.asList("*"));

        //准备返回接口的一个实现类
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //配置信息配置一下，任何请求路径都执行上面的规则
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
