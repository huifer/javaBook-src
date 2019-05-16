package com.huifer.design.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <p>Title : Prototype </p>
 * <p>Description : 原型模式</p>
 *
 * @author huifer
 * @date 2019-05-16
 */
public class Prototype implements Cloneable, Serializable {


    private static final long serialVersionUID = -2616767906128391875L;
    public String name;
    public Tag tag;

    public Prototype() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
        return deepClone();
    }


    public Object deepClone() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            Prototype copyObject = (Prototype) ois.readObject();
            return copyObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
