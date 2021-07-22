package TrainTCP22_7.Bai2;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
public class TrainTCP22_7Bai2Client {
    public static void menu(){
        System.out.println("1.Thêm cầu thủ");
        System.out.println("2.Hiển thị");
        System.out.println("3.Tính lương");
        System.out.println("4.Thoát");
        System.out.println("Lựa chọn: ");
    }
    
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",10);
        
        while (true) {            
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
            
            menu();
            Scanner sc = new Scanner(System.in);
            int chon=sc.nextInt();
            sc.nextLine();
            
            dos.writeUTF(chon+"");
            
            switch (chon){
                case 1:
                    System.out.println("Nhập Mã số: ");
                    String ID = sc.nextLine();
                    System.out.println("Nhập Tên: ");
                    String name = sc.nextLine();
                    System.out.println("Nhập Năm sinh: ");
                    String bornyear = sc.nextLine();
                    System.out.println("Nhập Vị trí: ");
                    String pos = sc.nextLine();
                    System.out.println("Nhập Lương cơ bản: ");
                    String defsalary = sc.nextLine();
                    
                    dos.writeUTF(ID+"$"+name+"$"+bornyear+"$"+pos+"$"+defsalary);
                    
                    System.out.println(dis.readUTF());
                    
                    break;
                case 2:
                    System.out.println(dis.readUTF());
                    
                    break;
                case 3:
                    System.out.println("Nhập Mã số cầu thủ cần tính: ");
                    dos.writeUTF(sc.nextLine());
                    System.out.println("Nhập số trận đã đá: ");
                    dos.writeUTF(sc.nextLine());
                    
                    System.out.println("Lương: "+dis.readUTF());
                    
                    break;
                case 4 :
                    System.exit(0);
                    
            }
        }
    }
}
