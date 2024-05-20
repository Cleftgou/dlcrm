package com.niu.task;

import com.niu.DlykServerApplication;
import com.niu.model.TActivity;
import com.niu.model.TDicType;
import com.niu.model.TDicValue;
import com.niu.model.TProduct;
import com.niu.result.DicEnum;
import com.niu.service.ActivityService;
import com.niu.service.DicTypeService;
import com.niu.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 定时任务
 * 为了把类型对应的编号查出来放在内存在，供AppellationConverter使用
 * @date 2024/5/6 17:45:25
 */
@EnableScheduling//开启定时任务
@Component//需要将其纳入 Spring 容器的管理中，以便 Spring 能够识别并执行其中的定时任务方法
public class DataTask {

    @Resource
    private DicTypeService dicTypeService;

    @Resource
    private ProductService productService;

    @Resource
    private ActivityService activityService;

    //调度的意思
    //@Scheduled(cron = "${project.task.cron}", zone = "Asia/Shanghai", timeUnit = TimeUnit.MICROSECONDS)//确定的延迟时间，数字表示，单位是毫秒
    //更详细的调度写法，项目启动后1秒执行，之后每三分钟执行一次
    @Scheduled (initialDelay = 0, fixedRate = 300000)
    public void task(){
        /*
        定时任务逻辑执行...Mon May 06 17:57:24 CST 2024
        定时任务逻辑执行...Mon May 06 17:57:27 CST 2024
        定时任务逻辑执行...Mon May 06 17:57:30 CST 2024
         */
        System.out.println("定时任务逻辑执行..." + new Date());
        /*
        查到的内容是如下格式：DicType已经扩展了private List<TDicValue> dicValueList;
            12	userState	用户状态		58	正常
            12	userState	用户状态		59	锁定
            12	userState	用户状态		60	禁用
            13	noteWay	跟踪方式		61	电话
            13	noteWay	跟踪方式		61	电话
            13	noteWay	跟踪方式		62	微信
            13	noteWay	跟踪方式		63	QQ
         */
        List<TDicType> tDicTypeList = dicTypeService.loadAllDicData();

        //数据查到后，放到内存中，键名的tDicType（比如userState）
        tDicTypeList.forEach(tDicType -> {
            String typeCode = tDicType.getTypeCode();
            List<TDicValue> dicValueList = tDicType.getDicValueList();
            //存放
            DlykServerApplication.cacheMap.put(typeCode, dicValueList);
        });
        //System.out.println("查到的数据：" + DlykServerApplication.cacheMap.get(DicEnum.APPELLATION.getCode()));

        //查所有在售产品表
        List<TProduct> tProductList = productService.getAllOnSaleProduct();
        //放到map集合里
        DlykServerApplication.cacheMap.put(DicEnum.PRODUCT.getCode(), tProductList);

        //查询正在进行的市场活动
        List<TActivity> tActivityList = activityService.getOngoingActivity();
        DlykServerApplication.cacheMap.put(DicEnum.ACTIVYTY.getCode(), tActivityList);

    }

}
