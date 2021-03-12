/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListNhanVienUDP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class ListNhanVienUDPServer {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];
        ArrayList<NhanVien> dsnv = new ArrayList();
        try {
            DatagramSocket server = new DatagramSocket(10);
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            server.receive(request);
            String temp =new String(request.getData(),0,request.getLength());
            System.out.println(temp);
            if (true){
                FileReader fr = new FileReader("nhanvien.dat");
                BufferedReader br =new BufferedReader(fr);
                
                dsnv.clear();
                String current;
                while((current=br.readLine())!=null){
                    
                    NhanVien nv = new NhanVien();
                    String[] result = current.split("\\$");
                    nv.setName(result[0]);
                    nv.setAge(Integer.parseInt(result[1]));
                    nv.setSalary(Integer.parseInt(result[2]));
                    
                    String currentItem = nv.toString();
                    System.out.println(currentItem);
                    buffer = new byte[2048];
                    buffer = currentItem.getBytes();

                    DatagramPacket currentItemToClient = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                    server.send(currentItemToClient);
                    
                    
                }
                System.out.println("Read list ok");
                int i;
                for (i = 0; i < dsnv.size(); i++) {
                    System.out.println(dsnv.get(i).toString());
                }
                
                
            }
        } catch (Exception e) {
        }
    }
    
}
