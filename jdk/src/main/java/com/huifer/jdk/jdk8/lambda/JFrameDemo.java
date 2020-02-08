package com.huifer.jdk.jdk8.lambda;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-12
 */
public class JFrameDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("jf");
        JButton button = new JButton("button");


        button.addActionListener(e -> {
            // 开发人员真正关注的内容
            System.out.println("button click");

        });

        jf.add(button);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
