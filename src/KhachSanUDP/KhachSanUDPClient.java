/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachSanUDP;

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
public class KhachSanUDPClient {
    
    public static void menu() {
        System.out.println("1.Tìm kiếm phòng ");
        System.out.println("2.Hiển thị tất cả phòng");
        System.out.println("3.Thuê phòng");
        System.out.println("4.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];
        
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        while (rep != 4) {
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            
            buffer = new byte[1024];
            
            buffer = (rep+"").getBytes();
            DatagramPacket choice = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(choice);
            
            switch (rep) {
                case 1:
                    System.out.println("Nhập mã số nhà cần tìm: ");
                    String target = sc.nextLine();
                    
                    buffer = new byte[1024];
                    buffer =target.getBytes();
                    DatagramPacket targetSearch = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(targetSearch);
                    
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    
                    buffer = new byte[1024];
                    DatagramPacket repSearch = new DatagramPacket(buffer, buffer.length);
                    client.receive(repSearch);
                    System.out.println(new String(repSearch.getData(),0,repSearch.getLength()));
                    
                    
                    break;
                case 2:
                    System.out.println("Thông tin toàn bộ các nhà");
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    buffer = new byte[1024];
                    DatagramPacket repShowAll = new DatagramPacket(buffer, buffer.length);
                    client.receive(repShowAll);
                    
                    System.out.println(new String(repShowAll.getData(),0,repShowAll.getLength()));
                    
                    break;
                case 3:
                    System.out.println("Nhập mã số nhà cần thuê");
                    String targetRent = sc.nextLine();

                    buffer = new byte[1024];
                    buffer = targetRent.getBytes();
                    DatagramPacket targetRentPack = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(targetRentPack);
                    
                    buffer = new byte[1024];
                    DatagramPacket resultRent = new DatagramPacket(buffer, buffer.length);
                    client.receive(resultRent);
                    
                    System.out.println(new String(resultRent.getData(),0,resultRent.getLength()));
            }
        }
    }
}
