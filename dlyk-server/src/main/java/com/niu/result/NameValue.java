package com.niu.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 漏斗图数据
 * @date 2024/5/16 16:15:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameValue {

    private String name;
    private Integer value;

}
