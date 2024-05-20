package com.niu.web;

import com.niu.result.MonthCount;
import com.niu.result.NameValue;
import com.niu.result.R;
import com.niu.result.SummaryData;
import com.niu.service.StatisticService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 统计控制器
 * @date 2024/5/15 11:14:05
 */
@RestController
public class StatisticController {

    @Resource
    private StatisticService statisticService;

    @GetMapping("/api/summary/data")
    public R summaryData() {
        SummaryData summaryData = statisticService.loadsSummaryData();
        return R.OK(summaryData);
    }

    @GetMapping("/api/saleFunnel/data")
    public R saleFunnelData() {
        // [
        //   { value: 20, name: '成交' },
        //   { value: 60, name: '交易' },
        //   { value: 80, name: '客户' },
        //   { value: 100, name: '线索' }
        // ]
        List<NameValue> nameValueList = statisticService.loadsSaleFunnelData();
        return R.OK(nameValueList);
    }

    @GetMapping("/api/sourcePie/data")
    public R sourcePieData() {
        // data: [
        //   { value: 1048, name: 'Search Engine' },
        //   { value: 735, name: 'Direct' },
        //   { value: 580, name: 'Email' },
        //   { value: 484, name: 'Union Ads' },
        //   { value: 300, name: 'Video Ads' }
        // ],
        List<NameValue> nameValueList = statisticService.loadSourcePieData();
        return R.OK(nameValueList);
    }

    @GetMapping("/api/activityBar/data")
    public R activityBarData() {
        //data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月']
        //data: [120, 200, 150, 80, 70, 110, 130],
        List<MonthCount> activityDataArray = statisticService.getActivityBarChartData();
        return R.OK(activityDataArray);
    }

    @GetMapping("/api/clueLine/data")
    public R clueLineData() {
        //data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月']
        //data: [120, 200, 150, 80, 70, 110, 130],
        List<MonthCount> clueLineDataArray = statisticService.getClueLineChartData();
        return R.OK(clueLineDataArray);
    }

}
