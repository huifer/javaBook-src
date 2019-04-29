package com.huifer.data.string;

/**
 * <p>Title : StringBF </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-04-22
 */
public class StringBF {

    public static void main(String[] args) {
        char[] s = {'a', 'a', 'b', 'c', 'd', 'e'};
        char[] t = {'b', 'c'};

        index_bf_2(s, t);

    }

    private static void index_bf_2(char[] s, char[] t) {
        int i = 0;
        int j = 0;
        int k;
        while (i < s.length && j < t.length) {

            if (s[i] == t[j]) {
                // 主串s 和字串t匹配 指针向后移动
                ++i;
                ++j;

            } else {
                // 不能匹配 ，ij指针回溯
                i = i - j + 2;
                j = 0;
            }
        }
        if (j >= t.length) {
             k = i - t.length;
        } else {
             k = 0;
        }
        System.out.println(k);
    }


    private static int index_bf(char[] s, char[] t) {
        int i = 0;
        int j = 0;
        for (; i < s.length; ) {
            if (j >= t.length - 1) {
                break;
            }
            if (s[i] == t[j]) {
                i = i + 1;
                j = j + 1;
            } else {
                i = i + 1;
            }
        }

        int i1 = i - t.length + 1;
        return i1;
    }

}
