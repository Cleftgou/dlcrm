package com.niu.web;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.niu.constant.Constants;
import com.niu.model.TClue;
import com.niu.model.TUser;
import com.niu.query.ClueQuery;
import com.niu.result.R;
import com.niu.service.ClueService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 线索控制器
 * @date 2024/5/5 22:00:38
 */
@RestController
public class ClueController {

    @Resource
    private ClueService clueService;

    //相当于用户的通行证（权限），只有登录后查到用户具有这个权限，才能执行此控制器，这个权限判断，都是安全框架做的
    @PreAuthorize("hasAnyAuthority('clue:list')")
    @GetMapping("/api/clues")
    public R cluePage(@RequestParam(value = "current", required = false) Integer current) {
        if (current == null || current < 1) {
            current = 1;
        }
        PageInfo<TClue> pageInfo = clueService.getClueByPage(current);
        return R.OK(pageInfo);
    }

    @PreAuthorize("hasAnyAuthority('clue:import')")
    @PostMapping("/api/importExcel")
    public R importExcel(@RequestParam("file") MultipartFile file, @RequestHeader(Constants.TOKEN_NAME) String token) throws IOException {
        clueService.importExcel(file.getInputStream(), token);
        return R.OK();
    }

    //没有标权限就是任何人都可以查
    @GetMapping("/api/clue/{phone}")
    public R checkPhone(@PathVariable("phone") String phone) {
        boolean check = clueService.checkPhone(phone);
        //查到手机号返回false（500错误），不让登记
        return check ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('clue:add')")
    @PostMapping("/api/clue")
    public R addClue(ClueQuery clueQuery, @RequestHeader(Constants.TOKEN_NAME) String token) {
        clueQuery.setToken(token);
        int save = clueService.saveClue(clueQuery);
        return save>=1 ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('clue:view')")
    @GetMapping("/api/clueDetail/{id}")
    public R loadClue(@PathVariable("id") Integer id) {
        TClue clue = clueService.getClueById(id);
        return R.OK(clue);
    }

    @PreAuthorize("hasAnyAuthority('clue:edit')")
    @PutMapping("/api/clue")
    public R editClue(ClueQuery clueQuery, @RequestHeader(Constants.TOKEN_NAME) String token) {
        clueQuery.setToken(token);
        int update = clueService.updateClue(clueQuery);
        return update>=1 ? R.OK() : R.FAIL();
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:delete')")
    @DeleteMapping("/api/clue/{id}")
    public R deleteClue(@PathVariable("id") Integer id) {
        boolean delete = clueService.deleteClueById(id);
        return delete ? R.OK() : R.FAIL();
    }

    @PreAuthorize("hasAnyAuthority('clue:delete')")
    @DeleteMapping("/api/clues")
    public R deleteClueBatch(@RequestParam("ids") String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        int del = clueService.batchDelClueByIds(idList);
        //单提示一个失败表示删除了0条
        return del>=idList.size() ? R.OK() : R.FAIL();
    }

}
