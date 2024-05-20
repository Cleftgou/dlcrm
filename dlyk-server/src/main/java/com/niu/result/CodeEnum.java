package com.niu.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description R类的结果枚举
 * @date 2024/3/29 17:04:48
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {

    OK(200, "成功"),

    FAIL(500, "失败"),


    TOKEN_IS_EMPTY(901, "请求的Token为空"),

    TOKEN_IS_ERROR(902, "请求的Token有误"),

    TOKEN_IS_EXPIRED(903, "请求的Token已过期或不存在"),

    TOKEN_IS_NONE_MATCH(904, "请求的Token不匹配"),

    USER_LOGOUT(200, "退出成功"),

    DATA_ACCESS_EXCEPTION(500, "数据库操作失败"),

    ACCESS_DENIED(500, "用户没有足够的权限")

    ;

    //结果码
    @Setter
    private int code;

    //结果信息
    @Setter
    private String msg;

    CodeEnum(String msg) {
        this.msg = msg;
    }
}

