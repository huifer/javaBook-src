package com.huifer.design.event;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Title : GUIEvent </p>
 * <p>Description : GUI监听</p>
 *
 * @author huifer
 * @date 2019-06-03
 */
public class GUIEvent {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("GUI监听事件");
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.printf("[%s]事件：%s\n", Thread.currentThread().getName(), e);
            }
        });

        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jFrame.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        });

        jFrame.setBounds(300, 300, 400, 300);
        jFrame.setVisible(true);
    }

}
