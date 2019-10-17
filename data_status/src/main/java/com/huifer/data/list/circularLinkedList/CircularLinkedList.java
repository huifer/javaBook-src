package com.huifer.data.list.circularLinkedList;

/**
 * <p>Title : CircularLinkedList </p>
 * <p>Description : 循环链表</p>
 *
 * @author huifer
 * @date 2019-04-18
 */
public class CircularLinkedList {


    private CircularNode head;


    public CircularLinkedList() {
        head = new CircularNode();
        head.data = null;
        head.next = head;
    }

    public static void main(String[] args) {
        CircularLinkedList c = new CircularLinkedList();
        c.add(1);
        c.add(2);
        c.add(3);
        c.printall();

        c.delete(2);
        c.printall();

        CircularNode node = c.get(1);
        System.out.println(node);

    }

    public void add(Integer data) {
        CircularNode node = new CircularNode(data);
        if (head.next == head) {
            head.next = node;
            // 最后一个结点的指针域指向头
            node.next = head;
        } else {
            // // 找到最后一个元素进行追加
            CircularNode tem = head;
            while (tem.next != head) {
                tem = tem.next;
            }
            tem.next = node;
            node.next = head;
        }
    }

    public void delete(Integer data) {
        CircularNode tem = head;
        while (tem.next != head) {
            // 判断tem当前指向的结点数据是否和输入数据一样
            if (tem.next.data.equals(data)) {
                // 删除结点
                tem.next = tem.next.next;
            } else {
                // next指针后移
                tem = tem.next;
            }
        }
    }

    public CircularNode get(int i) {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException();
        } else {
            int count = 0;
            CircularNode node = new CircularNode();
            CircularNode tem = head;
            while (tem.next != head) {
                count++;
                if (count == i) {
                    node.data = tem.next.data;
                }
                tem = tem.next;
            }
            return node;
        }
    }

    public int size() {
        CircularNode tem = head;
        int size = 0;
        // 当tem的指针域指向了head说明到了最后一个结点
        while (tem.next != head) {
            size++;
            tem = tem.next;
        }
        return size;
    }

    public void printall() {
        System.out.println("循环链表");
        CircularNode node = head;
        while (node.next != head) {

            node = node.next;
            System.out.print(node.data + "\t");

        }
        System.out.println();
    }
}
