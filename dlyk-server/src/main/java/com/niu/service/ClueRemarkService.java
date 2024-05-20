package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TClueRemark;
import com.niu.query.ClueRemarkQuery;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/10 17:11:18
 */
public interface ClueRemarkService {
    int saveClueRemark(ClueRemarkQuery clueRemarkQuery);

    PageInfo<TClueRemark> getClueRemarkByPage(Integer current, ClueRemarkQuery clueRemarkQuery);

    TClueRemark getClueRemarkById(Integer id);

    int updateClueRemark(ClueRemarkQuery clueRemarkQuery);

    int delClueRemarkById(Integer id);
}
