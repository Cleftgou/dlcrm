package com.niu.manager;

import com.niu.model.TUser;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 业务下沉，缓存的实现
 * rightPushAll:将一个数组中的所有元素按顺序插入到列表的右侧
 * rightPush:将一个数组作为一个整体插入到列表的右侧
 * 举个例子来说，假设有一个列表 myList 已经存在，其内容为 [1, 2, 3]：
 * 如果使用 rightPushAll("myList", [4, 5, 6])，那么列表 myList 的内容将会变成 [1, 2, 3, 4, 5, 6]，即每个元素都被逐个插入到列表的右侧。
 * 如果使用 rightPush("myList", [7, 8, 9])，那么列表 myList 的内容将会变成 [1, 2, 3, [7, 8, 9]]，即整个数组 [7, 8, 9] 作为一个元素插入到列表的右侧。
 * @date 2024/5/2 18:01:44
 */
@Component
public class RedisManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public Object getValue(String key) {
        //拿到所有数据(用户的id和name)
        //如果选择string，则通过一个键key，拿到的是一大条java.util.list（就比如ArrayList<User> list）转为json的数据
        //这里选择用集合存放数据，即通过一个键key，拿到所有用户的数据（每条list存放一条完整的用户数据，但这会导致前端取数据麻烦，不是不能取）
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /*public void setValue(String key, Object data) {
        redisTemplate.opsForList().leftPushAll(key, data);
    }*/

    //因为上面想用集合list存放数据，发现前端不好取（已有解决办法），所以改成以数组的形式存到redis里
    public <T> void setValue(String key, Collection<T> data) {
        Object[] array = data.toArray();
        //leftPush是倒序存放到redis，rightPush是正序
        redisTemplate.opsForList().rightPushAll(key, array);
    }

    //删除redis中指定的数据
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }



}
