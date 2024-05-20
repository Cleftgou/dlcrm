package com.niu.mapper;

import com.niu.commons.DataScope;
import com.niu.model.TClue;
import com.niu.query.BaseQuery;
import com.niu.result.MonthCount;
import com.niu.result.NameValue;

import java.util.List;

public interface TClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClue record);

    int insertSelective(TClue record);

    TClue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClue record);

    int updateByPrimaryKey(TClue record);

    //分页查询（只能看到自己登记的线索）
    @DataScope(tableAlias = "tc", tableField = "owner_id")
    List<TClue> selectClueByPage(BaseQuery build);

    //excel数据报存到数据库
    void saveClue(List<TClue> tClueList);

    //根据电话统计是否已经存在了数据
    int selectByCount(String phone);

    int selectClueByCount();

    //查询详细信息
    TClue selectDetailById(Integer id);

    //批量删除
    int deleteClueByIds(List<String> idList);

    //根据来源查线索数量（比如微信来源的有20条线索）
    List<NameValue> selectClueBySource();

    //查找每月的线索数量
    List<MonthCount> selectClueLineChartData();
}