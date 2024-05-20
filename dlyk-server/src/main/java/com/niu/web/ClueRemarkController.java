package com.niu.web;

import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.model.TActivityRemark;
import com.niu.model.TClueRemark;
import com.niu.query.ActivityRemarkQuery;
import com.niu.query.ClueRemarkQuery;
import com.niu.result.R;
import com.niu.service.ClueRemarkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 线索表控制器
 * @date 2024/5/10 17:07:37
 */
@RestController
public class ClueRemarkController {

    @Resource
    private ClueRemarkService clueRemarkService;

    @PostMapping("/api/clue/remark")
    public R addActivityRemark(@RequestBody ClueRemarkQuery clueRemarkQuery, @RequestHeader(Constants.TOKEN_NAME) String token){
        //axios提交post请求，提交过来的是json数据，使用@RequestBody接收
        clueRemarkQuery.setToken(token);
        int save = clueRemarkService.saveClueRemark(clueRemarkQuery);
        return save >0 ? R.OK() : R.FAIL();
    }

    @GetMapping("/api/clue/remark")
    public R clueRemarkPage(@RequestParam(value = "current", required = false) Integer current,
                                @RequestParam(value = "clueId") Integer clueId){
        //用这个对象传递信息
        ClueRemarkQuery clueRemarkQuery = new ClueRemarkQuery();
        clueRemarkQuery.setClueId(clueId);
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TClueRemark> pageInfo = clueRemarkService.getClueRemarkByPage(current, clueRemarkQuery);
        return R.OK(pageInfo);
    }

    @GetMapping("/api/clue/remark/{id}")
    public R activityRemarkOne(@PathVariable(value = "id") Integer id){
        TClueRemark tClueRemark = clueRemarkService.getClueRemarkById(id);
        return R.OK(tClueRemark);
    }

    @PutMapping("/api/clue/remark")
    public R editActivityRemark(@RequestBody ClueRemarkQuery clueRemarkQuery, @RequestHeader(Constants.TOKEN_NAME) String token){
        //根据要不要当前用户信息的状况，从请求头里拿token
        clueRemarkQuery.setToken(token);
        int update = clueRemarkService.updateClueRemark(clueRemarkQuery);
        return update > 0 ? R.OK() : R.FAIL();
    }

    @DeleteMapping("/api/clue/remark/{id}")
    public R deleteActivityRemark(@PathVariable(value = "id") Integer id){
        int del =  clueRemarkService.delClueRemarkById(id);
        return del > 0 ? R.OK() : R.FAIL();
    }


}
