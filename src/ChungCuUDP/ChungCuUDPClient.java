/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChungCuUDP;

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
public class ChungCuUDPClient {
    public static void menu(){
        System.out.println("1. Tìm kiếm theo số nhà");
        System.out.println("2. Thêm nhà");
        System.out.println("3. Thuê nhà");
        System.out.println("4. Hiển thị tất cả");
        System.out.println("5. Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        InetAddress IP = InetAddress.getLocalHost();
        byte[] buffer;
        Scanner sc = new Scanner(System.in);
        
        int rep=0;
        while (rep!=5) { 
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            buffer = new byte[1024];
            buffer = (rep + "").getBytes();
            DatagramPacket choice = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(choice);
            switch(rep){
                case 1:
                    System.out.println("Nhập số nhà cần tìm: ");
                    buffer = new byte[1024];
                    buffer = (sc.nextLine()).getBytes();
                    DatagramPacket ID = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(ID);
                    
                    buffer = new byte[1024];
                    DatagramPacket searchResult = new DatagramPacket(buffer, buffer.length);
                    client.receive(searchResult);
                    
                    System.out.println("Kết quả tìm kiếm");
                    System.out.println(new String(searchResult.getData(),0,searchResult.getLength()));
                    
                    break;
                case 2 :
                    ChungCu t = new ChungCu();
                    System.out.println("Nhập số nhà: ");
                    t.setID(sc.nextLine());
                    System.out.println("Nhập giá: ");
                    t.setPrice(sc.nextLine());
                    System.out.println("Nhập tình trạng: ");
                    t.setStatus(sc.nextLine());
                    
                    buffer = new byte[1024];
                    buffer = t.toSend().getBytes();
                    DatagramPacket newHouse = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(newHouse);
                    
                    System.out.println("Thêm thành công");
                    break;
                case 3:
                    System.out.println("Nhập số nhà cần mua: ");
                    buffer = new byte[1024];
                    buffer = (sc.nextLine()).getBytes();
                    ID = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(ID);
                    
                    buffer = new byte[1024];
                    DatagramPacket rentResult = new DatagramPacket(buffer, buffer.length);
                    client.receive(rentResult);

                    
                    System.out.println(new String(rentResult.getData(), 0, rentResult.getLength()));
                    break;
                case 4:
                    buffer = new byte[1024];
                    DatagramPacket all = new DatagramPacket(buffer, buffer.length);
                    client.receive(all);

                    System.out.println(new String(all.getData(), 0, all.getLength()));
                    break;
                    
            }
        }
        
    }
}
