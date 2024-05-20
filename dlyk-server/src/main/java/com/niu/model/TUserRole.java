package com.niu.model;

import java.io.Serializable;
import lombok.Data;

/**
 * 用户角色关系表
 * t_user_role
 */
@Data
public class TUserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}