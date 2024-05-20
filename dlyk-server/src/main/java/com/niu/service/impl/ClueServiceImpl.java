package com.niu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.niu.config.listener.UploadDataListener;
import com.niu.constant.Constants;
import com.niu.mapper.TClueMapper;
import com.niu.model.TClue;
import com.niu.model.TUser;
import com.niu.query.BaseQuery;
import com.niu.query.ClueQuery;
import com.niu.service.ClueService;
import com.niu.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description
 * @date 2024/5/5 22:03:44
 */
@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private TClueMapper clueMapper;

    @Override
    public PageInfo<TClue> getClueByPage(Integer current) {
        PageHelper.startPage(current, Constants.PAGE_SIZE);
        List<TClue> list = clueMapper.selectClueByPage(BaseQuery.builder().build());
        PageInfo<TClue> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public void importExcel(InputStream inputStream, String token) {
        //链式编程，参数1是要读取的文件（输入流的形式），第二个参数Excel模板类，第三个参数是监听器
        //首先通过 EasyExcel.read(inputStream, TUser.class, new UploadDataListener(clueMapper, token))
        //创建了一个读取Excel文件的对象，然后通过 sheet() 方法指定了要读取的sheet（表格），
        //这里存在多个建造者模式ExcelReaderBuilder/ExcelReaderSheetBuilder/this.excelReader.read(new ReadSheet[]{this.build()});
        //最后调用 doRead() 方法开始实际的读取操作
        EasyExcel.read(inputStream, TClue.class, new UploadDataListener(clueMapper, token))
                .sheet()//一个表有多个sheet（左下方的分表）
                .doRead();//真正开始工作
    }

    @Override
    public boolean checkPhone(String phone) {
        int count = clueMapper.selectByCount(phone);
        return count <= 0;//没有查到手机号是true
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveClue(ClueQuery clueQuery) {
        TClue tClue = new TClue();
        BeanUtils.copyProperties(clueQuery, tClue);

        tClue.setCreateBy(JWTUtils.parseUserFromJWT(clueQuery.getToken()).getId());
        tClue.setCreateTime(new Date());

        return clueMapper.insertSelective(tClue);
    }

    @Override
    public TClue getClueById(Integer id) {
        return clueMapper.selectDetailById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateClue(ClueQuery clueQuery) {
        TClue tClue = new TClue();
        BeanUtils.copyProperties(clueQuery, tClue);
        tClue.setEditBy(JWTUtils.parseUserFromJWT(clueQuery.getToken()).getId());
        tClue.setEditTime(new Date());
        return clueMapper.updateByPrimaryKeySelective(tClue);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteClueById(Integer id) {
        int delete = clueMapper.deleteByPrimaryKey(id);
        //为1代表成功删除了一条
        return delete == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelClueByIds(List<String> idList) {
        return clueMapper.deleteClueByIds(idList);
    }
}
