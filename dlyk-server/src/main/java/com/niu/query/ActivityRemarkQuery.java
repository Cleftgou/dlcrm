package com.niu.query;

import lombok.Data;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 封装一下前端传过来的市场活动备注信息
 * @date 2024/5/4 15:08:54
 */
@Data
public class ActivityRemarkQuery extends BaseQuery{

    //备注编号
    private Integer id;

    //市场活动编号
    private Integer activityId;

    //备注的具体内容
    private String noteContent;

}
