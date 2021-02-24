/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoMessageUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author er1nzz
 */
public class ServerEcho {
    public static void main(String[] args) {
        DatagramSocket ds = null;
        byte[] buffer = new byte[2048];
        try {
            ds = new DatagramSocket(10);
            System.out.println("Opened connection");
            
            while (true){
                DatagramPacket in = new DatagramPacket(buffer, buffer.length);
                ds.receive(in);
                System.out.println("A packet was received!");
                
                String message = new String (in.getData(),0,in.getLength());
                System.out.println("-> "+message);
                
                String result="";
                for(int i=message.length()-1;i>=0;i--){
                    result += message.charAt(i);
                }
                System.out.println(result);
                
                
                buffer = result.getBytes();
                DatagramPacket out = new DatagramPacket(buffer, buffer.length, in.getAddress(), in.getPort());
                ds.send(out);
            }
        } catch (Exception e) {
        }
    }
    
}
