package com.huifer.secweb.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 描述:
 *
 * @author: huifer
 * @date: 2019-11-10
 */
public class MainController {
    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        String str = b.encode("123");
        System.out.println("Encoding " + str);
    }
}
