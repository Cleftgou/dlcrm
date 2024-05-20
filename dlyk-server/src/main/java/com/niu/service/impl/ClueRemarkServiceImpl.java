package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.mapper.TClueRemarkMapper;
import com.niu.model.TActivityRemark;
import com.niu.model.TClueRemark;
import com.niu.query.ClueRemarkQuery;
import com.niu.service.ClueRemarkService;
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
 * @date 2024/5/10 17:12:28
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Resource
    private TClueRemarkMapper clueRemarkMapper;

    @Override
    public int saveClueRemark(ClueRemarkQuery clueRemarkQuery) {
        TClueRemark clueRemark = new TClueRemark();
        BeanUtils.copyProperties(clueRemarkQuery, clueRemark);
        clueRemark.setCreateBy(JWTUtils.parseUserFromJWT(clueRemarkQuery.getToken()).getId());
        clueRemark.setCreateTime(new Date());
        return clueRemarkMapper.insertSelective(clueRemark);
    }

    @Override
    public PageInfo<TClueRemark> getClueRemarkByPage(Integer current, ClueRemarkQuery clueRemarkQuery) {
        PageHelper.startPage(current, Constants.PAGE_SIZE_A_R);
        List<TClueRemark> list = clueRemarkMapper.selectClueRemarkByPage(clueRemarkQuery);
        PageInfo<TClueRemark> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TClueRemark getClueRemarkById(Integer id) {
        return clueRemarkMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateClueRemark(ClueRemarkQuery clueRemarkQuery) {
        TClueRemark clueRemark = new TClueRemark();
        BeanUtils.copyProperties(clueRemarkQuery, clueRemark);
        clueRemark.setEditBy(JWTUtils.parseUserFromJWT(clueRemarkQuery.getToken()).getId());
        clueRemark.setEditTime(new Date());
        return clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
    }

    @Override
    public int delClueRemarkById(Integer id) {
        //逻辑删除（不删数据，只是修改一下数据状态(null或者0都正常，1是删除)）
        //所以底层是update，不是delete
        TClueRemark clueRemark = new TClueRemark();
        clueRemark.setId(id);
        clueRemark.setDeleted(1);
        return clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
    }
}
