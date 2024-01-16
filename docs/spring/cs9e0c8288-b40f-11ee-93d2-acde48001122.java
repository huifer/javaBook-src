package com.huifer.data.list.staticLinkedList;

/**
 * <p>Title : StaticNode </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-04-17
 */
public class StaticNode {

    public Integer data;

    public int cur;

    public StaticNode(Integer data) {
        this.data = data;
    }

    public StaticNode(int cur) {
        this.cur = cur;
    }

    public StaticNode() {
    }

    public StaticNode(Integer data, int cur) {
        this.data = data;
        this.cur = cur;
    }
}
