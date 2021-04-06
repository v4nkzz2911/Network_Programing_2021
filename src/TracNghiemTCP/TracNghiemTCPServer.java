/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TracNghiemTCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author er1nzz
 */
public class TracNghiemTCPServer {

    static ArrayList<Quest> dsch = new ArrayList();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10);
        Socket client = server.accept();

        FileReader fr = new FileReader("quest.txt");
        BufferedReader br = new BufferedReader(fr);
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            String[] result = currentLine.split("\\$");
            Quest t = new Quest();
            t.setQuest(result[0]);
            t.setAnswer1(result[1]);
            t.setAnswer2(result[2]);
            t.setAnswer3(result[3]);
            t.setCorrect(result[4]);
            dsch.add(t);
        }

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());

        Random generator = new Random();
        int count = 0;
        String[] check = new String[3];
        while (count < 3) {
            int select = generator.nextInt(dsch.size());
            
            dos.writeUTF(dsch.get(select).toString());
            
            if (dis.readUTF().equals(dsch.get(select).getCorrect())) {
                check[count]="1";
            } else{
                check[count]="0";
            }
            
            dsch.remove(select);
            
            count++;
        }
        
        int score=0;
        for (int i = 0; i < 3; i++) {
            if(check[i].equals("1"))
                score++;
        }
        
        if (score>1){
            dos.writeUTF("Đỗ");
        }
        else dos.writeUTF("Trượt");
        
    }
}
