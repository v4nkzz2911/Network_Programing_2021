/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TracNghiemTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class TracNghiemTCPClient {

    public static void main(String[] args) throws IOException {
        Socket myConnection = new Socket("127.0.0.1", 10);

        DataInputStream dis = new DataInputStream(myConnection.getInputStream());
        DataOutputStream dos = new DataOutputStream(myConnection.getOutputStream());

        Scanner sc = new Scanner(System.in);
        String t = "n";
        while (t.equals("n")) {
            System.out.println("Ready?");
            t = sc.nextLine();
        }
        int count = 0;
        String[] answer = new String[3];

        while (count < 3) {
            System.out.println(dis.readUTF());
            System.out.print("Lựa chọn: ");
            answer[count] = sc.nextLine();
            dos.writeUTF(answer[count] + "");
            count++;
        }
        System.out.println(dis.readUTF());
        
        
        

    }
}
