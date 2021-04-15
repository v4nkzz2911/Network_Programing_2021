/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UocSoUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author er1nzz
 */
public class UocSoUDPServer {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer = new byte[1024];
        
        while (true) { 
            buffer = new byte[1024];
            DatagramPacket target = new DatagramPacket(buffer, buffer.length);
            server.receive(target);
            int n = Integer.parseInt(new String(target.getData(),0,target.getLength()));
            int i,d = 0;
            String output="";
            for(i=1;i<=n;i++){
                if (n%i==0){
                    d++;
                    output = output+i+"   ";
                }
            }
            
            buffer = new byte[1024];
            buffer = (d+"").getBytes();
            DatagramPacket count = new DatagramPacket(buffer, buffer.length,target.getAddress(),target.getPort());
            server.send(count);
            
            buffer = new byte[1024];
            buffer = output.getBytes();
            DatagramPacket serial = new DatagramPacket(buffer, buffer.length, target.getAddress(), target.getPort());
            server.send(serial);
            
        }
    }
    
}
