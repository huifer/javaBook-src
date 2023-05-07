package com.huifer.design.strategy.comparator;

/**
 * <p>Title : PeopleComparable </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-20
 */
public class PeopleComparable implements Comparable<PeopleComparable> {

    public double height;
    public double weight;

    public PeopleComparable() {
    }

    public PeopleComparable(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(PeopleComparable o) {
        return (int) (this.height - o.height);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"height\":")
                .append(height);
        sb.append(",\"weight\":")
                .append(weight);
        sb.append('}');
        return sb.toString();
    }
}
