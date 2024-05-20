package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TActivityRemark;
import com.niu.query.ActivityRemarkQuery;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/4 15:11:56
 */
public interface ActivityRemarkService {

    //保存活动备注信息
    int saveActivityRemark(ActivityRemarkQuery activityRemarkQuery);

    //分页查询
    PageInfo<TActivityRemark> getActivityRemarkByPage(Integer current, ActivityRemarkQuery activityRemarkQuery);

    //单独查询一条评论
    TActivityRemark getActivityRemarkById(Integer id);

    //更新操作
    int updateActivityRemark(ActivityRemarkQuery activityRemarkQuery);

    //删除一条消息
    int delActivityRemarkById(Integer id);
}
