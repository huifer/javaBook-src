package com.huifer.bilibili.redis.entity;

import com.alibaba.fastjson.JSON;
import com.huifer.bilibili.redis.base.StringToEntity;

import java.math.BigDecimal;
import java.util.Objects;

public class BookEntity implements StringToEntity<BookEntity> {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{\"BookEntity\":{"
                + "\"id\":"
                + id
                + ",\"name\":\""
                + name + '\"'
                + ",\"price\":"
                + price
                + ",\"count\":"
                + count
                + "}}";

    }

    public BookEntity convert(String s) {
        return JSON.parseObject(s, BookEntity.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, count);
    }
}
