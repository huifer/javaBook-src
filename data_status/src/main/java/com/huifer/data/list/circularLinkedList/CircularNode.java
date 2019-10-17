package com.huifer.data.list.circularLinkedList;

/**
 * <p>Title : CircularNode </p>
 * <p>Description : 循环链表结点</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class CircularNode {

    public Integer data;
    public CircularNode next;

    public CircularNode(Integer data) {
        this.data = data;
    }

    public CircularNode() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"data\":")
                .append(data);
        sb.append(",\"next\":")
                .append(next);
        sb.append('}');
        return sb.toString();
    }
}
