package com.niu.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 概览数据
 * @date 2024/5/15 11:16:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SummaryData {
    //有效的市场活动数
    private Integer effectiveActivityCount;
    //总的市场活动数
    private Integer totalActivityCount;
    //线索总数
    private Integer totalClueCount;
    //客户总数
    private Integer totalCustomerCount;
    //成功的交易额
    private BigDecimal successTranAmount;
    //总的交易额
    private BigDecimal totalTranAmount;

}
