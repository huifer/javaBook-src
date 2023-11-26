package com.huifer.data.list.doubleLinkedList;

/**
 * <p>Title : DoubleLinkedList </p>
 * <p>Description : 双向循环链表</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class DoubleLinkedList {

    private DoubleNode first;
    private DoubleNode last;
    private int size = 0;

    public static void main(String[] args) {
        DoubleLinkedList d = new DoubleLinkedList();
        d.add(0);
        d.add(1);
        d.add(2);

        d.add(1, 999);
        d.delete(999);
        System.out.println();
    }

    public void add(Integer data) {
        addLast(data);
    }


    public boolean delete(Integer data) {
        if (data == null) {
            throw new RuntimeException("参数不能空");
        } else {
            for (DoubleNode d = first; d != null; d = d.next) {
                if (d.data.equals(data)) {
                    deleteNode(d);
                    return true;
                }
            }
        }
        return false;
    }

    private Integer deleteNode(DoubleNode d) {
        DoubleNode next = d.next;
        DoubleNode prev = d.prev;

        if (prev == null) {
            first = next;
        } else {
            // 当前结点上一个结点等与当前结点的下一个
            prev.next = next;
            d.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            // 当前结点的下一个结点等与当前结点的上一个结点
            next.prev = prev;
            d.next = null;
        }

        d.data = null;
        size--;
        return d.data;
    }


    public DoubleNode get(int index) {
        return node(index);
    }


    private void addLast(Integer data) {
        // 原来的最后一个结点
        DoubleNode l = this.last;
        // 新增节点 ，新增节点的上一个结点是最后一个 没有下一个结点
        DoubleNode newNode = new DoubleNode(data, l, null);
        last = newNode;
        if (l == null) {
            // 如果最后一个结点等于空 那么只有一个结点 第一个结点等于新节点
            first = newNode;
        } else {
            // 否则l的下一个结点等于新节点
            l.next = newNode;
        }
        size++;
    }


    public void add(int index, Integer data) {
        if (!(index >= 0 && index <= size)) {
            throw new IndexOutOfBoundsException();
        }
        if (size == index) {
            addLast(data);
        } else {
            addbefor(data, node(index));
        }

    }


    private void addbefor(Integer data, DoubleNode node) {
        // 输入结点的上一个结点
        DoubleNode pred = node.prev;
        // 新节点构造   (数据， 上一个结点是输入结点的下一个，下一个结点时输入结点)
        DoubleNode newNode = new DoubleNode(data, pred, node);
        // 输入结点的下一个结点时新结点
        node.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }


    private DoubleNode node(int index) {
        // 一半一半的去查询找到这个结点
        if (index < (size >> 1)) {
            // 当index这个值小于总量的一半从头查询 反之从尾部开始
            DoubleNode d = first;
            for (int i = 0; i < index; i++) {
                d = d.next;
            }
            return d;
        } else {
            DoubleNode d = this.last;
            for (int i = size - 1; i < index; i--) {
                d = d.prev;
            }
            return d;
        }
    }


}
