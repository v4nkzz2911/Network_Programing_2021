/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UocSoUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class UocSoUDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];
        Scanner sc = new Scanner(System.in);
        while (true) {            
            System.out.println("Nhập số cần: ");
            buffer = new byte[1024];
            buffer = sc.nextLine().getBytes();
            DatagramPacket target = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(target);
            
            buffer = new byte[1024];
            DatagramPacket count = new DatagramPacket(buffer, buffer.length);
            client.receive(count);
            
            System.out.println("Số ước số: "+new String(count.getData(),0,count.getLength()));
            
            buffer = new byte[1024];
            DatagramPacket serial = new DatagramPacket(buffer, buffer.length);
            client.receive(serial);
            
            System.out.println("Các ước: ");
            System.out.println(new String(serial.getData(),0,serial.getLength()));
        }
    }
}
