package com.niu.service;

import java.util.concurrent.TimeUnit;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/4/21 23:04:14
 */
public interface RedisService {

    //放数据
    void setValue(String key, Object value);

    //取数据
    Object getValue(String key);

    //删除数据
    Boolean removeValue(String key);

    //给某个key设置过期时间（第三个参数是枚举表示单位）
    Boolean expire(String key, Long timeOut, TimeUnit timeUnit);

}
