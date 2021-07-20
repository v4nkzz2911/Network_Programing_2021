/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP20_7.Bai1;

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
public class TrainUDP20_7Bai1Client {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] buffer = new byte[1024];
        InetAddress IP = InetAddress.getLocalHost();
        
        while (true) {            
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập vào số cần tính bình phương: ");
            int n = sc.nextInt();
            
            buffer = new byte[1024];
            buffer = (n+"").getBytes();
            DatagramPacket sqrNumPacket = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(sqrNumPacket);
            
            buffer = new byte[1024];
            DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length);
            client.receive(resultPacket);
            
            System.out.println("Bình phương: "+new String(resultPacket.getData(),0,resultPacket.getLength()));
        }
    }
}
