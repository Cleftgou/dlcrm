package com.niu.query;


import lombok.Data;

import java.util.Date;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/11 16:59:02
 */
@Data
public class CustomerQuery extends BaseQuery {

    private Integer clueId;

    private Integer product;

    private String description;

    private Date nextContactTime;
}

