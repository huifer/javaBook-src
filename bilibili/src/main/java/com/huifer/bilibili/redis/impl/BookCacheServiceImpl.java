package com.huifer.bilibili.redis.impl;

import com.alibaba.fastjson.JSON;
import com.huifer.bilibili.redis.BookCacheService;
import com.huifer.bilibili.redis.JedisFactory;
import com.huifer.bilibili.redis.entity.BookEntity;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BookCacheServiceImpl implements BookCacheService {
    public static final String BOOK_CACHE_KEY = "BOOK_CACHE_KEY";
    Jedis conn = JedisFactory.conn();

    public static void main(String[] args) {
        BookCacheServiceImpl bookCacheService = new BookCacheServiceImpl();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(1);
        bookEntity.setName("11111111111111111");
        bookEntity.setPrice(new BigDecimal("0"));
        bookEntity.setCount(0);

        bookCacheService.update(bookEntity);
//        bookCacheService.delete(bookEntity);
    }

    public void add(BookEntity bookEntity) {
        conn.lpush(BOOK_CACHE_KEY, bookEntity.toJson());
    }

    public BookEntity delete(BookEntity bookEntity) {
        int i;
        List<BookEntity> all = this.getAll();
        for (int i1 = 0; i1 < all.size(); i1++) {
            BookEntity entity = all.get(i1);
            if (bookEntity.equals(entity)) {
                i = i1;
                break;
            }
        }
        conn.lrem(BOOK_CACHE_KEY, 0, bookEntity.toJson());
        return bookEntity;
    }

    public BookEntity update(BookEntity bookEntity) {
        int index = getIndex(bookEntity);
        if (index > 0) {
            conn.lset(BOOK_CACHE_KEY, index, bookEntity.toJson());
            return bookEntity;
        }
        return null;
    }


    private int getIndex(BookEntity bookEntity) {
        for (int i = 0; i < getAll().size(); i++) {
            if (getAll().get(i).getId().equals(bookEntity.getId())) {
                return i;
            }
        }
        return -1;
    }

    public List<BookEntity> getAll() {
        List<String> range = conn.lrange(BOOK_CACHE_KEY, 0, -1);
        return range.stream().map(s -> JSON.parseObject(s, BookEntity.class)).collect(Collectors.toList());
    }
}
