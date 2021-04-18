/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChungCuUDP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class ChungCuUDPServer {
    static ArrayList<ChungCu> list = new ArrayList<>();
    
    public static String showAll() {
        int i;
        String output = "";
        for (i = 0; i < list.size(); i++) {
            output = output + list.get(i).toString() + "\n";
        }
        return output;
    }
    
    public static String search(String target) {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equalsIgnoreCase(target)) {
                output = list.get(i).toString();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }
    
    public static String rentHouse(String targetID) {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < list.size(); i++) {
            if (list.get(i).getID().equalsIgnoreCase(targetID)) {
                if (list.get(i).getStatus().equals("True")) {
                    return "Nhà đã được bán, mời chọn nhà khác!";
                }
                list.get(i).setStatus("True");
                output = "Bạn đã mua nhà có số nhà: " + list.get(i).getID();
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
        byte[] buffer = new byte[1024];
        System.out.println("Openned connection");
        
        FileReader fr = new FileReader("chungcu.txt");
        BufferedReader br = new BufferedReader(fr);
        String a;
        while ((a = br.readLine()) != null) {
            ChungCu hs = new ChungCu();
            String[] result = a.split("\\$");
            hs.setID(result[0]);
            hs.setPrice(result[1]);
            hs.setStatus(result[2]);
            
            list.add(hs);

        }
        br.close();
        fr.close();
        
        String rep="0";
        while (true){
            buffer = new byte[1024];
            DatagramPacket choice = new DatagramPacket(buffer, buffer.length);
            server.receive(choice);
            
            rep = new String(choice.getData(),0,choice.getLength());
            switch(rep){
                case "1":
                    buffer = new byte[1024];
                    DatagramPacket ID = new DatagramPacket(buffer, buffer.length);
                    server.receive(ID);
                    
                    buffer = new byte[1024];
                    buffer = (search(new String(ID.getData(),0,ID.getLength()))).getBytes();
                    DatagramPacket searchResult = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(searchResult);
                    
                    break;
                case "2":
                    buffer = new byte[1024];
                    DatagramPacket newHouse = new DatagramPacket(buffer, buffer.length);
                    server.receive(newHouse);
                    
                    ChungCu hs = new ChungCu();
                    String[] result = (new String(newHouse.getData(),0,newHouse.getLength())).split("\\$");
                    hs.setID(result[0]);
                    hs.setPrice(result[1]);
                    hs.setStatus(result[2]);

                    list.add(hs);
                    
                    FileWriter fw = new FileWriter("chungcu.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);

                    bw.write(hs.toSend());
                    bw.newLine();
                    
                    bw.close();
                    fw.close();
                    
                    break;
                case "3":
                    buffer = new byte[1024];
                    ID = new DatagramPacket(buffer, buffer.length);
                    server.receive(ID);

                    buffer = new byte[1024];
                    buffer = (rentHouse(new String(ID.getData(), 0, ID.getLength()))).getBytes();
                    DatagramPacket rentResult = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(rentResult);
                    fw = new FileWriter("chungcu.txt");
                    bw = new BufferedWriter(fw);
                    for (ChungCu t : list) {
                        bw.write(t.toSend());
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                    break;
                    
                case "4":
                    buffer = new byte[1024];
                    buffer = (showAll()).getBytes();
                    DatagramPacket all = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(all);
                    break;
            }
        }
    }
}
