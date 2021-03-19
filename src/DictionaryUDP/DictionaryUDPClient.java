/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class DictionaryUDPClient {
    public static void menu(){
        System.out.println("1.Anh->Viet");
        System.out.println("2.Viet->Anh");
        System.out.println("3.Hien toan bo tu dien");
        System.out.println("4.Exit");
        System.out.println("Choice: ");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            byte[] buffer = new byte[2048];
            
            String rep="";
            DatagramPacket target;
            while(true){
                menu();
                rep= sc.nextLine();
                buffer = rep.getBytes();
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, IP, 69);
                client.send(request);
                String targetWord;
                DatagramPacket meaning;
                switch (rep){
                    case "1":
                        
                        System.out.println("Nhap tu can tra: ");
                        targetWord = sc.nextLine();
                        
                        buffer = new byte[2048];
                        buffer = targetWord.getBytes();
                        target = new DatagramPacket(buffer, buffer.length, IP, 69);
                        client.send(target);
                        
                        buffer = new byte[2048];
                        meaning = new DatagramPacket(buffer, buffer.length);
                        client.receive(meaning);
                        System.out.println("Nghia: "+new String(meaning.getData(),0,meaning.getLength()));
                        break;
                    case "2":
                        
                        System.out.println("Nhap tu can tra: ");
                        targetWord = sc.nextLine();

                        buffer = new byte[2048];
                        buffer = targetWord.getBytes();
                        target = new DatagramPacket(buffer, buffer.length, IP, 69);
                        client.send(target);

                        buffer = new byte[2048];
                        meaning = new DatagramPacket(buffer, buffer.length);
                        client.receive(meaning);
                        System.out.println("Nghia: " + new String(meaning.getData(), 0, meaning.getLength()));
                        break;
                }
                
            }
            
        } catch (Exception e) {
        }
        
    }
    
}
