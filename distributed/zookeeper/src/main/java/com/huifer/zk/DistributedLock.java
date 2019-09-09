package com.huifer.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * <p>Title : DistributedLock </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-12
 */
public class DistributedLock implements Lock, Watcher {

    /**
     * zk客户端
     */
    private ZooKeeper zk = null;
    /**
     * 根节点
     */
    private String root_lock = "/locks";
    /**
     * 前一个锁
     */
    private String wait_lock;
    /**
     * 当前锁
     */
    private String current_lock;

    private CountDownLatch countDownLatch;

    public DistributedLock() {
        try {
            zk = new ZooKeeper("192.168.1.107:2181", 4000, this);
            // 判断根节点是否存在
            Stat stat = zk.exists(root_lock, false);
            if (stat == null) {
                // 不存在创建节点
                zk.create(root_lock, "0".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (this.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "-->>" + current_lock + " lock获得锁");
            return;
        }
        waitForLock(wait_lock);
    }

    /**
     * 没有锁持续等待
     */
    private boolean waitForLock(String prev) {
        try {
            Stat stat = zk.exists(prev, true);
            if (stat != null) {
                System.out.println(
                        Thread.currentThread().getName() + "-->>" + prev
                                + " 等待锁");
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();

                System.out.println(Thread.currentThread().getName() + "-->" + prev + " 所获得成功");

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        try {
            // 临时有序节点
            current_lock = zk.create(root_lock + "/", "0".getBytes(), Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread().getName() + "-->>" + current_lock + " 参与竞争");

            // 根节点下的所有子节点
            List<String> children = zk.getChildren(root_lock, false);

            SortedSet<String> sortedSet = new TreeSet<>();
            for (String child : children) {
                sortedSet.add(root_lock + "/" + child);
            }

            // 最小节点
            String firstNode = sortedSet.first();
            // 判断是否最小
            SortedSet<String> lessThenMe = sortedSet.headSet(current_lock);
            if (current_lock.equals(firstNode)) {
                return true;
            }
            if (!lessThenMe.isEmpty()) {
                // 获取当前节点更小的一个节点
                wait_lock = lessThenMe.last();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        System.out.println(Thread.currentThread().getName() + "-->> 释放锁 " + current_lock);
        try {
            zk.delete(current_lock, -1);
            current_lock = null;
            zk.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
