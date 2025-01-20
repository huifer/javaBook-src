package com.huifer.design.decorate.login;

import java.util.Objects;

/**
 * <p>Title : Menber </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-21
 */
public class Menber {

    private String name;
    private String pwd;

    public Menber(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"pwd\":\"")
                .append(pwd).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Menber)) {
            return false;
        }
        Menber menber = (Menber) o;
        return Objects.equals(getName(), menber.getName()) &&
                Objects.equals(getPwd(), menber.getPwd());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPwd());
    }
}
