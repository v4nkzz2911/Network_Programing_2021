/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticeUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author er1nzz
 */
public class PracticeUDPServer {

    public static void main(String[] args) {
        DatagramSocket ds = null;
        byte[] buffer = new byte[4096];
        try {
            ds = new DatagramSocket(10);
            System.out.println("Opened connection");

            while (true) {
                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);
                System.out.println("A packet was received!");

                String message = new String(in.getData(), 0, in.getLength());
                String result;
                int n;
                n = Integer.parseInt(message);
                System.out.println("-> " + n);
                if ((n%2)==0) result="chan";
                else result="le";
                System.out.println(result);
                
                buffer = result.getBytes();
                DatagramPacket out = new DatagramPacket(buffer, buffer.length, in.getAddress(), in.getPort());
                ds.send(out);
            }
        } catch (Exception e) {
        }
    }

}
