/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP20_7.Bai2;

import java.io.IOException;
import static java.lang.System.exit;
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
public class TrainUDP20_7Bai2Client {
    public static void menu(){
        System.out.println("1.Hiển thị thông tin");
        System.out.println("2.Mượn sách");
        System.out.println("3.Trả sách");
        System.out.println("4.Thoát");
        System.out.println("Lựa chọn: ");
    }
    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] buffer = new byte[1024];
        InetAddress IP = InetAddress.getLocalHost();
        
        while (true) {            
            Scanner sc = new Scanner(System.in);
            
            menu();
            int chon = sc.nextInt();
            
            buffer = new byte[1024];
            buffer = (chon+"").getBytes();
            DatagramPacket selectPacket = new DatagramPacket(buffer, buffer.length, IP, 10);
            client.send(selectPacket);
            
            DatagramPacket targetPacket;
            DatagramPacket resultPacket;
            switch (chon){
                case 1:
                    buffer = new byte[1024];
                    resultPacket = new DatagramPacket(buffer, buffer.length);
                    client.receive(resultPacket);
                    
                    System.out.println(new String(resultPacket.getData(),0,resultPacket.getLength()));
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Nhập mã sách cần mượn: ");
                    buffer =new byte[1024];
                    buffer = sc.nextLine().getBytes();
                    targetPacket = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(targetPacket);
                    
                    buffer = new byte[1024];
                    resultPacket = new DatagramPacket(buffer, buffer.length);
                    client.receive(resultPacket);

                    System.out.println(new String(resultPacket.getData(), 0, resultPacket.getLength()));
                    break;
                case 3:
                    sc.nextLine();
                    System.out.println("Nhập mã sách cần mượn: ");
                    buffer = new byte[1024];
                    buffer = sc.nextLine().getBytes();
                    targetPacket = new DatagramPacket(buffer, buffer.length, IP, 10);
                    client.send(targetPacket);

                    buffer = new byte[1024];
                    resultPacket = new DatagramPacket(buffer, buffer.length);
                    client.receive(resultPacket);

                    System.out.println(new String(resultPacket.getData(), 0, resultPacket.getLength()));
                    break;
                case 4:
                    exit(0);
            }
            
        }
    }
}
