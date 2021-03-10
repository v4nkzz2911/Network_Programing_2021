/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SumUDP;

import java.io.ObjectStreamConstants;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class SumUDPServer {
    public static void menu(){
        System.out.println("1.+");
        System.out.println("2.-");
        System.out.println("Choice: ");
    }
    
    public static void main(String[] args) {
        DatagramSocket ds = null;
        byte[] buffer = new byte[2048];
        Scanner sc = new Scanner(System.in);
        try {
            ds = new DatagramSocket(10);
            System.out.println("Connected");
            while (true) {
                
                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);
                
                String message = new String(in.getData(),0,in.getLength());
                String[] data = message.split("\\$");
                System.out.println(data[0]+data[1]);
                menu();
                int rep = sc.nextInt();
                int result=0;
                switch (rep){
                    case 1:
                        result = Integer.parseInt(data[0]) + Integer.parseInt(data[1]);
                        break;
                    case 2:
                        result = Integer.parseInt(data[0]) - Integer.parseInt(data[1]);
                        break;
                        
                }
                
                
                System.out.println(result);
                buffer = (result+"").getBytes();
                DatagramPacket out = new DatagramPacket(buffer, buffer.length, in.getAddress(), in.getPort());
                ds.send(out);
            }
        } catch (Exception e) {
        }
    }
    
}
