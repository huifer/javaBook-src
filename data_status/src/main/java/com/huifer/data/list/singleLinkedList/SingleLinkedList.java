package com.huifer.data.list.singleLinkedList;


/**
 * <p>Title : SingleLinkedList </p>
 * <p>Description : 单向链表</p>
 *
 * @author huifer
 * @date 2019-04-16
 */
public class SingleLinkedList {

    /**
     * 头结点
     */
    private SingleNode head;

    /**
     * 尾结点
     */
    private SingleNode tail;


    private int length;

    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();
        s.insertLast(1);
        s.insertLast(2);
        s.insertLast(3);
        s.inster(100, 1);
//        SingleNode singleNode = s.deleteFirst();
//        SingleNode singleNode = s.deleteLast();

//        SingleNode singleNode = s.delete(1);
        SingleNode singleNode = s.get(1);
        System.out.println(singleNode);
        s.print();
    }


    /**
     * 输出所有数据
     */
    public void print() {
        if (isEmpty()) {
            return;
        }
        SingleNode cur = head;
        while (cur != null) {
            System.out.print(cur.data + "\t");
            cur = cur.next;
        }
        System.out.println();
    }


    /**
     * 根据索引获取数据
     * @param position 索引
     * @return
     */
    public SingleNode get(int position) {
        int i = 0;
        SingleNode cur = head;
        SingleNode rs = null;
        while (cur != null) {
            if (i == position) {
                cur = cur.next;
                rs = cur;
                break;
            }
            i++;
        }
        return rs;
    }


    /**
     * 结点是否空
     */
    public boolean isEmpty() {
        return length == 0;
    }

    public int getLength() {
        return length;
    }

    /**
     * 向链表的最后追加一个结点
     */
    public SingleNode insertLast(int data) {
        SingleNode singleNode = new SingleNode(data);
        if (isEmpty()) {
            // 当链表是空的时候头尾都是本身
            head = singleNode;
            tail = singleNode;
        } else {
            // 尾部追加
            tail.next = singleNode;
            tail = singleNode;
        }
        length++;
        return singleNode;
    }

    /**
     * 头部插入
     */
    public SingleNode inertFirst(int data) {
        SingleNode singleNode = new SingleNode(data);
        SingleNode lastSingleNode;
        lastSingleNode = head;
        head = singleNode;
        head.next = lastSingleNode;
        length++;
        return singleNode;
    }

    /**
     * 指定位置插入数据
     *
     * @param data 数据
     * @param position 指定索引
     */
    public SingleNode inster(int data, int position) {
        if (position < 0) {
            throw new IndexOutOfBoundsException();
        }
        SingleNode singleNode = new SingleNode(data);
        if (position == 0) {
            // 第一个位置插入
            inertFirst(data);
        } else if (isEmpty() || position >= getLength()) {
            // 最后一个位置插入
            insertLast(data);
        } else {
            // 中间部分插入
            // node的上一个结点
            SingleNode cur = head;
            // singleNode 的下一个结点
            SingleNode nextSingleNode = cur.next;
            for (int i = 1; i < getLength(); i++) {
                if (i == position) {
                    // 遍历结点 当i等于输入的目标位置后
                    // 上一个结点指向node
                    cur.next = singleNode;
                    // singleNode 的下一个结点只想 cur的下一个
                    singleNode.next = nextSingleNode;
                } else {
                    // 节点更新
                    cur = cur.next;
                    nextSingleNode = cur.next;
                }
            }
            // 头尾追加都有length++
            length++;
        }

        return singleNode;
    }

    public SingleNode getHead() {
        return head;
    }

    public SingleNode getTail() {
        return tail;
    }

    /**
     * 删除头结点
     */
    public SingleNode deleteFirst() {
        if (isEmpty()) {
            throw new RuntimeException("没有节点 不能进行删除操作");
        }
        // 删除节点等于头
        SingleNode deleteSingleNode = head;
        // 头结点等于后续
        head = deleteSingleNode.next;
        length--;
        return deleteSingleNode;
    }

    /**
     * 从最后一个结点开始删除
     */
    public SingleNode deleteLast() {
        SingleNode deleteSingleNode = tail;
        if (isEmpty()) {
            throw new RuntimeException("没有节点 不能进行删除操作");
        }
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            SingleNode lastSingleNode = head;
            // 根据单向链表的描述，最后一个节点的指针域为null 作为结束
            while (lastSingleNode.next != tail) {
                lastSingleNode = lastSingleNode.next;
            }
            tail = lastSingleNode;
            tail.next = null;
        }
        length--;
        return deleteSingleNode;
    }

    /**
     * 指定位置删除
     *
     * @param position 索引
     */
    public SingleNode delete(int position) {
        if (isEmpty()) {
            throw new RuntimeException("没有节点 不能进行删除操作");
        }
        if (position < 0 || position > getLength() - 1) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        if (position == 0) {
            return deleteFirst();
        } else if (position == getLength() - 1) {
            return deleteLast();
        } else {
            // 上一个元素
            SingleNode lastSingleNode = head;
            // 当前元素
            SingleNode cur = lastSingleNode.next;
            // 下一个元素
            SingleNode nextSingleNode = cur.next;

            for (int i = 1; i < getLength(); i++) {
                if (i == position) {
                    lastSingleNode.next = nextSingleNode;
                    break;
                } else {
                    lastSingleNode = cur;
                    cur = nextSingleNode;
                    nextSingleNode = nextSingleNode.next;
                }
            }
            length--;
            return cur;

        }

    }


    /**
     * 删除指定数据
     */
    public Integer deleteByData(int data) {
        if (isEmpty()) {
            throw new RuntimeException("没有节点 不能进行删除操作");

        }

        if (head.data == data) {
            deleteFirst();
            return data;
        } else if (tail.data == data) {
            deleteLast();
            return data;
        } else {
            // 上一个元素
            SingleNode lastSingleNode = null;
            // 当前元素
            SingleNode cur = head;
            // 下一个元素
            SingleNode nextSingleNode = cur.next;

            while (cur != null) {
                if (cur.data == data) {
                    lastSingleNode.next = nextSingleNode;
                    length--;
                    return data;
                }
                if (nextSingleNode == null) {
                    return null;
                } else {
                    lastSingleNode = cur;
                    cur = nextSingleNode;
                    nextSingleNode = nextSingleNode.next;
                }
            }

        }
        return null;


    }


}
