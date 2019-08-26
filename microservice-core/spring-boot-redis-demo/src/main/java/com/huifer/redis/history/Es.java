package com.huifer.redis.history;

public class Es {
    /**
    * 自增长列
    */
    private Long selfPro;

    /**
    * id
    */
    private Long id;

    /**
    * random
    */
    private String name;

    public Long getSelfPro() {
        return selfPro;
    }

    public void setSelfPro(Long selfPro) {
        this.selfPro = selfPro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}