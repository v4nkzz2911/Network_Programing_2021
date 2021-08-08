/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.UDP;

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
public class entermUDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getLocalHost();
        byte[] buffer = new byte[1024];
        
        Scanner sc = new Scanner(System.in);
        while (true) {            
            System.out.println("Chỉ số điện tiêu thụ: ");
            int sd = sc.nextInt();
            
            buffer = new byte[1024];
            buffer = ("Chỉ số điện tiêu thụ:"+sd).getBytes();
            DatagramPacket consumPacket = new DatagramPacket(buffer, buffer.length, IP,10);
            client.send(consumPacket);
            
            buffer = new byte[1024];
            DatagramPacket pricePacket = new DatagramPacket(buffer, buffer.length);
            client.receive(pricePacket);
            
            System.out.println("Hoá đơn của bạn: "+new String(pricePacket.getData(),0,pricePacket.getLength()));
        }
    }
}
