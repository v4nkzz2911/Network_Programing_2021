/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachSanUDP;

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
public class KhachSanUDPServer {

    static ArrayList<PhongKhachSan> dsn = new ArrayList<>();

    public static String search(String target) {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getID().equalsIgnoreCase(target)) {
                output = dsn.get(i).toString();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }

    public static String showAll() {
        int i;
        String output = "";
        for (i = 0; i < dsn.size(); i++) {
            output = output + dsn.get(i).toString() + "\n";
        }
        return output;
    }

    public static String rentHouse(String targetID) {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getID().equalsIgnoreCase(targetID)) {
                if (dsn.get(i).getStatus().equals("Đã thuê")) {
                    return "Phòng đã được thuê, mời chọn phòng khác!";
                }
                dsn.get(i).setStatus("Đã thuê");
                output = "Bạn đã thuê phòng có mã số: " + dsn.get(i).getID();
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

        FileReader fr = new FileReader("KhachSan.txt");
        BufferedReader br = new BufferedReader(fr);

        String current;
        while ((current = br.readLine()) != null) {

            PhongKhachSan p = new PhongKhachSan();
            String[] result = current.split("\\$");
            p.setID(result[0]);
            p.setType(result[1]);
            p.setAddress(result[2]);
            p.setStatus(result[3]);

            System.out.println(p.toString());
            dsn.add(p);

        }
        fr.close();
        br.close();
        System.out.println("Read list ok");

        while (true) {

            buffer = new byte[1024];
            DatagramPacket choice = new DatagramPacket(buffer, buffer.length);
            server.receive(choice);
            String rep = new String(choice.getData(), 0, choice.getLength());
            switch (rep) {
                case "1":
                    buffer = new byte[1024];
                    DatagramPacket targetPacket = new DatagramPacket(buffer, buffer.length);
                    server.receive(targetPacket);

                    buffer = new byte[1024];
                    buffer = search(new String(targetPacket.getData(), 0, targetPacket.getLength())).getBytes();
                    DatagramPacket repSearch = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(repSearch);
                    
                    break;
                case "2":
                    buffer = new byte[1024];
                    buffer = showAll().getBytes();
                    DatagramPacket repShowAll = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(repShowAll);
                    
                    break;
                case "3":
                    buffer = new byte[1024];
                    DatagramPacket targetRentPacket = new DatagramPacket(buffer, buffer.length);
                    server.receive(targetRentPacket);
                    
                    buffer = new byte[1024];
                    buffer = rentHouse(new String(targetRentPacket.getData(),0,targetRentPacket.getLength())).getBytes();
                    DatagramPacket rentResult = new DatagramPacket(buffer, buffer.length, choice.getAddress(), choice.getPort());
                    server.send(rentResult);
            }
        }
    }

}
