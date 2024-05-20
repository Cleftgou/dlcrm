package com.niu.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 柱状图的月份和对应的活动数量
 * @date 2024/5/18 10:36:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthCount {

    //月份2024-01
    private String month;
    //数量12
    private Integer count;

}
