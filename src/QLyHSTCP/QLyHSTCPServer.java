/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLyHSTCP;

import KhachSanTCP.KhachSanTCPServer;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.chrono.JapaneseEra;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class QLyHSTCPServer {
    static ArrayList<HS> list = new ArrayList();
    
    
    public static String showAll(){
        int i;
        String output =  "";
        for(i=0;i<list.size();i++){
            output=output + list.get(i).toString()+"\n";
        }
        return output;
    }
    
    public static String show(){
        int i;
        String output = "";
        for(i=0;i<list.size();i++){
            if(list.get(i).getDiemTongket()>=8)
                output = output + list.get(i).toString()+"\n";
            
        }
        return output;
    }
    
   
    
    public String Nhap(){
        Scanner sc = new Scanner(System.in);
        HS hs = new HS();
        System.out.println("Nhap ma hs:");
        hs.setMaHS(sc.nextLine());
        System.out.println("Nhap ten:");
        hs.setTen(sc.nextLine());
        System.out.println("nhap Dia Chi:");
        hs.setDiaChi(sc.nextLine());
        System.out.println("Nhap Gioi Tinh");
        hs.setGioiTinh(sc.nextLine());
        System.out.println("Nhap Diem");
        hs.setDiemTongket(sc.nextFloat());
        sc.nextLine();
        
        list.add(hs);
        
        return "Nhap Thanh Cong";
    }
    
    public static void main(String[] args) throws IOException {
        ServerSocket mySocket = new ServerSocket(10);
        System.out.println("Openned Connection");
        Socket Client = mySocket.accept();
        System.out.println("Opened");

        FileReader fr = new FileReader("hocsinh.txt");
        BufferedReader br = new BufferedReader(fr);
        String a;
        while ((a = br.readLine()) != null) {
            HS hs = new HS();
            String[] result = a.split("\\$");
            hs.setMaHS(result[0]);
            hs.setTen(result[1]);
            hs.setDiaChi(result[2]);
            hs.setGioiTinh(result[3]);
            hs.setDiemTongket(Float.parseFloat(result[4]));
            list.add(hs);

        }
        br.close();
        fr.close();
        
        while (true) {
            DataInputStream dis = new DataInputStream(Client.getInputStream());
            DataOutputStream dos = new DataOutputStream(Client.getOutputStream());
            
            String rep = dis.readUTF();
            switch(rep){
                case "1":
                    dos.writeUTF(showAll());
                    break;
                case "2":
                    dos.writeUTF(show());
                    break;
                case "3":
                    String targetID = dis.readUTF();
                    int found=0;
                    for (HS t : list) {
                        if (t.getMaHS().equals(targetID)) {
                            found=1;
                            dos.writeUTF(found+"");
                            dos.writeUTF("Học sinh đã tồn tại, chuyển sang thao tác cập nhật điểm");
                            t.setDiemTongket(Float.parseFloat(dis.readUTF()));
                            dos.writeUTF("Cập nhật điểm thành công!");
                            
                        }
                    }
                    if (found==0){
                        dos.writeUTF(found+"");
                        dos.writeUTF("Chưa tồn tại, vui lòng nhập mới");
                        HS t = new HS();
                        t.setMaHS(targetID);
                        t.setTen(dis.readUTF());
                        t.setDiaChi(dis.readUTF());
                        t.setGioiTinh(dis.readUTF());
                        t.setDiemTongket(Float.parseFloat(dis.readUTF()));
                        
                        FileWriter fw = new FileWriter("hocsinh.txt",true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        
                        bw.write(t.toWrite());
                        bw.newLine();
                        
                        bw.close();
                        fw.close();
                        
                        list.add(t);
                        
                        
                        dos.writeUTF("Thêm thành công");
                    }
                    
            }
             
                
            
        }
    }
    
}
