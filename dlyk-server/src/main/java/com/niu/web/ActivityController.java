package com.niu.web;

import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.model.TActivity;
import com.niu.model.TUser;
import com.niu.query.ActivityQuery;
import com.niu.result.R;
import com.niu.service.ActivityService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 活动控制器
 * @date 2024/5/2 16:26:02
 */
@RestController
public class ActivityController {

    @Resource
    private ActivityService activityService;

    //查询活动列表（required = false参数不是必须要传）
    @PreAuthorize("hasAnyAuthority('activity:search') and hasAuthority('activity:list')")
    @GetMapping("/api/activitys")
    public R activityPage(@RequestParam(value = "current", required = false) Integer current, ActivityQuery activityQuery) {
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TActivity> pageInfo = activityService.getActivityByPage(current, activityQuery);
        return R.OK(pageInfo);
    }

    @PreAuthorize("hasAnyAuthority('activity:add')")
    @PostMapping("/api/activity")
    public R addActivity(ActivityQuery activityQuery, @RequestHeader(Constants.TOKEN_NAME) String token) {
        //拿token是为了获取创建人id
        activityQuery.setToken(token);
        int save = activityService.saveActivity(activityQuery);
        return save>0 ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('activity:view')")
    @GetMapping("/api/activity/{id}")
    public R loadActivity(@PathVariable("id") int id) {
        TActivity tActivity = activityService.getActivityById(id);
        return R.OK(tActivity);
    }

    @PreAuthorize("hasAnyAuthority('activity:edit')")
    @PutMapping(value = "/api/activity")
    public R editActivity(ActivityQuery activityQuery, @RequestHeader(Constants.TOKEN_NAME) String token) {
        activityQuery.setToken(token);
        int update = activityService.updateActivity(activityQuery);
        return update >= 1 ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('activity:delete')")
    @DeleteMapping("/api/activity/{id}")
    public R deleteActivity(@PathVariable("id") int id) {
        int del = activityService.delActivityById(id);
        return del>0 ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('activity:delete')")
    @DeleteMapping("/api/activities")
    public R batchDelActivity(@RequestParam("ids") String ids){
        List<String> idList = Arrays.asList(ids.split(","));
        int del = activityService.batchDelActivityIds(idList);
        //单提示一个失败表示删除了0条
        return del>=idList.size() ? R.OK() : R.FAIL();
    }

}
