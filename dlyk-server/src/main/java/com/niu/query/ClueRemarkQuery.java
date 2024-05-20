package com.niu.query;

import lombok.Data;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/10 17:09:10
 */
@Data
public class ClueRemarkQuery extends BaseQuery {

    private Integer id;

    private Integer clueId;

    private String noteContent;

    private Integer noteWay;

}
