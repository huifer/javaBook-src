package com.huifer.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {

        return
                new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("bname"),
                        resultSet.getString("belone"),
                        resultSet.getString("toname")
                );
    }
}