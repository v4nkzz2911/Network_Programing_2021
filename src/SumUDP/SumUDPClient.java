/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SumUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class SumUDPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        byte[] buffer = new byte[2048];
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            while (true) {
                System.out.println("Enter num 1: ");
                int a=sc.nextInt();
                System.out.println("Enter num 2: ");
                int b = sc.nextInt();        
                String temp = a +"$"+b;
                buffer = temp.getBytes();
                DatagramPacket out = new DatagramPacket(buffer, buffer.length, IP, 10);
                ds.send(out);

                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);
                String output = new String(in.getData(),0,in.getLength());
                System.out.println("result: " +output );
            }
        } catch (Exception e) {
        }
    }
    
}
