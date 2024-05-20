package com.niu.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 更新日志表
 * @date 2024/5/19 21:06:00
 */
@Data
public class TUpgradeLog implements Serializable {
    private Integer id;
    private String description;
    private Date upgradeTime;
    private String version;
}
