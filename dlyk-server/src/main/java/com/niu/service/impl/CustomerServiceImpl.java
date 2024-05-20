package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.manager.CustomerManager;
import com.niu.mapper.TCustomerMapper;
import com.niu.model.TCustomer;
import com.niu.query.CustomerQuery;
import com.niu.result.CustomerExcel;
import com.niu.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 需要操作多个mapper去manage层管理，这里直接去注入操作manage层
 * @date 2024/5/11 17:01:41
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerManager manager;

    @Resource
    private TCustomerMapper customerMapper;

    @Override
    public Boolean convertCustomer(CustomerQuery customerQuery) {
        return manager.convertCustomer(customerQuery);
    }

    @Override
    public PageInfo<TCustomer> getCustomerByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<TCustomer> customerList = customerMapper.selectCustomerPage();
        PageInfo<TCustomer> pageInfo = new PageInfo<>(customerList);
        return pageInfo;
    }

    @Override
    public List<CustomerExcel> getCustomerByExcel(List<String> idList) {
        List<TCustomer> customerList = customerMapper.selectCustomerByExcel(idList);
        List<CustomerExcel> customerExcelList = new ArrayList<>();

        //数据转换，字段对不上，不能使用BeanUtils，只能人工处理
        customerList.forEach(tCustomer -> {
            CustomerExcel customerExcel = new CustomerExcel();

            customerExcel.setOwnerName(ObjectUtils.isEmpty(tCustomer.getOwnerDO()) ? Constants.EMPTY : tCustomer.getOwnerDO().getName());
            customerExcel.setActivityName(ObjectUtils.isEmpty(tCustomer.getActivityDO()) ? Constants.EMPTY : tCustomer.getActivityDO().getName());
            customerExcel.setFullName(tCustomer.getClueDO().getFullName());
            customerExcel.setAppellationName(ObjectUtils.isEmpty(tCustomer.getAppellationDO()) ? Constants.EMPTY : tCustomer.getAppellationDO().getTypeValue());
            customerExcel.setPhone(tCustomer.getClueDO().getPhone());
            customerExcel.setWeixin(tCustomer.getClueDO().getWeixin());
            customerExcel.setQq(tCustomer.getClueDO().getQq());
            customerExcel.setEmail(tCustomer.getClueDO().getEmail());
            customerExcel.setAge(Optional.ofNullable(tCustomer.getClueDO().getAge()).orElse(0));
            customerExcel.setJob(tCustomer.getClueDO().getJob());
            customerExcel.setYearIncome(tCustomer.getClueDO().getYearIncome());
            customerExcel.setAddress(tCustomer.getClueDO().getAddress());
            customerExcel.setNeedLoadName(ObjectUtils.isEmpty(tCustomer.getNeedLoanDO()) ? Constants.EMPTY : tCustomer.getNeedLoanDO().getTypeValue());
            customerExcel.setProductName(ObjectUtils.isEmpty(tCustomer.getIntentionProductDO()) ? Constants.EMPTY : tCustomer.getIntentionProductDO().getName());
            customerExcel.setSourceName(ObjectUtils.isEmpty(tCustomer.getSourceDO()) ? Constants.EMPTY : tCustomer.getSourceDO().getTypeValue());
            customerExcel.setDescription(tCustomer.getDescription());
            customerExcel.setNextContactTime(tCustomer.getNextContactTime());

            customerExcelList.add(customerExcel);
        });
        return customerExcelList;
    }
}
