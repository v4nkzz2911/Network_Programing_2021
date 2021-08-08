/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VX;

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
public class VXClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getLocalHost();
        byte[] buffer = new byte[1024];
        
        Scanner sc = new Scanner(System.in);
        while (true) {            
            System.out.println("Nhập thông điệp: ");
            String report = sc.nextLine();
            
            buffer=new byte[1024];
            buffer = report.getBytes();
            DatagramPacket reportPacket = new DatagramPacket(buffer, buffer.length,IP,10);
            client.send(reportPacket);
            
            buffer = new byte[1024];
            DatagramPacket confirmPacket = new DatagramPacket(buffer, buffer.length);
            client.receive(confirmPacket);
            
            System.out.println(new String(confirmPacket.getData(),0,confirmPacket.getLength()));
        }
    }
}
