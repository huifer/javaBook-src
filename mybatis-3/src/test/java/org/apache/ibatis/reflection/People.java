package org.apache.ibatis.reflection;

import java.util.List;

public class People {
    private String name;

    public People() {
    }

    public People(String name) {
        this.name = name;
    }

    public List<String> getlist() {
        return null;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
