package com.niu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.netty.util.internal.ObjectUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

/**
 * 用户表
 * t_user
 */
@Data
public class TUser implements UserDetails,Serializable {
    /**
     * 主键，自动增长，用户ID
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String loginAct;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账户是否没有过期，0已过期 1正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期，0已过期 1正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账号是否没有锁定，0已锁定 1正常
     */
    private Integer accountNoLocked;

    /**
     * 账号是否启用，0禁用 1启用
     */
    private Integer accountEnabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑人
     */
    private Integer editBy;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 菜单的list
     */
    private List<TPermission> menuPermissionList;

    //一对一关联的多余属性（创建人）
    private TUser createByDO;

    //一对一关联的多余属性（修改人）
    private TUser editByDO;

    private static final long serialVersionUID = 1L;

    //-----------------------------------------------------------------------------------------------------

    //角色list，从数据库取出的数据会放在这里
    private List<String> roleList;

    //权限标识符
    private List<String> permissionList;

    //实现接口的方法，此外再转成json字符串时忽略（这就体现了包装一层的好处）
    //isAccountNonExpired()、isAccountNonLocked()、
    // isCredentialsNonExpired() 和 isEnabled() 这些方法会在认证过程中被调用，以检查用户账户是否过期、是否被锁定、凭据是否过期以及账户是否可用。
    // 如果这些状态不符合预期，Spring Security 将会相应地给出反馈，例如拒绝登录或者限制访问。
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //存放角色和权限
        List<GrantedAuthority> list = new ArrayList<>();

        //不是空再判断（spring框架给的）
        if (!ObjectUtils.isEmpty(this.getRoleList())) {
            //角色
            this.getRoleList().forEach(role -> {
                //这个new是权限具体实现类
                list.add(new SimpleGrantedAuthority(role));
            });
        }

        if (!ObjectUtils.isEmpty(this.getPermissionList())) {
            //权限标识符
            this.getPermissionList().forEach(permission -> {
                list.add(new SimpleGrantedAuthority(permission));
            });
        }
        return list;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.getLoginPwd();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.getLoginAct();
    }

    //账户是否过期（1不过期）
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.getAccountNoExpired() == 1;
    }

    //账户是否锁定
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.getAccountNoLocked() == 1;
    }

    //账户密码是否过期
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return this.getCredentialsNoExpired() == 1;
    }

    //账户是否可用
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.getAccountEnabled() == 1;
    }
}