package com.niu.web;

import com.github.pagehelper.PageInfo;
import com.niu.model.TCustomer;
import com.niu.model.TProduct;
import com.niu.result.R;
import com.niu.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 产品控制器
 * @date 2024/5/18 12:01:07
 */
@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @PreAuthorize("hasAnyAuthority('product:view') and hasAuthority('product:list')")
    @GetMapping("/api/products")
    public R getProducts(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null || current < 1) {
            current = 1;
        }
        //页码总数等信息都是pagehelper插件给的
        PageInfo<TProduct> pageInfo = productService.getProductByPage(current);
        return R.OK(pageInfo);
    }

}
