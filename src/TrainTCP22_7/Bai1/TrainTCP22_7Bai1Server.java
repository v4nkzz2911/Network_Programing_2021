package TrainTCP22_7.Bai1;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author er1nzz
 */
public class TrainTCP22_7Bai1Server {
    public static String giaithua(int a){
        String output;
        int i,s=1;
        for (i=1; i <= a ;i++){
            s=s*i;
        }
        output=s+"";
        return output;
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10);
        Socket client = server.accept();
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            
            
            dos.writeUTF(giaithua(Integer.parseInt(dis.readUTF())));
            
        }
    }
    
}
