package com.huifer.bigfile.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * <p>Title : MD5Utils </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-05-27
 */
public class MD5Utils {

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
