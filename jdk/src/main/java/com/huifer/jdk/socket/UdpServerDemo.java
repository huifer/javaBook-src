package com.huifer.jdk.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * <p>Title : UdpServerDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class UdpServerDemo {

    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket = null;
        try {
            datagramSocket = new DatagramSocket(9999);
            byte[] receiveData = new byte[1024];
            datagramPacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(datagramPacket);

            System.out.println(new String(receiveData, 0, datagramPacket.getLength()));


        } catch (Exception e) {

        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }
}
