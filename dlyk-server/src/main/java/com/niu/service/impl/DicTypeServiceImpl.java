package com.niu.service.impl;

import com.niu.mapper.TDicTypeMapper;
import com.niu.model.TDicType;
import com.niu.service.DicTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/6 18:13:20
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Resource
    private TDicTypeMapper dicTypeMapper;

    @Override
    public List<TDicType> loadAllDicData() {
        return dicTypeMapper.selectByAll();
    }
}
