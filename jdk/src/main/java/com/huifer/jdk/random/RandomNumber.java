package com.huifer.jdk.random;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *随机数字
 */
public class RandomNumber {
    /**
     * 数字长度
     */
    public static final int NUMB_LENGTH = 5;


    private final static int delta = 0x9fa5 - 0x4e00 + 1;

    private static Random ran = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            List<Integer> x = randomInt();
            System.out.println(toString(x));
//            System.out.println("修改后: " + toString(setIndexNum(x, 1, 3)));
        }
    }

    private static String toString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();

        for (Integer integer : list) {
            sb.append(integer);
        }
        return "当前兑奖值=" + sb.toString();
    }

    private static List<Integer> setIndexNum(List<Integer> list, int index, int value) {
        list.set(index, value);
        return list;
    }


    /**
     * 随机数字
     * @return
     */
    private static List<Integer> randomInt() {
        // 随机次数
        long prime = createPrime();
        List<Long> longs = new ArrayList<>(NUMB_LENGTH);

        if (prime > NUMB_LENGTH) {
            List<String> stringList = randomString(Long.valueOf(prime).intValue());
            String toMD5 = stringToMD5(listString2String(stringList));
            int size = size(toMD5);
            for (int i = 0; i < size; i++) {
                try {

                    int i1 = ran.nextInt(stringList.size()) - 1;
                    String s = stringList.get(i1);
                    long l = s.hashCode() << 11 | 123 * 13 >> 3;
                    longs.add(l);
                }
                catch (Exception e) {

                }
            }
        }


        return gInt(longs);

    }


    private static int size(String toMD5) {
        if (toMD5.hashCode() % 3 == 1) {
            return 23;
        }
        else {
            return 13;
        }
    }

    private static List<Integer> gInt(List<Long> longs) {
        List<Integer> list = new ArrayList<>(NUMB_LENGTH);

        for (Long aLong : longs) {
            int i = aLong.hashCode();
            int anInt = getInt(i);
            if (list.size() == NUMB_LENGTH) {
                break;
            }
            else {
                String intString = String.valueOf(anInt);
                if (intString.length() > 1) {
                    String[] split = intString.split("");
                    list.add(Integer.parseInt(split[ran.nextInt(split.length)]));
                }
                else {
                    list.add(anInt);
                }
            }
        }
        return list;
    }

    /**
     * 获取一个数字,长度1
     * @param i
     * @return
     */
    private static int getInt(int i) {
        String s = String.valueOf(i);
        char[] chars = s.toCharArray();
        int i1 = ran.nextInt(chars.length);
        return chars[i1];
    }

    /**
     * 出md5
     * @param plainText
     * @return
     */
    private static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    /**
     * 随机n个中文字
     * @return
     */
    private static List<String> randomString(int n) {
        List<String> result = new ArrayList<>(n);

        while (true) {
            String ranHan = String.valueOf(getRandomHanChar());
            if (Math.floorMod(ranHan.hashCode(), createPrime()) % 3 == 1) {
                if (result.size() == n) {
                    break;
                }
                else {
                    result.add(ranHan);
                }
            }

        }
        return result;
    }

    /**
     * 随机1个中文字
     * @return
     */
    private static char getRandomHanChar() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    /**
     * 随机质数
     */
    private static long createPrime() {
        int max = 49999, min = 10;
        long ranNum = ran.nextInt(max - min + 1) + min;
        if (isPrime(ranNum) == 1) {
            return ranNum;
        }
        return createPrime();
    }

    /**
     * 是否质数
     * @param num 数字
     * @return 1: 是,0: 不是
     */
    private static int isPrime(long num) {
        for (int a = 2; a < Math.sqrt(num); a++) {
            if ((Math.pow(a, num - 1) % num) == 1) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * StringList to string
     * @param strings
     * @return
     */
    private static String listString2String(List<String> strings) {
        StringBuilder sb = new StringBuilder(32);
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

}