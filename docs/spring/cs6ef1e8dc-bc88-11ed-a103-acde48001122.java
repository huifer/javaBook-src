package com.huifer.bilibili.redis.entity.bo;

import com.huifer.bilibili.redis.entity.BookEntity;
import com.huifer.bilibili.redis.entity.UserEntity;

import java.util.List;

/**
 * 假设存在这么一个对象
 */
public class UserInfo {
    private UserEntity userEntity;
    private List<BookEntity> bookEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<BookEntity> getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(List<BookEntity> bookEntity) {
        this.bookEntity = bookEntity;
    }
}
