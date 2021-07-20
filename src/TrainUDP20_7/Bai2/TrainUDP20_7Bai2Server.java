/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainUDP20_7.Bai2;


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
public class TrainUDP20_7Bai2Server {
    
    static ArrayList<Sach> lib = new ArrayList();
    
    public static String hienthi(){
        String output="";
        int i;
        output = output + "Mã số\tTên\tNXB\tTổng\tMượn\n";
        for(i=0;i<lib.size();i++){
            output = output + lib.get(i).toString();
        }
        return output;
    }
    
    public static String muon(String target){
        String output="";
        
        int i;
        int found = -1;
        for(i=0;i<lib.size();i++){
            if (lib.get(i).getID().equalsIgnoreCase(target)){
                found = i;
                break;
            }
        }
        if(found == -1){
            output="Không tìm thấy quyển tương ứng";
            return output;
        }
        
        lib.get(found).setRent(lib.get(found).getRent()+1);
        output="Bạn đã mượn 1 cuốn "+lib.get(found).getName();
        return output;
    }
    
    public static String tra(String target) {
        String output = "";

        int i;
        int found = -1;
        for (i = 0; i < lib.size(); i++) {
            if (lib.get(i).getID().equalsIgnoreCase(target)) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            output = "Không tìm thấy quyển tương ứng";
            return output;
        }

        lib.get(found).setRent(lib.get(found).getRent() - 1);
        output = "Bạn đã trả 1 cuốn " + lib.get(found).getName();
        return output;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        FileReader fr = new FileReader("thuvien.txt");
        BufferedReader br = new BufferedReader(fr);
        
        String currentLine;
        while((currentLine=br.readLine())!=null){
            String[] result = currentLine.split("\\$");
            Sach t = new Sach();
            t.setID(result[0]);
            t.setName(result[1]);
            t.setPublisher(result[2]);
            t.setTotal(Integer.parseInt(result[3]));
            t.setRent(Integer.parseInt(result[4]));
            
            lib.add(t);
        }
        
        DatagramSocket server = new DatagramSocket(10);
        byte[] buffer = new byte[1024];
        while (true) {
            buffer = new byte[1024];
            DatagramPacket selectPacket = new DatagramPacket(buffer, buffer.length);
            server.receive(selectPacket);
            
            String chon = new String(selectPacket.getData(),0,selectPacket.getLength());
            
            DatagramPacket targetPacket;
            DatagramPacket resultPacket;
            switch (chon){
                case "1":
                    buffer = new byte[1024];
                    buffer = hienthi().getBytes();
                    resultPacket = new DatagramPacket(buffer, buffer.length, selectPacket.getAddress(), selectPacket.getPort());
                    server.send(resultPacket);
                    
                    break;
                case "2":
                    buffer = new byte[1024];
                    targetPacket = new DatagramPacket(buffer, buffer.length);
                    server.receive(targetPacket);
                    
                    buffer = new byte[1024];
                    buffer = muon(new String(targetPacket.getData(),0,targetPacket.getLength())).getBytes();
                    resultPacket = new DatagramPacket(buffer, buffer.length, selectPacket.getAddress(), selectPacket.getPort());
                    server.send(resultPacket);
                    
                    break;
                case "3":
                    buffer = new byte[1024];
                    targetPacket = new DatagramPacket(buffer, buffer.length);
                    server.receive(targetPacket);

                    buffer = new byte[1024];
                    buffer = tra(new String(targetPacket.getData(), 0, targetPacket.getLength())).getBytes();
                    resultPacket = new DatagramPacket(buffer, buffer.length, selectPacket.getAddress(), selectPacket.getPort());
                    server.send(resultPacket);

                    break;
                    
                case "4":
                    exit(0);
            
            }
            
            
        }
    }
}
