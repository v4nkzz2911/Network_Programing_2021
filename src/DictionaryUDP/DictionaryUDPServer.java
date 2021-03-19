/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DictionaryUDP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class DictionaryUDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(69);
            ArrayList<Word> dictionary = new ArrayList();
            byte[] buffer = new byte[2048];
            
            FileReader fr = new FileReader("tu_dien.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String currentLine;
            while((currentLine=br.readLine())!=null){
                Word temp = new Word();
                String[] result =new String[2];
                result = currentLine.split("\\$");
                temp.setEn(result[0]);
                temp.setVi(result[1]);
                dictionary.add(temp);
            }
            while (true) {                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);
                String choice = new String(request.getData(),0,request.getLength());
                DatagramPacket target;
                String targetWord;
                int i;
                int found = 0;
                switch(choice){
                    case "1":
                        buffer = new byte[2048];
                        target = new DatagramPacket(buffer, buffer.length);
                        server.receive(target);
                        targetWord = new String(target.getData(),0,target.getLength());
                        
                        for(i=0;i<dictionary.size();i++){
                            if (dictionary.get(i).getEn().equals(targetWord)) {
                                buffer = new byte[2048];
                                buffer = dictionary.get(i).getVi().getBytes();
                                DatagramPacket replyMean = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                                server.send(replyMean);
                                found=1;
                            }
                        }
                        if (found==0) {
                            buffer = new byte[2048];
                            buffer = "Khong tim thay".getBytes();
                            DatagramPacket notFound = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                            server.send(target);
                        }
                    case "2":
                        buffer = new byte[2048];
                        target = new DatagramPacket(buffer, buffer.length);
                        server.receive(target);
                        targetWord = new String(target.getData(), 0, target.getLength());
                        
                        for (i = 0; i < dictionary.size(); i++) {
                            if (dictionary.get(i).getVi().equals(targetWord)) {
                                buffer = new byte[2048];
                                buffer = dictionary.get(i).getEn().getBytes();
                                DatagramPacket replyMean = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                                server.send(replyMean);
                                found = 1;
                            }
                        }
                        if (found == 0) {
                            buffer = new byte[2048];
                            buffer = "Khong tim thay".getBytes();
                            DatagramPacket notFound = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());
                            server.send(target);
                        }
                }
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
