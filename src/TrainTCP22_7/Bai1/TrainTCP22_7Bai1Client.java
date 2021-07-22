/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainTCP22_7.Bai1;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/** 
 *
 * @author er1nzz
 */
public class TrainTCP22_7Bai1Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 10);
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhập vào số nguyên dương: ");
            
            dos.writeUTF(sc.nextLine());
            
            System.out.println("Giai thừa là: "+dis.readUTF());
        }
    }
}
