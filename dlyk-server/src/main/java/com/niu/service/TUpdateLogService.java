package com.niu.service;

import com.niu.model.TUpgradeLog;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/19 21:15:54
 */
public interface TUpdateLogService {
    List<TUpgradeLog> getUpdateLogs();
}
