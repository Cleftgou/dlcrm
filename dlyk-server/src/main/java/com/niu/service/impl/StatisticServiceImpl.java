package com.niu.service.impl;

import com.niu.manager.StatisticManager;
import com.niu.result.MonthCount;
import com.niu.result.NameValue;
import com.niu.result.SummaryData;
import com.niu.service.StatisticService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/15 11:24:45
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private StatisticManager statisticManager;

    @Override
    public SummaryData loadsSummaryData() {
        return statisticManager.loadsSummaryData();
    }

    @Override
    public List<NameValue> loadsSaleFunnelData() {
        return statisticManager.loadsSaleFunnelData();
    }

    @Override
    public List<NameValue> loadSourcePieData() {
        return statisticManager.loadSourcePieData();
    }

    @Override
    public List<MonthCount> getActivityBarChartData() {
        return statisticManager.loadActivityBarChartData();
    }

    @Override
    public List<MonthCount> getClueLineChartData() {
        return statisticManager.loadClueLineChartData();
    }


}
