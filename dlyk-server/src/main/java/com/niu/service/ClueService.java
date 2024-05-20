package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TClue;
import com.niu.query.ClueQuery;

import java.io.InputStream;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/5 22:02:59
 */
public interface ClueService {

    PageInfo<TClue> getClueByPage(Integer current);

    void importExcel(InputStream inputStream, String token);

    boolean checkPhone(String phone);

    int saveClue(ClueQuery clueQuery);

    TClue getClueById(Integer id);

    int updateClue(ClueQuery clueQuery);

    boolean deleteClueById(Integer id);

    int batchDelClueByIds(List<String> idList);
}
