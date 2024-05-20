package com.niu.manager;

import com.niu.mapper.TClueMapper;
import com.niu.mapper.TCustomerMapper;
import com.niu.model.TClue;
import com.niu.model.TCustomer;
import com.niu.query.CustomerQuery;
import com.niu.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description 需要操作多个mapper去manage层管理
 * @date 2024/5/11 17:10:56
 */
@Component
public class CustomerManager {

    @Resource
    private TClueMapper clueMapper;

    @Resource
    private TCustomerMapper customerMapper;

    //其他也要加事务管理（除查询）
    @Transactional(rollbackFor = Exception.class)
    public Boolean convertCustomer(CustomerQuery customerQuery) {
        //验证是否已经转过客户，转过就不转了
        TClue tClue = clueMapper.selectByPrimaryKey(customerQuery.getClueId());
        if (tClue.getState() == -1){
            throw new RuntimeException("该线索已经转过客户，不能再转了");
        }
        //向客户表插入一条数据
        TCustomer tCustomer = new TCustomer();
        BeanUtils.copyProperties(customerQuery, tCustomer);
        tCustomer.setCreateBy(JWTUtils.parseUserFromJWT(customerQuery.getToken()).getId());
        tCustomer.setCreateTime(new Date());
        int insert = customerMapper.insertSelective(tCustomer);
        //把线索表里的线索状态改为-1（已转客户）
        tClue.setState(-1);
        int update = clueMapper.updateByPrimaryKeySelective(tClue);
        return insert >=1 && update >=1;
    }
}
