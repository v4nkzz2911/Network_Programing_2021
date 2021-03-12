/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListNhanVienUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class ListNhanVienUDPClient {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        Scanner sc = new Scanner(System.in);
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            
            System.out.println("Yêu cầu hiển thị danh sách nhân viên(y/n)?");
            String rep="y";
            
            buffer = rep.getBytes();
            DatagramPacket requestToServer = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(requestToServer);
            
        
            
            System.out.println("Danh sách nhân viên trên Server");
            System.out.println("Tên\t\tTuổi\tLương");
            while(true){
                buffer = new byte[2048];
                DatagramPacket currentItem = new DatagramPacket(buffer, buffer.length);
                client.receive(currentItem);
                String output = new String(currentItem.getData());
                System.out.println(output);
                
            }
        } catch (Exception e) {
        }
    }
    
}
