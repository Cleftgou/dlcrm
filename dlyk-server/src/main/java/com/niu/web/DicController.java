package com.niu.web;

import com.niu.DlykServerApplication;
import com.niu.model.TActivity;
import com.niu.model.TDicValue;
import com.niu.model.TProduct;
import com.niu.result.DicEnum;
import com.niu.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 字典控制器
 * @date 2024/5/9 17:14:50
 */
@RestController
public class DicController {

    @GetMapping("/api/dicValue/{typeCode}")
    public R dicData(@PathVariable("typeCode") String typeCode){
        if (typeCode.equals(DicEnum.ACTIVYTY.getCode())){
            //活动
            List<TActivity> tActivityList = (List<TActivity>)DlykServerApplication.cacheMap.get(typeCode);
            return R.OK(tActivityList);
        } else if (typeCode.equals(DicEnum.PRODUCT.getCode())){
            //产品
            List<TProduct> tProductList = (List<TProduct>)DlykServerApplication.cacheMap.get(typeCode);
            return R.OK(tProductList);
        }else {
            //字典直接从内存中拿数据（定时任务）
            List<TDicValue> tDicValueList = (List<TDicValue>)DlykServerApplication.cacheMap.get(typeCode);
            return R.OK(tDicValueList);
        }
    }

}
