package com.huifer.data.queue;

/**
 * <p>Title : RoundRobinQueue </p>
 * <p>Description : 循环队列</p>
 *
 * @author huifer
 * @date 2019-04-22
 */
public class RoundRobinQueue {

    private Object[] queue;
    private int queueSize;
    // 头指针
    private int front;
    // 尾指针
    private int rear;


    public RoundRobinQueue(int queueSize) {
        this.queueSize = queueSize;
        this.front = 0;
        this.rear = 0;
        this.queue = new Object[queueSize];
    }

    public static void main(String[] args) {
        RoundRobinQueue r = new RoundRobinQueue(3);
        r.enqueue("元素1");
        r.enqueue("元素2");
        // 队列满
        r.enqueue("元素3");
        Object dequeue = r.dequeue();
        System.out.println(dequeue.equals("元素1"));
    }


    /**
     * 是否满队列
     */
    public boolean isFull() {
        return (rear + 1) % queue.length == front;
    }

    public boolean enqueue(Object obj) {
        if (isFull()) {
            return false;
        }
        queue[rear] = obj;
        rear = (rear + 1) % queue.length;
        return true;
    }


    public Object dequeue() {
        if (rear == front) {
            return null;
        }
        Object obj = queue[front];
        front = (front + 1) % queue.length;
        return obj;
    }


}
