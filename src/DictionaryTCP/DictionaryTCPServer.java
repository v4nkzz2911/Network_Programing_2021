/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryTCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class DictionaryTCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(69);
            
            Socket client = server.accept();
            
            FileReader fr = new FileReader("tu_dien.txt");
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Word> dic = new ArrayList();
            
            String currentLine;
            while((currentLine=br.readLine())!=null){
                Word temp = new Word();
                String[] result = new String[2];
                result = currentLine.split("\\$");
                temp.setEn(result[0]);
                temp.setVi(result[1]);
                dic.add(temp);
            }
            
            
            while (true) {                
                DataInputStream dis = new DataInputStream(client.getInputStream());
                DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                String choice = dis.readUTF();
                String target;
                switch(choice){
                    case "1":
                        target = dis.readUTF();
                        for(int i=0; i<dic.size(); i++){
                            if (target.equals(dic.get(i).getEn())) {
                                dos.writeUTF(dic.get(i).getVi());
                            }
                        }
                    break;
                    case "2":
                        target = dis.readUTF();
                        for (int i = 0; i < dic.size(); i++){
                            if (target.equals(dic.get(i).getVi())){
                                dos.writeUTF(dic.get(i).getEn());
                            }
                        }
                    break;
                    case "3":
                        ObjectOutputStream oop = new ObjectOutputStream(client.getOutputStream());
                        oop.writeObject(dic);
                        
                        
                }
            }
        } catch (Exception e) {
        }
    }
    
}
