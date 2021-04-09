/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KhachSanTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class KhachSannTCPClient {
    public static void menu() {
        System.out.println("1.Tìm kiếm phòng ");
        System.out.println("2.Hiển thị tất cả phòng");
        System.out.println("3.Đặt phòng");
        System.out.println("4.Cập nhật dữ liệu");
        System.out.println("5.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",10);
        Scanner sc = new Scanner(System.in);
        
        int rep=0;
        while(rep!=5){
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            menu();
            rep = sc.nextInt();
            sc.nextLine();
            switch(rep){
                case 1:
                    dos.writeUTF(rep+"");
                    System.out.println("Nhập mã số phòng cần tìm: ");
                    dos.writeUTF(sc.nextLine());
                    
                    System.out.println("Kết quả tìm kiếm");
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    System.out.println(dis.readUTF());
                    
                    break;
                case 2:
                    dos.writeUTF(rep+"");
                    System.out.println("Thông tin tất cả các phòng");
                    System.out.println("Mã số\tLoại\t\tĐịa chỉ\t\t\tTình trạng");
                    System.out.println(dis.readUTF());
                    
                    break;
                case 3:
                    dos.writeUTF(rep+"");
                    System.out.println("Nhập mã số phòng cần đặt: ");
                    dos.writeUTF(sc.nextLine());
                    
                    System.out.println(dis.readUTF());
                    break;
                case 4:
                    dos.writeUTF(rep + "");
                    System.out.println(dis.readUTF());
                    
                    
            }
        }
    }
    
}
