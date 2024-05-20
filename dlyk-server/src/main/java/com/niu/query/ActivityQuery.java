package com.niu.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 封装的前端传来的活动查询条件
 * @date 2024/5/3 10:32:55
 */
@Data
public class ActivityQuery extends BaseQuery{

    private Integer id;

    private Integer ownerId;

    private String name;

    //前端传过来的是这个格式的字符串，后端对这个字符串进行转换，转换为日期（注意字母大小写）
    //后期优化可以替换成LocalDateTime（有时间要求）或者LocalDate，这里主要是要与数据库表生成的对应实体类一致
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。
    //BigDecimal所创建的是对象，故我们不能使用传统的+、-、*、/等算术运算符直接对其对象进行数学运算，而必须调用其相对应的方法
    private BigDecimal cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String description;

}
