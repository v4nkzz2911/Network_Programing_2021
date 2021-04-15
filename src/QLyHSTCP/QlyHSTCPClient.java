/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QLyHSTCP;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class QlyHSTCPClient {

    public static void menu() {
        System.out.println("1: Hiển thị toàn bộ danh sách học sinh");
        System.out.println("2: Hiển thị danh sách học bổng");
        System.out.println("3: Nhập thông tin học sinh");
        System.out.println("4: Thoát");
        System.out.println("Lựa chọn:");
    }

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 10);
        Scanner sc = new Scanner(System.in);

        int rep = 0;

        while (rep != 4) {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            DataOutputStream dos = new DataOutputStream(client.getOutputStream());

            menu();

            rep = sc.nextInt();
            sc.nextLine();
            dos.writeUTF(rep + "");
            switch (rep) {
                case 1:
                    System.out.println(dis.readUTF());
                    break;
                case 2:
                    System.out.println(dis.readUTF());
                    break;
                case 3:
                    System.out.println("Nhập mã học sinh");
                    dos.writeUTF(sc.nextLine());

                    String found = dis.readUTF();
                    if (found.equals("1")) {
                        System.out.println(dis.readUTF());
                        System.out.println("Nhập điểm ");
                        dos.writeUTF(sc.nextLine());
                        
                        System.out.println(dis.readUTF());
                    }
                    else{
                        System.out.println(dis.readUTF());
                        System.out.println("Nhập tên: ");
                        dos.writeUTF(sc.nextLine());
                        System.out.println("Nhập địa chỉ: ");
                        dos.writeUTF(sc.nextLine());
                        System.out.println("Nhập giới tính");
                        dos.writeUTF(sc.nextLine());
                        System.out.println("Nhập điểm tổng kết: ");
                        dos.writeUTF(sc.nextLine());
                        
                        System.out.println(dis.readUTF());
                    }
            }
        }

    }
}
