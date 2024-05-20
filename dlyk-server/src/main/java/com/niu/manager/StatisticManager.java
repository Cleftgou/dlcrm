package com.niu.manager;

import com.niu.mapper.*;
import com.niu.result.MonthCount;
import com.niu.result.NameValue;
import com.niu.result.SummaryData;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/15 11:25:51
 */
@Component
public class StatisticManager {

    @Resource
    private TActivityMapper activityMapper;

    @Resource
    private TClueMapper clueMapper;

    @Resource
    private TCustomerMapper customerMapper;

    @Resource
    private TTranMapper tranMapper;

    public SummaryData loadsSummaryData() {
        //有效的市场活动数
        Integer effectiveActivityCount = activityMapper.selectOngoingActivity().size(); //偷懒写法，应该单独写个查询
        //总的市场活动数
        Integer totalActivityCount = activityMapper.selectByCount();
        //线索总数
        Integer totalClueCount = clueMapper.selectClueByCount();
        //客户总数
        Integer totalCustomerCount = customerMapper.selectCustomerByCount();
        //成功的交易额
        BigDecimal successTranAmount = tranMapper.selectBySuccessTranAmount();
        //总的交易额
        BigDecimal totalTranAmount = tranMapper.selectByTotalTranAmount();

        return SummaryData.builder()
                .effectiveActivityCount(effectiveActivityCount)
                .totalActivityCount(totalActivityCount)
                .totalClueCount(totalClueCount)
                .totalCustomerCount(totalCustomerCount)
                .successTranAmount(successTranAmount)
                .totalTranAmount(totalTranAmount)
                .build();
    }

    public List<NameValue> loadsSaleFunnelData() {
        // [
        //   { value: 20, name: '成交' },
        //   { value: 60, name: '交易' },
        //   { value: 80, name: '客户' },
        //   { value: 100, name: '线索' }
        // ]
        List<NameValue> saleFunnelData = new ArrayList<>();

        int tranSuccessCount = tranMapper.selectBySuccessTotalTranCount();
        int tranCount = tranMapper.selectByTotalTranCount();
        int customerCount = customerMapper.selectCustomerByCount();
        int clueCount = clueMapper.selectClueByCount();


        NameValue tranSuccess = NameValue.builder()
                .name("成交")
                .value(tranSuccessCount)
                .build();

        NameValue tran = NameValue.builder()
                .name("交易")
                .value(tranCount)
                .build();

        NameValue customer = NameValue.builder()
                .name("客户")
                .value(customerCount)
                .build();

        NameValue clue = NameValue.builder()
                .name("线索")
                .value(clueCount)
                .build();

        //经过测试，这里的添加顺序会影响前端销售漏斗图的颜色顺序
        saleFunnelData.add(tranSuccess);
        saleFunnelData.add(tran);
        saleFunnelData.add(customer);
        saleFunnelData.add(clue);

        return saleFunnelData;

    }

    public List<NameValue> loadSourcePieData() {
        // data: [
        //   { value: 1048, name: 'Search Engine' },
        //   { value: 735, name: 'Direct' },
        //   { value: 580, name: 'Email' },
        //   { value: 484, name: 'Union Ads' },
        //   { value: 300, name: 'Video Ads' }
        // ],
        return clueMapper.selectClueBySource();
    }

    public List<MonthCount> loadActivityBarChartData() {
        return activityMapper.selectActivityBarChartData();
    }

    public List<MonthCount> loadClueLineChartData() {
        return clueMapper.selectClueLineChartData();
    }
}
