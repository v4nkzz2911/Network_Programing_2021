/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerUDP;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class BankManagerUDPServer {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        ArrayList<Account> dstk = new ArrayList();
        
        FileReader fr = new FileReader("account.txt");
        BufferedReader br = new BufferedReader(fr);
        System.out.println("Start Read File");
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            Account temp = new Account();
            String[] result = new String[4];
            System.out.println(currentLine);
            result = currentLine.split("\\$");
            temp.setName(result[0]);
            temp.setUsername(result[1]);
            temp.setPass(result[2]);
            temp.setBalance(Integer.parseInt(result[3]));
            dstk.add(temp);
        }
        try {
            DatagramSocket server = new DatagramSocket(69);
            
            System.out.println("Opened connection");
            byte[] buffer = new byte[2048];

            
            while (true) {
                System.out.println("Read list account ok");

                System.out.println("Waiting for login");
                buffer = new byte[2048];
                DatagramPacket loginPacket = new DatagramPacket(buffer, buffer.length);
                server.receive(loginPacket);
                System.out.println("Received");

                String loginInfo = new String(loginPacket.getData(), 0, loginPacket.getLength());
                String[] tempInfo = loginInfo.split("\\$");
                String currentUser = tempInfo[0];
                String currentPass = tempInfo[1];
                System.out.println(currentUser+" "+currentPass);
                int i, found = 0;
                for (i = 0; i < dstk.size(); i++) {
                    if ((dstk.get(i).getUsername().equals(currentUser)) && (dstk.get(i).getPass().equals(currentPass))) {
                        buffer = new byte[2048];
                        buffer = "Login OK".getBytes();
                        DatagramPacket loginReply = new DatagramPacket(buffer, buffer.length, loginPacket.getAddress(), loginPacket.getPort());
                        server.send(loginReply);
                        found = 1;
                    }
                    

                }
                if (found == 0) {
                    buffer = new byte[2048];
                    buffer = "Login Failed".getBytes();
                    DatagramPacket loginReply = new DatagramPacket(buffer, buffer.length, loginPacket.getAddress(), loginPacket.getPort());
                    server.send(loginReply);

                }
            }

        } catch (Exception e) {
        }
    }

}
