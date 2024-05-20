package com.niu.util;

import org.springframework.util.ObjectUtils;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CacheUtils {

    //带有缓存的查询工具方法Supplier生产者，Consumer消费者
    //这里接收的三个参数，实际上是三个用lambda表达式书写的函数
    public static <T> T getCacheData(Supplier<T> cacheSelector, Supplier<T> databaseSelector, Consumer<T> cacheSave) {
        //这里才具体的进行函数的逻辑处理

        //从redis查询
        T data = cacheSelector.get();
        //如果redis没查到
        if (ObjectUtils.isEmpty(data)) {
            //从数据库查
            data = databaseSelector.get();
            //数据库查到了数据
            if (!ObjectUtils.isEmpty(data)) {
                //把数据放入redis
                cacheSave.accept(data);
            }
        }
        //返回数据
        return data;
    }
}
