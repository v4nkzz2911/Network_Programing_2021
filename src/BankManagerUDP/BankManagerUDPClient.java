/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class BankManagerUDPClient {
    public void menu(){
        System.out.println("1.Nạp tiền");
        System.out.println("2.Rút tiền");
        System.out.println("3.Kiểm tra số dư");
        System.out.println("4.Lịch sử giao dịch");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        byte[] buffer = new byte[2048];
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress IP = InetAddress.getByName("localhost");
            
            String loginResult = "Login Failed";
            while (loginResult.equals("Login Failed")){
                System.out.println("Username: ");
                String username = sc.nextLine();
                System.out.println("Pass: ");
                String pass = sc.nextLine();
                
                String loginInfo = username+"$"+pass;
                buffer = new byte[2048];
                buffer = loginInfo.getBytes();
                DatagramPacket loginInfoPacket = new DatagramPacket(buffer, buffer.length, IP, 69);
                client.send(loginInfoPacket);
                
                buffer = new byte[2048];
                DatagramPacket loginReplyPacket = new DatagramPacket(buffer, buffer.length);
                client.receive(loginReplyPacket);
                
                loginResult = new String(loginReplyPacket.getData(),0,loginReplyPacket.getLength());
                System.out.println(loginResult);
                
            }
            
            
        } catch (Exception e) {
        }
    }
    
}
