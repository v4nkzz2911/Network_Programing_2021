/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class ExampleTCPClient {
    public static void main(String[] args) {
        try {
            Socket mySocket = new Socket("127.0.0.1",10);
            Scanner sc = new Scanner(System.in);
            
            while (true) {   
                DataInputStream dis = new DataInputStream(mySocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());
                System.out.println("Nhập số cần kiểm tra: ");
                String input = sc.nextLine();
                dos.writeUTF(input);
                System.out.println(dis.readUTF());
                System.out.println(dis.readUTF());
                //dis.close();
                //dos.close();
            }
            
        } catch (Exception e) {
        }
    }
    
}
