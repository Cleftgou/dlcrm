package com.niu.service;

import com.niu.result.MonthCount;
import com.niu.result.NameValue;
import com.niu.result.SummaryData;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/15 11:15:50
 */
public interface StatisticService {

    SummaryData loadsSummaryData();

    List<NameValue> loadsSaleFunnelData();

    List<NameValue> loadSourcePieData();

    List<MonthCount> getActivityBarChartData();

    List<MonthCount> getClueLineChartData();
}
