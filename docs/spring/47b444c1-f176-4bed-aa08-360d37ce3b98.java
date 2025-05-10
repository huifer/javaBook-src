package com.huifer.concurrence.ch2.livelock;

/**
 * <p>Title : People </p>
 * <p>Description : 人</p>
 *
 * @author huifer
 * @date 2019-03-27
 */
public class People {

    private String name;
    /**
     * 动作：是否给别人
     */
    private boolean active;

    public People(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"active\":")
                .append(active);
        sb.append('}');
        return sb.toString();
    }

    public synchronized void doWork(Apple apple, People otherPeople) {
        while (active) {
            if (apple.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                }
                continue;
            }

            if (otherPeople.isActive()) {
                System.out.println("苹果当前所有者" + apple.getOwner());

                System.out.println(getName() + " 把苹果交给 ： " +
                        otherPeople.getName());
                apple.setOwner(otherPeople);
                continue;
            }

            System.out.println(getName() + ": 吃苹果");
            active = false;
            apple.setOwner(otherPeople);
        }

    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
