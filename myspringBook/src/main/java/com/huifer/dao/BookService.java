package com.huifer.dao;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-09
 */
public interface BookService {
    /**
     * 借书
     * @param from 从xxx借出
     * @param to 借给xxx
     * @param bname 书名
     */
    void loanBook(String from, String to, String bname);
}
