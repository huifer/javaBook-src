package com.huifer.dao;

import java.util.List;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-03-09
 */
public interface BookLibDao {
    void update(String bname, String belone, String to);

    List<Book> query();
}
