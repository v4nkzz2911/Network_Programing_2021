/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author er1nzz
 */
public class TrainUDPServer {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer = new byte[1024];
        
        buffer = new byte[1024];
        DatagramPacket nameReceive = new DatagramPacket(buffer, buffer.length);
        server.receive(nameReceive);
        
        String name = new String(nameReceive.getData(),0,nameReceive.getLength());
        
        String reply = "Hello "+name;
        buffer = new byte[1024];
        buffer = reply.getBytes();
        DatagramPacket replyPacket = new DatagramPacket(buffer, buffer.length, nameReceive.getAddress(), nameReceive.getPort());
        server.send(replyPacket);
        
    }
    
}
