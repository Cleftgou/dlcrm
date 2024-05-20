package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TCustomer;
import com.niu.query.CustomerQuery;
import com.niu.result.CustomerExcel;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/11 17:01:32
 */
public interface CustomerService {
    Boolean convertCustomer(CustomerQuery customerQuery);

    PageInfo<TCustomer> getCustomerByPage(Integer current);

    List<CustomerExcel> getCustomerByExcel(List<String> idList);
}
