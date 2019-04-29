package com.huifer.data.string;

/**
 * <p>Title : Kmp </p>
 * <p>Description : kmp</p>
 *
 * @author huifer
 * @date 2019-04-23
 */
public class Kmp {


    public static void main(String[] args) {
        char[] pattern = "ABABCABAA".toCharArray();
        int n = pattern.length;
        int[] prefix = new int[n];
        prefixTable(pattern, prefix, n);
        movePrefixTable(prefix, n);
        kmpSearch("acABABCABAA".toCharArray(), pattern);
        System.out.println();
    }

    private static void kmpSearch(char[] text, char[] pattern) {
        int n = pattern.length-1;
        int[] prefix = new int[n];
        prefixTable(pattern, prefix, n);
        movePrefixTable(prefix, n);
        // text [i]  len(text) = m
        // pattern[j] len(pattern) = n
        int m = text.length-1;
        int i = 0;
        int j = 0;
        while (i < m ) {

            if (j == n - 1 && text[i] == pattern[j]) {
                System.out.println("匹配位置：" + (i - j));
                j = prefix[j];
            }
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                j = prefix[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }


    }

    /**
     * 前缀表后移一位
     */
    private static void movePrefixTable(int[] prefix, int n) {
        for (int i = n - 1; i > 0; i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
    }

    /**
     * 前缀表
     */
    private static void prefixTable(char[] pattern, int[] prefix, int n) {
        prefix[0] = 0;
        int len = 0;
        int i = 1;
        while (i < n) {
            if (pattern[i] == pattern[len]) {
                len++;
                prefix[i] = len;
                i++;
            } else {
                if (len > 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }
    }
}
