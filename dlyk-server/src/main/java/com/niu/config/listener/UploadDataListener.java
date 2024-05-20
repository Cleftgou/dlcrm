package com.niu.config.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.niu.mapper.TClueMapper;
import com.niu.model.TClue;
import com.niu.util.JSONUtils;
import com.niu.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description excel快速读取并报存到数据库
 * 在特定事件发生时执行相应操作的设计模式称为观察者模式或者监听器模式
 * 监听器，每读一行数据就会触发该监听器中的invoke()方法，读完之后会触发doAfterAllAnalysed()方法
 * 这段代码创建了一个配置好的监听器对象 UploadDataListener，但是并没有直接执行操作，但创建对象的过程中就把任务完成了（触发器）
 * @date 2024/5/6 13:07:30
 */
@Slf4j
public class UploadDataListener implements ReadListener<TClue> {

    //每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 100;
    //一个长度自定义的缓存列表，底层是arraylist
    private List<TClue> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    //假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
    private TClueMapper clueMapper;
    private String token;

    public UploadDataListener() {}

    //如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
    public UploadDataListener(TClueMapper clueMapper, String token) {
        this.clueMapper = clueMapper;
        this.token = token;
    }

    //每读一条数据就会触发一次
    @Override
    public void invoke(TClue tClue, AnalysisContext context) {

        //tClue就代表了一行excel数据，tClue能存储excel对象是因为tClue有注解标注
        log.info("解析到一条数据:{}", JSONUtils.toJSON(tClue));

        //给读到的clue对象设置创建时间（导入时间）和创建人（导入人），所以需要token
        tClue.setCreateTime(new Date());
        Integer loginUserId = JWTUtils.parseUserFromJWT(token).getId();
        tClue.setCreateBy(loginUserId);

        //每读取一行就把数据放入一个缓存list中（cachedDataList）
        cachedDataList.add(tClue);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            //把缓存中的数据写入数据库
            saveData();
            //存储完成清理 list，重新来一个list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }

        //如果没有装满100条，则那几条数据会在doAfterAllAnalysed方法中调用存储方法，往数据库存放数据
    }
    //所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    //加上存储数据库
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        //把缓存list存放到数据库
        clueMapper.saveClue(cachedDataList);
        log.info("存储数据库成功！");
    }

}
