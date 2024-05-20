package com.niu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.mapper.TProductMapper;
import com.niu.model.TCustomer;
import com.niu.model.TProduct;
import com.niu.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/6 22:12:13
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private TProductMapper productMapper;

    @Override
    public List<TProduct> getAllOnSaleProduct() {
        return productMapper.selectAllOnSaleProduct();
    }

    @Override
    public PageInfo<TProduct> getProductByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<TProduct> productList = productMapper.selectProductPage();
        PageInfo<TProduct> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }
}
