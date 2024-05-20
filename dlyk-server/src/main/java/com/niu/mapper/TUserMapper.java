package com.niu.mapper;

import com.niu.commons.DataScope;
import com.niu.model.TUser;
import com.niu.query.BaseQuery;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByLoginAct(String username);

    //分页查询，数据库表别名是tu，条件过滤字段名id，执行底层sql语句时，会把BaseQuery的参数filterSQL取出来（对应的getFilterSQL方法）
    @DataScope(tableAlias = "tu", tableField = "id")
    List<TUser> selectUserByPage(BaseQuery query);

    TUser selectUserDetailById(Integer id);

    int deleteByUserIdArray(List<String> userIdArray);

    List<TUser> selectUserByOwner();
}