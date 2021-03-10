/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class PracticeUDPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        byte[] buffer = new byte[4096];
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            while (true) {
                System.out.println("Enter number: ");
                String temp = sc.nextLine();
                buffer = temp.getBytes();
                DatagramPacket out = new DatagramPacket(buffer, buffer.length, IP, 10);
                ds.send(out);

                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);
                String output = new String(in.getData());
                System.out.println(output);
            }
        } catch (Exception e) {
        }
    }
        
}
