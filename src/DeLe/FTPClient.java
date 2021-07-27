/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeLe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class FTPClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 10);
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập tên tập tin cần mở: ");
            String target = sc.nextLine();
            
            dos.writeUTF(target);
            
            String output = dis.readUTF();
            if (output.equalsIgnoreCase("Không tìm thấy file")) {
                System.out.println(output);
            }
            else{
                System.out.println("Nội dung file: ");
                System.out.println(output);
            }
        }
    }
}
