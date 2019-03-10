package com.huifer.springmybatis.service;

import com.huifer.springmybatis.mapper.AccountMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-10
 */
@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper mapper;
    @Override
    public void transfer(String from, String to, double money) {
        double fromMoney = mapper.queryMoney(from);
        mapper.update(from, fromMoney - money);
        double toMoney = mapper.queryMoney(to);
        mapper.update(to, toMoney + money);
    }
}
