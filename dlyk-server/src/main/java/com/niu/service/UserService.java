package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TUser;
import com.niu.query.UserQuery;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/3/29 17:08:58
 */
public interface UserService extends UserDetailsService {

    //根据页码获取该页的数据
    PageInfo<TUser> getUserByPage(Integer current);

    //根据id查询用户信息
    TUser getUserById(Integer id);

    //新增用户
    int saveUser(UserQuery userQuery);

    //修改用户信息
    int editUser(UserQuery userQuery);

    //删除用户信息
    int delUserById(Integer id);

    //批量删除
    int batchDelUserIds(List<String> userIdArray);

    //查询负责人列表
    List<TUser> getOwnerList();
}
