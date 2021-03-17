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
            FileReader fr = new FileReader("nhanvien.dat");
            BufferedReader br = new BufferedReader(fr);

            String current;
            while ((current = br.readLine()) != null) {

                NhanVien nv = new NhanVien();
                String[] result = current.split("\\$");
                nv.setName(result[0]);
                nv.setAge(Integer.parseInt(result[1]));
                nv.setSalary(Integer.parseInt(result[2]));
                dsnv.add(nv);
                String currentItem = nv.toString();
                System.out.println(currentItem);
            }
            fr.close();
            br.close();
            System.out.println("Read list ok");
            while (true) {                
                
            
                if (temp.equals("1")) {
                    buffer = new byte[2048];
                    DatagramPacket targetSearch = new DatagramPacket(buffer, buffer.length);
                    server.receive(targetSearch);
                    String target = new String(targetSearch.getData(),0,targetSearch.getLength());
                    int i;
                    int found=0;
                    for (i = 0; i < dsnv.size(); i++) {
                        if (dsnv.get(i).getName().equals(target)) {

                        found=1;
                        String currentItem = dsnv.get(i).toString();
                        buffer = new byte[2048];
                        buffer = currentItem.getBytes();

                        DatagramPacket currentItemToClient = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                        server.send(currentItemToClient);
                        }
                        if(found==0){
                            String notFound="Khong tim thay";
                            buffer = new byte[2048];
                            buffer = notFound.getBytes();

                            DatagramPacket currentItemToClient = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                            server.send(currentItemToClient);
                        }

                    }
                }
                if (temp.equals("2")){
                        
                    String currentItem = dsnv.size()+"";
                    buffer = new byte[2048];
                    buffer = currentItem.getBytes();

                    DatagramPacket currentItemToClient = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                    server.send(currentItemToClient);
                        int i;
                        for(i=0;i<dsnv.size();i++){
                            currentItem = dsnv.get(i).toString();
                            buffer = new byte[2048];
                            buffer = currentItem.getBytes();

                            currentItemToClient = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                            server.send(currentItemToClient);
                        

                    }
                    
                       




                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
