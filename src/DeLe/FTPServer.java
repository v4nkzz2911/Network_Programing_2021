/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DeLe;

import com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author er1nzz
 */
public class FTPServer {
    public static String readFile(String target) throws IOException{
        String output = "", current;
        try {
            FileReader fr = new FileReader(target);
            BufferedReader br = new BufferedReader(fr);
            
            
            
            while ((current = br.readLine())!=null) {                
                output= output+current+"\n";
                
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FTPServer.class.getName()).log(Level.SEVERE, null, ex);
            return "Không tìm thấy file";
            
        }
        
        return output;
        
    }
    
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10);
        Socket client = server.accept();
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            String taget = dis.readUTF();
            
            dos.writeUTF(readFile(taget));
        }
    }
    
}
