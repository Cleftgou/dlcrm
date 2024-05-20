package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.mapper.TActivityRemarkMapper;
import com.niu.model.TActivity;
import com.niu.model.TActivityRemark;
import com.niu.model.TUser;
import com.niu.query.ActivityRemarkQuery;
import com.niu.query.BaseQuery;
import com.niu.service.ActivityRemarkService;
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
 * @date 2024/5/4 15:16:48
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Resource
    private TActivityRemarkMapper tActivityRemarkMapper;

    @Override
    public int saveActivityRemark(ActivityRemarkQuery activityRemarkQuery) {
        TActivityRemark tActivityRemark = new TActivityRemark();
        BeanUtils.copyProperties(activityRemarkQuery, tActivityRemark);

        //设置创建时间
        tActivityRemark.setCreateTime(new Date());
        //设置创建人的编号
        tActivityRemark.setCreateBy(JWTUtils.parseUserFromJWT(activityRemarkQuery.getToken()).getId());

        return tActivityRemarkMapper.insert(tActivityRemark);
    }

    @Override
    public PageInfo<TActivityRemark> getActivityRemarkByPage(Integer current, ActivityRemarkQuery activityRemarkQuery) {
        PageHelper.startPage(current, Constants.PAGE_SIZE_A_R);
        List<TActivityRemark> list = tActivityRemarkMapper.selectActivityRemarkByPage(activityRemarkQuery);
        PageInfo<TActivityRemark> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TActivityRemark getActivityRemarkById(Integer id) {
        return tActivityRemarkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateActivityRemark(ActivityRemarkQuery activityRemarkQuery) {
        TActivityRemark tActivityRemark = new TActivityRemark();

        BeanUtils.copyProperties(activityRemarkQuery, tActivityRemark);

        tActivityRemark.setEditTime(new Date()); //编辑时间

        Integer loginUserId = JWTUtils.parseUserFromJWT(activityRemarkQuery.getToken()).getId();
        tActivityRemark.setEditBy(loginUserId); //编辑人

        return tActivityRemarkMapper.updateByPrimaryKeySelective(tActivityRemark);
    }

    @Override
    public int delActivityRemarkById(Integer id) {
        //逻辑删除（不删数据，只是修改一下数据状态(null或者0都正常，1是删除)）
        //所以底层是update，不是delete
        TActivityRemark tActivityRemark = new TActivityRemark();
        tActivityRemark.setId(id);
        tActivityRemark.setDeleted(1);
        return tActivityRemarkMapper.updateByPrimaryKeySelective(tActivityRemark);
        //物理删除（直接删除掉数据）
    }

}
