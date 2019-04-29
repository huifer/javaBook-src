package com.huifer.data.list.staticLinkedList;


/**
 * <p>Title : StaticLinkedList </p>
 * <p>Description : 静态链表</p>
 *
 * @author huifer
 * @date 2019-04-17
 */
public class StaticLinkedList {

    private static final int maxSize = 10;
    private StaticNode[] staticNodes = null;

    private int length;

    public StaticLinkedList() {
        this(maxSize);
    }

    public StaticLinkedList(int maxSize) {
        staticNodes = new StaticNode[maxSize];
        for (int i = 0; i < maxSize - 1; i++) {
            staticNodes[i] = new StaticNode(null, i + 1);
        }

        staticNodes[maxSize - 1] = new StaticNode(null, 0);

        this.length = 0;
    }


    public static void main(String[] args) {

        StaticLinkedList staticLinkedList = new StaticLinkedList();
        staticLinkedList.add(999, 1);
        staticLinkedList.add(777, 2);


        staticLinkedList.printAll();

        System.out.println();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == maxSize;
    }

    /**
     * 插入数据
     */
    public void insert(Integer data) {
        int t = staticNodes[maxSize - 1].cur;
        int first = staticNodes[0].cur;

        staticNodes[maxSize - 1].cur = first;
        staticNodes[0].cur = staticNodes[first].cur;

        staticNodes[first].cur = t;
        staticNodes[first].data = data;
        length++;
    }

    public boolean add(Integer data, int index) {
        if (isFull() || index > maxSize || index < -1 || data == null) {
            return false;
        }
        if (index == 0) {
            insert(data);
            return true;
        }
        if (index > length) {
            index = length;
        }

        int firstUser = staticNodes[maxSize - 1].cur;
        int firstNull = staticNodes[0].cur;

        for (int i = 1; i < index; i++) {
            firstUser = staticNodes[firstUser].cur;
        }

        int nextUser = staticNodes[firstUser].cur;
        int nextNull = staticNodes[firstNull].cur;

        staticNodes[0].cur = nextNull;
        staticNodes[firstUser].cur = firstNull;
        staticNodes[firstNull].cur = nextUser;
        staticNodes[firstNull].data = data;
        return true;
    }

    public boolean deleteByData(Integer data) {
        if (isEmpty()) {
            return false;
        }
        int temp = maxSize - 1;
        while (temp != 0) {
            if (staticNodes[staticNodes[temp].cur].data.equals(data)) {
                int p = staticNodes[temp].cur;

                staticNodes[temp].cur = staticNodes[p].cur;
                staticNodes[p].cur = staticNodes[0].cur;
                staticNodes[0].cur = p;
                staticNodes[p].data = null;
                length--;
                return true;
            }
            temp = staticNodes[temp].cur;
        }
        return false;
    }

    public boolean deleteAll() {
        if (isEmpty()) {
            return true;
        }
        for (int i = 0; i < maxSize - 1; i++) {
            staticNodes[i].cur = i + 1;
            staticNodes[i].data = null;
        }
        staticNodes[maxSize - 1].cur = 0;
        staticNodes[maxSize - 1].data = null;

        length = 0;
        return true;
    }

    public void print() {
        int first = staticNodes[maxSize - 1].cur;

        for (int i = first; i != 0; ) {
            System.out.print(staticNodes[i].data + "\t");
            i = staticNodes[i].cur;
        }

    }


    public void printAll() {

        System.out.println("链表：");
        for (int i = 0; i < maxSize; i++) {
            System.out.print("[索引：" + i + " 数据：" + staticNodes[i].data + " 下一个cur：" + staticNodes[i].cur + "]");

        }

    }

}


