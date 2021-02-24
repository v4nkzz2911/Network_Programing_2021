/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleTCP;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author er1nzz
 */
public class ExampleTCPServer {
    public static void main(String[] args) {
        try{
            ServerSocket myServer = new ServerSocket(10);
            System.out.println("Đã mở kết nối!");
            Socket clientSocket = myServer.accept();
            while (true) {
                
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                String input = dis.readUTF();
                int n = Integer.parseInt(input);
                System.out.println("Đã nhận: "+n);
                if ((n%2)==0){
                    dos.writeUTF("Là số chẵn");
                }
                else{
                    dos.writeUTF("Là số lẻ");
                }
                //dis.close();
                //dos.close();
            }
        }catch(Exception e){
            
        }
    }
    
}
