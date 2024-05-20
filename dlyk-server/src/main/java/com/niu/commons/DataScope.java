package com.niu.commons;

import java.lang.annotation.*;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 数据范围注解，根据这个注解定位到需要aop处理的方法
 * @date 2024/4/28 11:32:01
 */
//注解写在方法上，注解运行时生效，生成文档
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    //要在sql语句末尾加一个过滤条件
    //select * from t_user（管理员）
    //select * from t_user tu where tu.id = 2 （普通用户雨燕只能看见自己）

    //select * from t_activity（管理员）
    //select * from t_activity ta where ta.owner.id = 2 （普通用户雨燕只能看见自己负责的活动）

    //表的别名
    String tableAlias() default "";

    //表的字段名
    String tableField() default "";
}
