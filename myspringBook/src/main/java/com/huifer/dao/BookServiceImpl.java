package com.huifer.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-09
 */
@Service(value = "bookService")
//@Transactional 事务注解
public class BookServiceImpl implements BookService {
    @Resource
    private BookLibDao bookLibDao;

    @Override
    public void loanBook(String from, String to, String bname) {
        bookLibDao.update(bname, from, to);

    }
}
