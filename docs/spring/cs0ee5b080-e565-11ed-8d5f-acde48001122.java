package com.huifer.data.stack;

/**
 * <p>Title : StackLinkedList </p>
 * <p>Description : 栈的链式存储</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class StackLinkedList<E> {


    /**
     * 栈顶元素
     */
    private Node<E> top;
    /**
     * 栈大小
     */
    private int size;

    /**
     * 初始化
     */
    public StackLinkedList() {
        top = null;
    }

    public static void main(String[] args) {
        StackList<String> stringStackList = new StackList<>();
        stringStackList.push("01");
        stringStackList.push("02");
        String peek = stringStackList.peek();
        System.out.println(peek);
        String pop = stringStackList.pop();
        System.out.println();
    }

    /**
     * 当前的栈大小
     */
    public int length() {
        return size;
    }

    /**
     * 判空
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * 入栈
     */
    public boolean push(E e) {
        top = new Node<>(e, top);
        size++;
        return true;
    }

    /**
     * 查看栈顶
     */
    public Node<E> peek() {
        if (empty()) {
            throw new RuntimeException("空栈");
        } else {
            return top;
        }
    }

    /**
     * 出栈
     */
    public Node<E> pop() {
        if (empty()) {
            throw new RuntimeException("空栈");
        } else {
            // 栈顶临时变量
            Node<E> topNode = top;
            // 栈顶指向下一个元素
            top = top.next;
            // 栈顶清空
            topNode.next = null;
            size--;
            return topNode;

        }
    }

    /**
     * 栈的链式存储结点类
     */
    private class Node<E> {

        E e;
        Node<E> next;

        public Node() {
        }

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }
    }
}
