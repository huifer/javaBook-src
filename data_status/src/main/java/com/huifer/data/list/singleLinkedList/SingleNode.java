package com.huifer.data.list.singleLinkedList;

/**
 * <p>Title : SingleNode </p>
 * <p>Description : 单向链表结点</p>
 *
 * @author huifer
 * @date 2019-04-16
 */
public class SingleNode {

    /**
     * 数据
     */
    public int data;
    /**
     * 下一个结点
     */
    public SingleNode next = null;


    public SingleNode(int data, SingleNode next) {
        this.data = data;
        this.next = next;
    }

    public SingleNode() {
    }

    public SingleNode(int data) {
        this.data = data;
    }
}
