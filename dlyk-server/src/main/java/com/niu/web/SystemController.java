package com.niu.web;

import com.niu.model.TSystemInfo;
import com.niu.result.R;
import com.niu.service.SystemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 系统控制器
 * @date 2024/5/19 17:49:36
 */
@RestController
public class SystemController {

    @Resource
    private SystemService systemService;

    @GetMapping("/api/notices")
    public R notices() {
        List<TSystemInfo> systemInfoList = systemService.getSystemInfo();
        return R.OK(systemInfoList);
    }



}
