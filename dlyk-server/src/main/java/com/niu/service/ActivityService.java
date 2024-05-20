package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TActivity;
import com.niu.query.ActivityQuery;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/2 16:29:06
 */
public interface ActivityService {

    //查询活动信息
    PageInfo<TActivity> getActivityByPage(Integer current, ActivityQuery activityQuery);

    //保存新活动
    int saveActivity(ActivityQuery activityQuery);

    //根据id查活动详情
    TActivity getActivityById(int id);

    //更新活动
    int updateActivity(ActivityQuery activityQuery);

    //删除活动
    int delActivityById(int id);

    //批量删除活动
    int batchDelActivityIds(List<String> activityIdArray);

    //查询正在进行的活动
    List<TActivity> getOngoingActivity();
}
