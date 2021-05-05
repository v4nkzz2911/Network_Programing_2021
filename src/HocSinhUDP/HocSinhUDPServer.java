/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HocSinhUDP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class HocSinhUDPServer {
    static ArrayList<HocSinh> dshs = new ArrayList<>();
    public static String search(String target){
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < dshs.size(); i++) {
            if (dshs.get(i).getID().equalsIgnoreCase(target)) {
                output = dshs.get(i).toString();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }
    
    
    public static void main(String[] args) throws SocketException, FileNotFoundException, IOException {
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer;
        
        FileReader fr = new FileReader("hocsinh.dat");
        BufferedReader br = new BufferedReader(fr);
        String a;
        while ((a = br.readLine()) != null) {
            HocSinh hs = new HocSinh();
            String[] result = a.split("\\$");
            hs.setID(result[0]);
            hs.setName(result[1]);
            hs.setStudyClass(result[2]);
            hs.setScore(Float.parseFloat(result[3]));
            System.out.println(hs.toString());
            dshs.add(hs);

        }
        br.close();
        fr.close();
        
        buffer = new byte[1024];
        DatagramPacket targetPacket = new DatagramPacket(buffer, buffer.length);
        server.receive(targetPacket);
        
        buffer = new byte[1024];
       
        buffer = (search(new String(targetPacket.getData(),0,targetPacket.getLength()))).getBytes();
        
        DatagramPacket resultPacket = new DatagramPacket(buffer, buffer.length, targetPacket.getAddress(), targetPacket.getPort());
        server.send(resultPacket);
        
        
    }
    
}
