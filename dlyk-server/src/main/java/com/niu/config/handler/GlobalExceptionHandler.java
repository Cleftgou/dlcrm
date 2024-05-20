package com.niu.config.handler;

import com.niu.result.CodeEnum;
import com.niu.result.R;
import jakarta.servlet.ServletException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import java.sql.SQLException;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 全局异常捕获，后端服务器出问题后打印特定的错误信息给前端
 * @date 2024/4/21 22:18:32
 */
@RestControllerAdvice //aop。拦截标注了@RestController的controller中的所有方法
//@ControllerAdvice //aop。拦截标注了@Controller的controller中的所有方法
public class GlobalExceptionHandler {

    //顶级异常，先精确匹配找异常，找不到了就去找该异常的子类
    @ExceptionHandler(value = Exception.class)
    public R handException(Exception e) {
        e.printStackTrace(); //在控制台打印异常信息
        return R.FAIL(e.getMessage());
    }

    //数据访问异常
    @ExceptionHandler(value = DataAccessException.class)
    public R handException3(DataAccessException e) {
        e.printStackTrace(); //在控制台打印异常信息
        //返回500错误加上 数据库操作失败
        return R.FAIL(CodeEnum.DATA_ACCESS_EXCEPTION);
    }

    //服务器路径访问异常
    @ExceptionHandler(value = ServletException.class)
    public R handException4(ServletException e) {
        e.printStackTrace();
        //返回500错误加上 数据库操作失败
        return R.FAIL(e.getMessage());
    }

    //权限不足异常，全局异常捕获和权限拒绝捕获冲突了，注意捕获异常的包名
    @ExceptionHandler(value = AccessDeniedException.class)
    public R handException5(AccessDeniedException e) {
        e.printStackTrace();
        return R.FAIL(CodeEnum.ACCESS_DENIED);
    }
}
