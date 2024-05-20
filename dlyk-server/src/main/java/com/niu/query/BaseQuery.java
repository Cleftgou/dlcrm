package com.niu.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 好多类都会用到的参数
 * @date 2024/4/26 18:02:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseQuery {

    private String token;//jwt

    private String filterSQL;//数据权限的sql过滤条件(tu.id = 2)，在sql语句后面拼权限

}
