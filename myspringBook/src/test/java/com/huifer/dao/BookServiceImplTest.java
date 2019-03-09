package com.huifer.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_db_config.xml"})
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookLibDao bookLibDao;

    @Test
    public void loanBook() {
        bookService.loanBook("张三", "wa", "java Book");
        List<Book> query = bookLibDao.query();
        System.out.println(query);
    }
}