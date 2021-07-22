package TrainTCP22_7.Bai2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author er1nzz
 */
public class TrainTCP22_7Bai2Server {
    static ArrayList<Cauthu> player = new ArrayList();
    
    public static double layHeSoLuong(Cauthu t){
        switch (t.getPos()){
            case "ST":
                return 0.025;
            case "CB":
            case "CM":
                return 0.02;
            case "GK":
                return 0.015;
        }
        return 0;
    }
    
    public static String tinhLuong(String target, int match){
        String output="";
        int i, found =-1;
        for(i=0 ;i<player.size();i++){
            if (player.get(i).getID().equalsIgnoreCase(target)){
                found=i;
            }
        }
        if (found==-1){
            output = "Không tìm thấy";
            return output;
        }
        double s = player.get(found).getDefsalary()*(match + layHeSoLuong(player.get(found)));
        
        output = s+"";
        return output;
    }
    
    public static String hienthi() {
        String output = "";
        int i;
        output = output + "Mã số\tTên\tNăm sinh\tVị trí\tLương cơ bản\n";
        for (i = 0; i < player.size(); i++) {
            output = output + player.get(i).toString();
        }
        return output;
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10);
        Socket client = server.accept();
        
//        Cauthu A = new Cauthu("01", "A", "1999", "ST", 1000);
//        Cauthu B = new Cauthu("02", "B", "2000", "GK", 2000);
//        Cauthu C = new Cauthu("03", "C", "1999", "CB", 3000);
//        Cauthu D = new Cauthu("04", "D", "1996", "CM", 4000);
//        
//        player.add(A);
//        player.add(B);
//        player.add(C);
//        player.add(D);
        
        FileReader fr = new FileReader("cauthu.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String currentLine;
        
        while ((currentLine = br.readLine())!=null) {            
            String[] result = currentLine.split("\\$");
            
            Cauthu t = new Cauthu();
            t.setID(result[0]);
            t.setName(result[1]);
            t.setBornyear(result[2]);
            t.setPos(result[3]);
            t.setDefsalary(Integer.parseInt(result[4]));
            
            player.add(t);
        }
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            String chon= dis.readUTF();
            
            switch (chon){
                case "1":
                    currentLine = dis.readUTF();
                    String[] result = currentLine.split("\\$");

                    Cauthu t = new Cauthu();
                    t.setID(result[0]);
                    t.setName(result[1]);
                    t.setBornyear(result[2]);
                    t.setPos(result[3]);
                    t.setDefsalary(Integer.parseInt(result[4]));

                    player.add(t);
                    
                    dos.writeUTF("Thêm thành công");
                    
                    break;
                case "2":
                    dos.writeUTF(hienthi());
                    
                    break;
                case "3": 
                    String target = dis.readUTF();
                    String match = dis.readUTF();
                    dos.writeUTF(tinhLuong(target, Integer.parseInt(match)));
                    
                    break;
            }
        }
        
    }
}
