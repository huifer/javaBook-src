package com.huifer.jdk.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <p>Title : UdpClientDemo </p>
 * <p>Description : </p>
 *
 * @author huifer
 * @date 2019-06-10
 */
public class UdpClientDemo {

    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("localhost");
        byte[] sendData = "hello".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(sendData, sendData.length, address,
                9999);

        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }

}
