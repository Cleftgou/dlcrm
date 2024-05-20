package com.niu.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.model.TClue;
import com.niu.model.TCustomer;
import com.niu.query.CustomerQuery;
import com.niu.result.CustomerExcel;
import com.niu.result.R;
import com.niu.service.CustomerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 客户控制器
 * @date 2024/5/11 16:54:59
 */
@RestController
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/api/clue/customer")
    public R convertCustomer(@RequestBody CustomerQuery customerQuery, @RequestHeader(Constants.TOKEN_NAME) String token) {
        customerQuery.setToken(token);
        Boolean convert = customerService.convertCustomer(customerQuery);
        return convert ? R.OK() : R.FAIL();
    }

    @GetMapping("/api/customers")
    public R getCustomers(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TCustomer> pageInfo = customerService.getCustomerByPage(current);
        return R.OK(pageInfo);
    }

    @GetMapping("/api/exportExcel")
    public void exportExcel(HttpServletResponse response, @RequestParam(value = "ids", required = false) String ids) throws IOException {
        List<String> idList = StringUtils.hasText(ids) ? Arrays.asList(ids.split(",")) : new ArrayList<>();
        //先去查询要保存的内容
        List<CustomerExcel> customerList = customerService.getCustomerByExcel(idList);
        //要想让浏览器弹出下载框，后端设置响应信息
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        //文件名有中文会乱码，要处理一下
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(Constants.EXCEL_FILE_NAME + new Date(), StandardCharsets.UTF_8) + ".xlsx");
        //2、后端查询数据库的数据，把数据写入Excel，然后把Excel以IO流的方式输出到前端浏览器（我们来实现）
        EasyExcel.write(response.getOutputStream(), CustomerExcel.class)
                .sheet("模板")
                .doWrite(customerList);
    }

}
