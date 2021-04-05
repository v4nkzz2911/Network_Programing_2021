/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP;

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
public class TrainUDPClient {
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] buffer = new byte[1024];
        InetAddress IP = InetAddress.getByName("localhost");
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Input name: ");
        String name = sc.nextLine();
        
        buffer = new byte[1024];
        buffer = name.getBytes();
        DatagramPacket namePacket = new DatagramPacket(buffer, buffer.length, IP, 10);
        client.send(namePacket);
        
        buffer = new byte[1024];
        DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length);
        client.receive(replyPacket);
        
        System.out.println(new String(replyPacket.getData(),0,replyPacket.getLength()));
    }
    
}
