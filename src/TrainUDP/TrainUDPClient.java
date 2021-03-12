/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class TrainUDPClient {
    public static void main(String[] args) {
        try {
            Scanner sc =new Scanner(System.in);
            byte[] buffer = new byte[4096];
            DatagramSocket client = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            System.out.println("input: ");
            String name=sc.nextLine();
            
            buffer = name.getBytes();
            
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length,IP,10);
            client.send(packetSend);
            
            buffer = new byte[4096];
            DatagramPacket packetReceive = new DatagramPacket(buffer, buffer.length);
            client.receive(packetReceive);
            
            String temp = new String(packetReceive.getData(),0,packetReceive.getLength());
            System.out.println(temp);
            
        } catch (Exception e) {
        }
    }
    
}
