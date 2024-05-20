package com.niu.web;

import com.github.pagehelper.PageInfo;
import com.niu.model.TUser;
import com.niu.query.UserQuery;
import com.niu.result.R;
import com.niu.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 用户控制器
 * @date 2024/4/21 22:18:32
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    //在登录界面的axios.post('/api/login', formDate)，由SecurityConfig进行分配处理
    //最终实际完全由MyAuthenticationSuccessHandler处理，返回给前端的信息也是
    //所以不需要自己再定义一个@GetMapping("/api/login")接口的服务器方法，下一层的业务类也由spring security去管理

    //登录后查看用户信息的功能（注意，这个不是登录功能）
    @GetMapping("/api/login/info")
    public R login(Authentication authentication) {
        //只是为了在前端显示登录用户的名字
        //获得主体信息,这个参数的注入过程交给springSecurity去实现（底层是aop等等，借助了SecurityContext）
        TUser tUser = (TUser) authentication.getPrincipal();
        return R.OK(tUser);
    }

    //自动登录（免登录）
    @GetMapping("/api/login/free")
    public R free() {
        return R.OK();
    }

    //查询用户列表（required = false参数不是必须要传）
    @PreAuthorize("hasAnyAuthority('user:list')")
    @GetMapping("/api/users")
    public R userPage(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TUser> pageInfo = userService.getUserByPage(current);
        return R.OK(pageInfo);
    }

    //根据id查询用户信息
    @PreAuthorize("hasAnyAuthority('user:view')")
    @GetMapping("/api/user/{id}")
    public R user(@PathVariable("id") Integer id) {
        TUser tUser = userService.getUserById(id);
        //主要功能就算结果转json并附上状态码和传成功提示信息
        return R.OK(tUser);
    }

    //添加新用户
    @PreAuthorize("hasAnyAuthority('user:add')")
    @PostMapping("/api/user")
    public R addUser(UserQuery userQuery, @RequestHeader("Authorization") String token) {
        //拿token是为了获取创建人id
        userQuery.setToken(token);
        int save = userService.saveUser(userQuery);
        return save>0 ? R.OK() : R.FAIL();
    }

    //编辑用户
    @PreAuthorize("hasAnyAuthority('user:edit')")
    @PutMapping("/api/user")
    public R editUser(UserQuery userQuery, @RequestHeader("Authorization") String token) {
        userQuery.setToken(token);
        int edit = userService.editUser(userQuery);
        return edit>0 ? R.OK() : R.FAIL();
    }

    //删根据id除用户
    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("/api/user/{id}")
    public R deleteUser(@PathVariable("id") Integer id) {
        int del = userService.delUserById(id);
        return del>0 ? R.OK() : R.FAIL();
    }

    //批量删除
    @PreAuthorize("hasAnyAuthority('user:delete')")
    @DeleteMapping("/api/users")
    public R batchDelUser(@RequestParam("ids") String ids){
        List<String> idList = Arrays.asList(ids.split(","));
        int del = userService.batchDelUserIds(idList);
        //单提示一个失败表示删除了0条
        return del>=idList.size() ? R.OK() : R.FAIL();
    }

    //查活动负责人
    @GetMapping("/api/owner")
    public R owner() {
        List<TUser> ownerList = userService.getOwnerList();
        return R.OK(ownerList);
    }
}
