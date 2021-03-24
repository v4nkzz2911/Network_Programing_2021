/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import static java.lang.System.exit;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.chart.PieChart;

/**
 *
 * @author er1nzz
 */
public class DictionaryTCPClient {
    public static void menu(){
        System.out.println("1.Anh->Viet");
        System.out.println("2.Viet->Anh");
        System.out.println("3.Hien toan bo tu dien");
        System.out.println("4.Exit");
        System.out.println("Choice: ");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Word> dic = new ArrayList();
        try {
            Socket mySocket = new Socket("127.0.0.1",69);
            
            while (true){              
                DataInputStream dis = new DataInputStream(mySocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(mySocket.getOutputStream());
                menu();
                String rep = sc.nextLine();
                String target;
                switch(rep){
                    case "1":
                        dos.writeUTF(rep);
                        System.out.println("Nhập từ cần tra sang tiếng Việt: ");
                        target = sc.nextLine();
                        dos.writeUTF(target);
                        System.out.println(dis.readUTF());
                        break;
                    case "2":
                        dos.writeUTF(rep);
                        System.out.println("Nhập từ cần tra sang tiếng Anh: ");
                        target = sc.nextLine();
                        dos.writeUTF(target);
                        System.out.println(dis.readUTF());
                        break;
                    case "3":
                        dos.writeUTF(rep);
                        System.out.println("Tất cả từ trong từ điển là: ");
                        ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                        dic = (ArrayList<Word>) ois.readObject();
                        
                        for (Word current : dic){
                            System.out.println(current.toString());
                        }
                        break;
                    case "4":
                        exit(0);
                
                        
                        
                }
                
            }
            
        } catch (Exception e) {
        }
    }
    
}
