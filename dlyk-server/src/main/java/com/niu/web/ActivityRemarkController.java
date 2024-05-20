package com.niu.web;

import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.model.TActivityRemark;
import com.niu.query.ActivityRemarkQuery;
import com.niu.result.R;
import com.niu.service.ActivityRemarkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 活动备注控制器
 * @date 2024/5/4 15:06:16
 */
@RestController
public class ActivityRemarkController {

    @Resource
    private ActivityRemarkService activityRemarkService;

    @PostMapping("/api/activity/remark")
    public R addActivityRemark(@RequestBody ActivityRemarkQuery activityRemarkQuery, @RequestHeader(Constants.TOKEN_NAME) String token){
        //axios提交post请求，提交过来的是json数据，使用@RequestBody接收
        //如果省略了 @RequestBody 注解，Spring MVC 将默认将请求数据解析为表单数据，而不是 JSON 数据（这也是为什么之前的用户控制器没加这个注解的原因）。
        //使用表单数据绑定注解（如 @RequestParam）来接收表单数据，这个情况下@RequestParam可以省略
        activityRemarkQuery.setToken(token);
        int save = activityRemarkService.saveActivityRemark(activityRemarkQuery);
        return save >0 ? R.OK() : R.FAIL();
    }

    @GetMapping("/api/activity/remark")
    public R activityRemarkPage(@RequestParam(value = "current", required = false) Integer current,
                                @RequestParam(value = "activityId") Integer activityId){
        //用这个对象传递信息
        ActivityRemarkQuery activityRemarkQuery = new ActivityRemarkQuery();
        activityRemarkQuery.setActivityId(activityId);
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TActivityRemark> pageInfo = activityRemarkService.getActivityRemarkByPage(current, activityRemarkQuery);
        return R.OK(pageInfo);
    }

    @GetMapping("/api/activity/remark/{id}")
    public R activityRemarkOne(@PathVariable(value = "id") Integer id){
        TActivityRemark pageInfo = activityRemarkService.getActivityRemarkById(id);
        return R.OK(pageInfo);
    }

    @PutMapping("/api/activity/remark")
    public R editActivityRemark(@RequestBody ActivityRemarkQuery activityRemarkQuery, @RequestHeader(Constants.TOKEN_NAME) String token){
        //根据要不要当前用户信息的状况，从请求头里拿token
        activityRemarkQuery.setToken(token);
        int update = activityRemarkService.updateActivityRemark(activityRemarkQuery);
        return update > 0 ? R.OK() : R.FAIL();
    }

    @DeleteMapping("/api/activity/remark/{id}")
    public R deleteActivityRemark(@PathVariable(value = "id") Integer id){
        int del =  activityRemarkService.delActivityRemarkById(id);
        return del > 0 ? R.OK() : R.FAIL();
    }

}
