package com.niu.service.impl;

import com.niu.mapper.TSystemInfoMapper;
import com.niu.model.TSystemInfo;
import com.niu.service.SystemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/19 17:53:06
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private TSystemInfoMapper systemInfoMapper;

    @Override
    public List<TSystemInfo> getSystemInfo() {
        return systemInfoMapper.selectAllSystemInfo();
    }
}
