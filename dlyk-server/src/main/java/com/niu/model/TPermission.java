package com.niu.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 权限表
 * t_permission
 */
@Data
public class TPermission implements Serializable {
    private Integer id;

    private String name;

    private String code;

    private String url;

    private String type;

    private Integer parentId;

    private Integer orderNo;

    private String icon;

    //额外的数据一对多关联
    private List<TPermission> subPermissionList;


    private static final long serialVersionUID = 1L;
}