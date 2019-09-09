package com.huifer.data.queue;

/**
 * <p>Title : LinkedQueue </p>
 * <p>Description : 队列的链表实现</p>
 *
 * @author huifer
 * @date 2019-04-22
 */
public class LinkedQueue<E> {

    /**
     * 头指针
     */
    private Node<E> first;
    /**
     * 尾指针
     */
    private Node<E> last;
    private int qSize;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.qSize = 0;

    }

    public static void main(String[] args) {
        LinkedQueue<String> l = new LinkedQueue<>();
        l.add("元素1");
        l.add("元素2");
        l.add("元素3");
        l.add("元素4");
        String delete = l.delete();
        delete = l.delete();
        delete = l.delete();
        delete = l.delete();
        System.out.println();
    }

    /**
     * 判空
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 获取头
     */
    public Node peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return first;
    }

    /**
     * 删除元素 头部删除
     */
    public E delete() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        E data = first.data;
        first = first.next;

        if (isEmpty()) {
            last = null;
        }

        qSize--;
        return data;
    }


    /**
     * 添加元素 尾部追加
     */
    public void add(E data) {
        Node old = last;
        last = new Node<>();
        last.data = data;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            old.next = last;
        }
        qSize++;
    }

    private class Node<E> {

        Node<E> next;
        E data;

    }


}
