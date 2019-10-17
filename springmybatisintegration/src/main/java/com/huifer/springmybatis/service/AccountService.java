package com.huifer.springmybatis.service;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-10
 */
public interface AccountService {
    void transfer(String from, String to, double money);
}
