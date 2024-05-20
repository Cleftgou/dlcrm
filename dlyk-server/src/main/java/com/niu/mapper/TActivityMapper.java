package com.niu.mapper;

import com.niu.commons.DataScope;
import com.niu.model.TActivity;
import com.niu.query.ActivityQuery;
import com.niu.query.BaseQuery;
import com.niu.result.MonthCount;
import com.niu.result.NameValue;

import java.util.List;

public interface TActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TActivity record);

    int insertSelective(TActivity record);

    TActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TActivity record);

    int updateByPrimaryKey(TActivity record);

    //分页查询，数据库表别名是ta，条件过滤字段名owner_id，执行底层sql语句时，会把BaseQuery的参数filterSQL取出来（对应的getFilterSQL方法）
    //因为后面会有切面类对这个注解所标记的内容进行夺取，进而影响这里BaseQuery(这里是对ActivityQuery这个子类进行影响)的filterSQL参数，然后就动态修改底层的sql语句了
    //相比之前，这里还会利用ActivityQuery里的其他参数，进行进一步影响sql实现条件查询
    @DataScope(tableAlias = "ta", tableField = "owner_id")
    List<TActivity> selectActivityByPage(ActivityQuery query);

    //根据主键查详细信息（管理用户表）
    TActivity selectDetailByPrimaryKey(int id);

    //批量删除
    int deleteByActivityIdArray(List<String> activityIdArray);

    //查询正在进行的活动
    List<TActivity> selectOngoingActivity();

    Integer selectByCount();

    List<MonthCount> selectActivityBarChartData();
}