package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.mapper.TTranMapper;
import com.niu.model.TCustomer;
import com.niu.model.TTran;
import com.niu.service.TranService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/18 15:34:05
 */
@Service
public class TranServiceImpl implements TranService {

    @Resource
    private TTranMapper tTranMapper;

    @Override
    public PageInfo<TTran> getTranByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<TTran> tranList = tTranMapper.selectTranPage();
        PageInfo<TTran> pageInfo = new PageInfo<>(tranList);
        return pageInfo;
    }
}
