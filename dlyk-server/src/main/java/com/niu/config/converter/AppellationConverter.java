package com.niu.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.niu.DlykServerApplication;
import com.niu.model.TDicValue;
import com.niu.result.DicEnum;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 称呼转换器把汉字与数字进行转换
 * excel中“先生”对应java类中的18
 * “女士”对应java中的41
 * 这个类因为用在注解里，所以没办法纳入spring管理，拿mapper去查数据库对应关系，只能尝试从内存中取数据
 * @date 2024/5/6 17:31:20
 */
public class AppellationConverter implements Converter<Integer> {

    //把excel单元格中的数据转换为java中的数据
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //cellData读到的单元格数据，比如“先生”
        String cellAppellationName = cellData.getStringValue();
        //根据key（typeCode）可以拿到值
        List<TDicValue> tDicValueList = (List<TDicValue>) DlykServerApplication.cacheMap.get(DicEnum.APPELLATION.getCode());

        for (TDicValue tDicValue : tDicValueList) {
            Integer id = tDicValue.getId();
            String name = tDicValue.getTypeValue();
            if (cellAppellationName.equals(name)) {
                //"先生"和数据库表中读取的内容相同，就返回对应的id
                return id;
            }
        }
        return -1;
    }
}
