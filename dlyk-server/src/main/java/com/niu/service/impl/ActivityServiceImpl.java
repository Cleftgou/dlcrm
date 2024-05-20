package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.mapper.TActivityMapper;
import com.niu.model.TActivity;
import com.niu.model.TUser;
import com.niu.query.ActivityQuery;
import com.niu.query.BaseQuery;
import com.niu.service.ActivityService;
import com.niu.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/2 16:30:36
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private TActivityMapper activityMapper;

    @Override
    public PageInfo<TActivity> getActivityByPage(Integer current, ActivityQuery activityQuery) {
        PageHelper.startPage(current, Constants.PAGE_SIZE_A);

        List<TActivity> list = activityMapper.selectActivityByPage(activityQuery);

        PageInfo<TActivity> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public int saveActivity(ActivityQuery activityQuery) {
        TActivity tactivity = new TActivity();

        BeanUtils.copyProperties(activityQuery, tactivity);

        tactivity.setCreateTime(new Date());

        Integer loginUserId = JWTUtils.parseUserFromJWT(activityQuery.getToken()).getId();
        tactivity.setCreateBy(loginUserId);

        return activityMapper.insertSelective(tactivity);
    }

    @Override
    public TActivity getActivityById(int id) {
        return activityMapper.selectDetailByPrimaryKey(id);
    }

    @Override
    public int updateActivity(ActivityQuery activityQuery) {
        TActivity tActivity = new TActivity();

        BeanUtils.copyProperties(activityQuery, tActivity);

        tActivity.setEditTime(new Date()); //编辑时间

        Integer loginUserId = JWTUtils.parseUserFromJWT(activityQuery.getToken()).getId();
        tActivity.setEditBy(loginUserId); //编辑人

        return activityMapper.updateByPrimaryKeySelective(tActivity);
    }

    @Override
    public int delActivityById(int id) {
        return activityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int batchDelActivityIds(List<String> activityIdArray) {
        return activityMapper.deleteByActivityIdArray(activityIdArray);
    }

    @Override
    public List<TActivity> getOngoingActivity() {
        return activityMapper.selectOngoingActivity();
    }
}
