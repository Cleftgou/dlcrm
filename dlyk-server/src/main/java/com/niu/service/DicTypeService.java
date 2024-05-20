package com.niu.service;

import com.niu.model.TDicType;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/6 18:11:21
 */
public interface DicTypeService {

    //把所有字典类型都查出来
    List<TDicType> loadAllDicData();
}
