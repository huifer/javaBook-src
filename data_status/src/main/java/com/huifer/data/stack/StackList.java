package com.huifer.data.stack;

/**
 * <p>Title : StackList </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class StackList<E> {


    /**
     * 数据存储数组
     */
    private Object[] elements = null;
    /**
     * 栈容量
     */
    private int maxSize = 0;
    /**
     * 当前栈顶指针位置
     */
    private int top = -1;


    public StackList() {
        this(10);
    }

    public StackList(int size) {
        if (size >= 0) {
            this.maxSize = size;
            elements = new Object[size];
            top = -1;
        } else {
            throw new RuntimeException("初始化长度需要大于等于0");
        }
    }

    public static void main(String[] args) {
        StackList<Integer> stackList = new StackList<>();
        stackList.push(1);
        stackList.push(2);
        stackList.push(3);
        stackList.print();
        Integer pop = stackList.pop();
        stackList.print();
    }

    public void print() {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {

            System.out.print(elements[i] + "\t");
            }

        }
        System.out.println();
    }

    /**
     * 入栈
     */
    public boolean push(E e) {
        if (top == maxSize - 1) {
            throw new RuntimeException("满了");
        } else {
            elements[++top] = e;
            return true;
        }
    }

    /**
     * 查看栈顶元素
     */
    public E peek() {
        if (top == -1) {
            throw new RuntimeException("栈为空");
        } else {
            return (E) elements[top];
        }
    }

    /**
     * 弹栈
     */
    public E pop() {
        if (top == -1) {
            throw new RuntimeException("栈为空");
        } else {
            return (E) elements[top--];
        }
    }

    /**
     * 查询索引
     */
    public int search(E e) {
        int i = top;
        while (top != -1) {
            if (peek() != e) {
                top--;
            } else {
                break;
            }
        }
        int res = top + 1;
        top = i;
        return res;
    }

    public boolean empty() {
        return top == -1;
    }
}
