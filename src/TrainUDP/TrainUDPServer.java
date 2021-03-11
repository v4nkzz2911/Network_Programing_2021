/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author er1nzz
 */
public class TrainUDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(10);
            byte[] buffer = new byte[4096];
            
            DatagramPacket packetReceive = new DatagramPacket(buffer, buffer.length);
            server.receive(packetReceive);
            
            String temp = new String(packetReceive.getData(),0,packetReceive.getLength());
            
            String respone = "Hello "+temp;
            
            buffer = respone.getBytes();
            
            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, packetReceive.getAddress(), packetReceive.getPort());
            server.send(packetSend);
            
        } catch (Exception ex) {
            
        }
        
    }
    
}
