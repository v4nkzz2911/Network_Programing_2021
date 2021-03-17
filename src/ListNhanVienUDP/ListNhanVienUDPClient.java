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
import sun.misc.UCDecoder;

/**
 *
 * @author er1nzz
 */
public class ListNhanVienUDPClient {
    public static void menu(){
        System.out.println("");
        System.out.println("1.Tìm kiếm SV");
        System.out.println("2.Hiển thị toàn bộ SV");
        System.out.println("3.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        Scanner sc = new Scanner(System.in);
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            int rep=0;
            while (rep!=3) {                
                menu();
                rep =sc.nextInt();
                sc.nextLine();
                String request = rep+"";
                buffer = new byte[2048];
                buffer = request.getBytes();
                DatagramPacket requestToServer = new DatagramPacket(buffer, buffer.length, IP, 10);
                client.send(requestToServer);
            
            
            switch (rep){
                case 1:
                    System.out.println("Nhập tên cần tìm: ");
                    String target = sc.nextLine();
                    buffer = new byte[2048];
                    buffer = target.getBytes();
                    DatagramPacket requestTargetToServer = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(requestTargetToServer);
                    
                    buffer = new byte[2048];
                    DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length);
                    client.receive(resultPacket);
                    System.out.println(new String(resultPacket.getData(),0,resultPacket.getLength()));
                    break;
                case 2:
                    buffer = new byte[2048];
                    DatagramPacket currentItem = new DatagramPacket(buffer, buffer.length);
                    client.receive(currentItem);
                    int size= Integer.parseInt(new String(currentItem.getData(),0,currentItem.getLength()));
                    System.out.println("Danh sách nhân viên trên Server");
                    System.out.println("Tên\t\tTuổi\tLương");
                    buffer = new byte[2048];
                    currentItem = new DatagramPacket(buffer, buffer.length);
                    client.receive(currentItem);
                    String output = new String(currentItem.getData(),0,currentItem.getLength());
                    int i=0;
                    while (i<size) {
                        System.out.println(output);
                        buffer = new byte[2048];
                        currentItem = new DatagramPacket(buffer, buffer.length);
                        client.receive(currentItem);
                        output = new String(currentItem.getData());
                        i++;
                    }
                    
            }
            }
            
            
            
        
            
            
                
            
        } catch (Exception e) {
        }
    }
    
}
