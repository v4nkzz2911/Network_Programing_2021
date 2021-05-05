/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HocSinhUDP;

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
public class HocSinhUDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getByName("localhost");
        byte[] buffer;
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã học sinh cần tìm: ");
        buffer = new byte[1024];
        buffer = sc.nextLine().getBytes();
        DatagramPacket targetPacket = new DatagramPacket(buffer, buffer.length, IP, 10);
        client.send(targetPacket);
        
        System.out.println("Kết quả:");
        buffer = new byte[1024];
        DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length);
        client.receive(resultPacket);
        
        System.out.println(new String(resultPacket.getData(),0,resultPacket.getLength()));
    }
    
}
