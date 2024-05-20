package com.niu.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.niu.DlykServerApplication;
import com.niu.model.TDicValue;
import com.niu.model.TProduct;
import com.niu.result.DicEnum;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 意向产品转换器（这个不再是数据字典了而是另一张表）
 * 比亚迪-》2
 * @date 2024/5/6 21:31:19
 */
public class IntentionProductConverter implements Converter<Integer> {

    //把excel单元格中的数据转换为java中的数据
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //cellData读到的单元格数据，比如“比亚迪”->2
        String cellIntentionProductName = cellData.getStringValue();
        //根据key（typeCode）可以拿到值
        //当前这个map集合里并没有产品表信息，所以要放
        List<TProduct> tDicValueList = (List<TProduct>) DlykServerApplication.cacheMap.get(DicEnum.PRODUCT.getCode());

        //转换部分
        for (TProduct tProduct : tDicValueList) {
            Integer id = tProduct.getId();
            String name = tProduct.getName();
            if (cellIntentionProductName.equals(name)) {
                //"比亚迪"和数据库表中读取的内容相同，就返回对应的id
                return id;
            }
        }
        return -1;
    }

}
