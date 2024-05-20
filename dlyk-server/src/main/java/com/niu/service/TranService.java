package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TTran;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/18 15:32:50
 */
public interface TranService {
    PageInfo<TTran> getTranByPage(Integer current);
}
