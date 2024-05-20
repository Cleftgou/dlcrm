package com.niu.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 字典枚举类，也可以写成常量类，ctrl+shift+u转换大小写
 * @date 2024/5/6 18:29:55
 */
@NoArgsConstructor
@AllArgsConstructor
public enum DicEnum {

    APPELLATION("appellation"),

    SOURCE("source"),

    STATE("clueState"),

    INTENTIONSTATE("intentionState"),

    NEEDLOAN("needLoan"),

    //这个不是字典值，可以自己定义
    PRODUCT("product"),

    ACTIVYTY("activity"),


    ;

    @Setter
    @Getter
    private String code;

}
