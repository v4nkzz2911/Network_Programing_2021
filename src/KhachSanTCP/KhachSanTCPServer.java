/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachSanTCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class KhachSanTCPServer {

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

    public static String rentRoom(String targetID) throws IOException {
        int i, found = 0;
        String output = "Không tìm thấy";
        
        FileWriter fw = new FileWriter("HistoryRent.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getID().equalsIgnoreCase(targetID)) {
                if (dsn.get(i).getStatus().equals("Đã thuê")) {
                    return "Phòng đã được thuê, mời chọn phòng khác!";
                }
                bw.write(dsn.get(i).getID()+"");
                bw.newLine();
                
                bw.close();
                fw.close();
                output = "Bạn đã đặt phòng có mã số: " + dsn.get(i).getID();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }

    public static String update() throws FileNotFoundException, IOException{
        FileReader fr = new FileReader("HistoryRent.txt");
        BufferedReader br = new BufferedReader(fr);

        String current;
        while ((current = br.readLine()) != null) {
            int i;
            for (i = 0; i < dsn.size(); i++) {
                if (dsn.get(i).getID().equalsIgnoreCase(current)) {
                    
                    dsn.get(i).setStatus("Đã thuê");
                }
            }
            

        }
        fr.close();
        br.close();
        return "Thành công!";
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket mySocket = new ServerSocket(10);
        System.out.println("Openned Connection");
        Socket client = mySocket.accept();
        System.out.println("Connected");
        
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
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            String rep = dis.readUTF();
            switch (rep){
                case "1":
                    dos.writeUTF(search(dis.readUTF()));
                    break;
                case "2":
                    dos.writeUTF(showAll());
                    break;
                case "3":
                    dos.writeUTF(rentRoom(dis.readUTF()));
                    break;
                case "4":
                    dos.writeUTF(update());
                
            }
        }

    }

}
