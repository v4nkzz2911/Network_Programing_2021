/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class entermUDPServer {
    static ArrayList<hoadon> ds = new ArrayList<>();
    public static Double thanhtoan(int sd) {
        if (sd < 51) {
            return sd * 1.7;
        }
        if (sd > 50 && sd < 101) {
            return 50 * 1.7 + (sd - 50) * 2.0;
        } else {
            return 50 * 1.7 + 50 * 2.0 + (sd - 100) * 2.5;
        }
    }
    
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer = new byte[1024];
        Scanner sc =new Scanner(System.in);
        while (true) {
            buffer = new byte[1024];
            DatagramPacket consumPacket = new DatagramPacket(buffer, buffer.length);
            server.receive(consumPacket);
            
            int n = Integer.parseInt(new String(consumPacket.getData(),0,consumPacket.getLength()).split("\\:")[1]);
            
            buffer = new byte[1024];
            buffer = (thanhtoan(n)+"").getBytes();
            DatagramPacket pricePacket = new DatagramPacket(buffer, buffer.length, consumPacket.getAddress(), consumPacket.getPort());
            server.send(pricePacket);
            
            hoadon t = new hoadon(n, thanhtoan(n));
            ds.add(t);
            System.out.println("Thống kê");
            System.out.println("Thứ tự hộ\tChỉ số\tTiêu thụ");
            int i;
            for (i=0;i<ds.size();i++){
                System.out.println(i+"\t\t"+ds.get(i).toString());
            }
        }
    }
}
