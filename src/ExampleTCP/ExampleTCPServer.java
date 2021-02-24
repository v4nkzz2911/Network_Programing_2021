/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExampleTCP;

import com.sun.org.apache.xerces.internal.util.FeatureState;
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
    public static int isPrime(int n){
        int i;
        if (n<2) return 0;
        if (n==2) return 1;
        for (i=2;i<=Math.sqrt(n);i++){
            if ((n%i)==0) return 0;
        }
        return 1;
    }
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
                int i,d=0;
                for (i=1;i<=n;i++){
                    if (isPrime(i)==1) {
                        d++;
                        System.out.println(i);
                    }
                }
                dos = new DataOutputStream(clientSocket.getOutputStream());
                dos.writeUTF("Có "+d+" số nguyên tố từ 1 đến "+n+"!");
                //dis.close();
                //dos.close();
            }
        }catch(Exception e){
            
        }
    }
    
}
