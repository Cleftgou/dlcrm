package com.niu.query;

import lombok.Data;

import java.util.Date;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 接收新增用户的数据模型
 * @date 2024/4/26 17:30:41
 */
@Data
public class UserQuery extends BaseQuery{

    private Integer id;

    private String loginAct;

    //这个密码需要加密
    private String loginPwd;

    private String name;

    private String phone;

    private String email;

    private Integer accountNoExpired;

    private Integer credentialsNoExpired;

    private Integer accountNoLocked;

    private Integer accountEnabled;
}
