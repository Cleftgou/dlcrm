package com.niu.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import ch.qos.logback.classic.pattern.DateConverter;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.niu.config.converter.*;
import lombok.Data;

/**
 * 线索表
 * t_clue
 */
//这表示没加注解的字段不参与excel的读写。如果不写这个注解的话下面的字段就算没加注解也会尝试去读，excel中没的话直接为null，有的话类型对不上就爆红
@ExcelIgnoreUnannotated
@Data
public class TClue implements Serializable {
    /**
     * 主键，自动增长，线索ID
     */
    private Integer id;

    /**
     * 线索所属人ID
     */
    //对应excel的表头信息
    @ExcelProperty(value = "负责人")
    private Integer ownerId;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "所属活动")
    private Integer activityId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String fullName;

    /**
     * 称呼，自定义一个称呼转换器，内置默认的在这里不太行
     */
    @ExcelProperty(value = "称呼", converter = AppellationConverter.class)
    private Integer appellation;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String phone;

    /**
     * 微信号
     */
    @ExcelProperty(value = "微信号")
    private String weixin;

    /**
     * QQ号
     */
    @ExcelProperty(value = "QQ号")
    private String qq;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 年龄
     */
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 职业
     */
    @ExcelProperty(value = "职业")
    private String job;

    /**
     * 年收入
     */
    @ExcelProperty(value = "年收入")
    private BigDecimal yearIncome;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 是否需要贷款（0不需要，1需要）
     */
    @ExcelProperty(value = "是否贷款", converter = NeedLoanConverter.class)
    private Integer needLoan;

    /**
     * 意向状态
     */
    @ExcelProperty(value = "意向状态", converter = IntentionStateConverter.class)
    private Integer intentionState;

    /**
     * 意向产品
     */
    @ExcelProperty(value = "意向产品", converter = IntentionProductConverter.class)
    private Integer intentionProduct;

    /**
     * 线索状态
     */
    @ExcelProperty(value = "线索状态", converter = StateConverter.class)
    private Integer state;

    /**
     * 线索来源
     */
    @ExcelProperty(value = "线索来源", converter = SourceConverter.class)
    private Integer source;

    /**
     * 线索描述
     */
    @ExcelProperty(value = "线索描述")
    private String description;

    /**
     * 下次联系时间
     */
    @ExcelProperty(value = "下次联系时间")
    private Date nextContactTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑人
     */
    private Integer editBy;

    //附加的数据库查询的八个对象，可以new一下，省的查到null以后强行赋值数据给java对象之后，前端取对象属性数据报错，虽然前端也能处理（就那个取数据时的三元运算符）
    //即使数据库查询没有返回结果，这些对象也不会是 null，而是已经被实例化了，避免了前端空指针异常的发生
    //负责人
    private TUser ownerDO = new TUser();

    //活动
    private TActivity activityDO = new TActivity();

    //称呼
    private TDicValue appellationDO = new TDicValue();

    //是否贷款
    private TDicValue needLoanDO = new TDicValue();

    //意向状态
    private TDicValue intentionStateDO = new TDicValue();

    //意向产品
    private TProduct intentionProductDO = new TProduct();

    //线索状态
    private TDicValue stateDO = new TDicValue();

    //线索来源
    private TDicValue sourceDO = new TDicValue();

    //编辑人
    private TUser editDO = new TUser();

    //创建人
    private TUser createDO = new TUser();

    private static final long serialVersionUID = 1L;
}