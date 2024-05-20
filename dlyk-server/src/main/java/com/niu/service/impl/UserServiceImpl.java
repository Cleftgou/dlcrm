package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.manager.RedisManager;
import com.niu.mapper.TPermissionMapper;
import com.niu.mapper.TRoleMapper;
import com.niu.mapper.TUserMapper;
import com.niu.model.TPermission;
import com.niu.model.TRole;
import com.niu.model.TUser;
import com.niu.query.BaseQuery;
import com.niu.query.UserQuery;
import com.niu.service.UserService;
import com.niu.util.CacheUtils;
import com.niu.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/3/29 17:09:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private TUserMapper tUserMapper;

    @Resource
    private TRoleMapper tRoleMapper;

    @Resource
    private RedisManager redisManager;

    @Resource
    private TPermissionMapper tPermissionMapper;

    //springSecurity的登录查询，这个方法名来自UserDetailsService接口，是固定的，这个方法由springSecurity调用，之后的密码的比对交给框架做
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这个执行的sql语句只查询到了用户表的信息，不包括权限表
        TUser tUser = tUserMapper.selectByLoginAct(username);

        if (tUser == null){
            throw new UsernameNotFoundException("登录账号不存在");
        }

        //查询一下用户的角色
        List<TRole> tRoleList = tRoleMapper.selectByUserId(tUser.getId());

        //字符串的角色列表
        List<String> stringRoleList = new ArrayList<>();
        tRoleList.forEach(role -> {
            stringRoleList.add(role.getRole());
        });

        tUser.setRoleList(stringRoleList);//把角色（不是角色名字，看清字段）放入用户，这个是控制查询sql自己只能看自己

        //查询一下用户拥有的菜单
        List<TPermission> menuPermissionList = tPermissionMapper.selectMenuPermissionByUserId(tUser.getId());
        tUser.setMenuPermissionList(menuPermissionList);

        //查询用户的功能权限（按钮）
        List<TPermission> buttonPermissionList = tPermissionMapper.selectButtonPermissionByUserId(tUser.getId());
        //权限标识符
        List<String> permissionList = new ArrayList<>();
        buttonPermissionList.forEach(permission -> {
            permissionList.add(permission.getCode());
        });
        tUser.setPermissionList(permissionList);//设置用户的权限标识符，这个是安全框架去管理

        return tUser;
    }

    @Override
    public PageInfo<TUser> getUserByPage(Integer current) {
        //设置PageHelper
        //在执行DQL（数据查询语言）语句之前进行分页操作
        //current表示要看的页码
        //pageSize表示每页显示的记录条数
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        //查询（查询全部用户），这里现在传递的参数是个空壳，通过aop操作给里面filterSQL赋上正确的值
        List<TUser> list = tUserMapper.selectUserByPage(BaseQuery.builder().build());
        PageInfo<TUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public TUser getUserById(Integer id) {
        //调用自动生成的根据主键（id）查信息
        return tUserMapper.selectUserDetailById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveUser(UserQuery userQuery) {
        //经典取数据对象赋值给实体类对象（复制），可以使用框架内置的工具类
        TUser tUser = new TUser();

        //使用前提，两个对象的属性名和属性类型要相同
        BeanUtils.copyProperties(userQuery, tUser);

        tUser.setLoginPwd(passwordEncoder.encode(userQuery.getLoginPwd()));
        tUser.setCreateTime(new Date());

        //拿到当前登录用户的id，即创建人id，解析jwt获得（用户每次操作都会传递这个jwt）
        Integer loginUserId = JWTUtils.parseUserFromJWT(userQuery.getToken()).getId();
        tUser.setCreateBy(loginUserId);

        return tUserMapper.insertSelective(tUser);
    }

    @Override
    public int editUser(UserQuery userQuery) {
        TUser tUser = new TUser();

        BeanUtils.copyProperties(userQuery, tUser);

        //单向加密，没办法给用户展示原密码
        //修改密码（展示的时候密码栏是空的，如果重新写了密码的值，就会去修改原本密码，替换为加密密码，否则不作修改）
        if (StringUtils.hasText(userQuery.getLoginPwd())){
            tUser.setLoginPwd(passwordEncoder.encode(userQuery.getLoginPwd()));
        }

        //编辑时间
        tUser.setEditTime(new Date());

        //编辑人id
        Integer id = JWTUtils.parseUserFromJWT(userQuery.getToken()).getId();
        tUser.setEditBy(id);

        int b= tUserMapper.updateByPrimaryKeySelective(tUser);
        if (b > 0){
            //mysql修改成功，删除此人在redis中的token
            redisManager.deleteKey(Constants.REDIS_JWT_KEY + tUser.getId());
        }
        return b;
    }

    @Override
    public int delUserById(Integer id) {
        int b = tUserMapper.deleteByPrimaryKey(id);
        if (b == 1){
            //mysql数据库删除成功

            //同时移除redis中的用户信息
            redisManager.deleteKey(Constants.REDIS_JWT_KEY + id);
        }
        return b;
    }

    @Override
    public int batchDelUserIds(List<String> userIdArray) {
        //批量删除，实际上不用if判断，有全局异常捕获和事务注解管理呢
        int b = tUserMapper.deleteByUserIdArray(userIdArray);
        if (b == userIdArray.size()){
            userIdArray.forEach(userId -> redisManager.deleteKey(Constants.REDIS_JWT_KEY + userId));
        }
        return b;
    }

    @Override
    public List<TUser> getOwnerList() {
        //带有缓存的查询，减小数据库的压力
        //从redis里查询。查不到再去数据库，再放到redis里（缓存几分钟）
        return CacheUtils.getCacheData(() -> {
            //复制参数列表，加上箭头和大于号花括号，组合成了第一个参数
            //生产（给东西），从redis中查询数据
            return (List<TUser>) redisManager.getValue(Constants.REDIS_OWNER_KEY);
        }, () -> {
            //生产，从数据库里拿
            return (List<TUser>) tUserMapper.selectUserByOwner();
        }, (t) ->{
            //消费（没有返回值），把数据写入redis
            redisManager.setValue(Constants.REDIS_OWNER_KEY, t);
        });
    }
}
