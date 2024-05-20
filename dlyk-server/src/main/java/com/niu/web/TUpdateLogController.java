package com.niu.web;

import com.niu.model.TUpgradeLog;
import com.niu.result.R;
import com.niu.service.TUpdateLogService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 版本日志控制器
 * @date 2024/5/19 21:13:59
 */
@RestController
public class TUpdateLogController {

    @Resource
    private TUpdateLogService updateLogService;

    @GetMapping("/api/updateLogs")
    public R updateLogs() {
        List<TUpgradeLog> upgradeLogList = updateLogService.getUpdateLogs();
        return R.OK(upgradeLogList);
    }

}
