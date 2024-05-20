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
 * @description 线索状态转换器
 * @date 2024/5/6 21:32:29
 */
public class StateConverter implements Converter<Integer> {

    //把excel单元格中的数据转换为java中的数据
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //cellData读到的单元格数据，比如“已联系”->27
        String cellStateName = cellData.getStringValue();
        //根据key（typeCode）可以拿到值,DicEnum.SOURCE.getCode()返回字符串source
        List<TDicValue> tDicValueList = (List<TDicValue>) DlykServerApplication.cacheMap.get(DicEnum.STATE.getCode());

        for (TDicValue tDicValue : tDicValueList) {
            Integer id = tDicValue.getId();
            String name = tDicValue.getTypeValue();
            if (cellStateName.equals(name)) {
                //"先生"和数据库表中读取的内容相同，就返回对应的id
                return id;
            }
        }
        return -1;
    }

}
