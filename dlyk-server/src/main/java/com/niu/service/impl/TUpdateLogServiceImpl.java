package com.niu.service.impl;

import com.niu.mapper.TUpdateLogMapper;
import com.niu.model.TUpgradeLog;
import com.niu.service.TUpdateLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/19 21:16:01
 */
@Service
public class TUpdateLogServiceImpl implements TUpdateLogService {

    @Resource
    private TUpdateLogMapper updateLogMapper;

    @Override
    public List<TUpgradeLog> getUpdateLogs() {
        return updateLogMapper.selectAllUpdateLogs();
    }
}
