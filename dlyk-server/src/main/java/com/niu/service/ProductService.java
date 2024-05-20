package com.niu.service;

import com.github.pagehelper.PageInfo;
import com.niu.model.TProduct;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/6 22:11:59
 */
public interface ProductService {
    //查询全部在售产品
    List<TProduct> getAllOnSaleProduct();

    PageInfo<TProduct> getProductByPage(Integer current);
}
