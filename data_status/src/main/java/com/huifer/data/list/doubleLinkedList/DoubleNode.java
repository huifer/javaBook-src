package com.huifer.data.list.doubleLinkedList;

/**
 * <p>Title : DoubleNode </p>
 * <p>Description : 双线循环链表的结点</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class DoubleNode {

    /**
     * 前一个结点
     */
    public DoubleNode prev = null;
    /**
     * 后一个结点
     */
    public DoubleNode next = null;

    /**
     * 数据
     */
    public Integer data;

    public DoubleNode() {
    }

    public DoubleNode(Integer data, DoubleNode prev, DoubleNode next) {

        this.prev = prev;
        this.next = next;
        this.data = data;
    }
}
