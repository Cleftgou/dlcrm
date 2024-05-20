package com.niu.aspect;

import com.niu.commons.DataScope;
import com.niu.constant.Constants;
import com.niu.model.TUser;
import com.niu.query.BaseQuery;
import com.niu.util.JWTUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description spring aop，aspect实现
 * Spring 在扫描包并初始化 Bean 的过程中，会检查被 @Component 注解标记的类，如果发现类中使用了 AOP 相关的注解（比如 @Pointcut、@Around 等），
 * 就会将这个类识别为切面类，并且会将其实例化为一个切面 Bean，使其生效。
 * @date 2024/4/28 11:50:44
 */
@Aspect
@Component
public class DataScopeAspect {

    //切点声明
    //指定了一个切点表达式，用于匹配被 @DataScope 注解标记的方法。这意味着当被 @DataScope 注解标记的方法被调用时，切面中的增强方法将会被触发。
    //切点方法，用于定义切点的具体匹配规则。这里只是一个空方法体，因为它的作用仅仅是作为一个标识符
    @Pointcut(value = "@annotation(com.niu.commons.DataScope)")
    public void pointCut(){
    }

    //切面（加一个环绕增强）（其实改成前置增强更好）
    //@Around 注解标记，指定了它要织入到的切点。这里的 pointCut() 就是前面定义的切点方法，意味着这个增强方法会围绕着 pointCut() 方法所匹配到的方法执行。
    //接收一个 ProceedingJoinPoint 对象作为参数，该对象包含了被切入方法的信息。在环绕增强方法内部，你可以在调用被切入方法之前执行一些逻辑，
    //也可以在调用之后执行一些逻辑，甚至可以完全替换被切入方法的调用
    @Around(value = "pointCut()")
    public Object proceed(ProceedingJoinPoint joinPoint) throws Throwable {
        //方法签名
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        //先拿到方法，再拿到方法上的注解
        DataScope dataScope = methodSignature.getMethod().getDeclaredAnnotation(DataScope.class);

        //拿到存储的字段，用来拼sql语句
        //表的别名tu
        String tableAlias = dataScope.tableAlias();
        //表的字段名id
        String tabledField = dataScope.tableField();

        //拿到request对象才能知道当前登录人的身份
        //spring提供的工具类，随时随地获取到当前请求的 HttpServletRequest 对象，而无需在每个需要请求对象的地方都显式地传递它作为参数。
        //这样可以方便地在各个地方获取请求对象，并且减少了代码的重复性。
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(Constants.TOKEN_NAME);

        //从token中拿到用户的角色
        TUser tUser = JWTUtils.parseUserFromJWT(token);
        //拿到角色集合
        List<String> roleList = tUser.getRoleList();

        if (!roleList.contains("admin")) {
            //不是管理员，所以只能查看自己的数据，作sql拼接
            //拿到方法的第一个参数
            Object params = joinPoint.getArgs()[0];
            if (params instanceof BaseQuery) {
                BaseQuery query = (BaseQuery) params;
                //select * from t_user tu where tu.id = 2 （普通用户雨燕只能看见自己），where的拼接到xml文件里进行标签的处理（xml还需要配别名）
                //此外，因为是sql拼接，所以要使用${filterSQL}，而不是#{filterSQL}，这样才能完成拼接
                query.setFilterSQL(" and " + tableAlias + "." + tabledField + "=" + tUser.getId());
            }
        }

        System.out.println("方法执行之前...");
        Object result = joinPoint.proceed();
        System.out.println("方法执行之后...");

        //方法操作完成了，继续执行原方法
        return result;
    }

}
