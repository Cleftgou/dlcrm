package com.niu.mapper;

import com.niu.model.TUpgradeLog;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/19 21:16:56
 */
public interface TUpdateLogMapper {


    List<TUpgradeLog> selectAllUpdateLogs();
}
