/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HayChonGiaDungRMI_23_7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author er1nzz
 */
public class HayChonGiaDung_Server extends UnicastRemoteObject implements HayChonGiaDung_Interface{
    
    static ArrayList<SanPham> listSP = new ArrayList();
    
    @Override
    public int randomChoice() throws RemoteException {
        Random generator = new Random();
        return generator.nextInt(listSP.size());
    }

    @Override
    public String checkPredict(int predict,int target) throws RemoteException {
        if (predict>listSP.get(target).getPrice()) {
            return "Giá dự đoán cao";
        } else 
            if(predict<listSP.get(target).getPrice()){
                return "Giá dự đoán thấp";
            }
        return "Dự đoán chính xác";
    }
    
    @Override
    public String currentProduct(int num) throws RemoteException {
        return listSP.get(num).getName();
    }
    
    public HayChonGiaDung_Server() throws RemoteException, AlreadyBoundException{
        Registry regis = LocateRegistry.createRegistry(10);
        regis.bind("Server", this);
        System.out.println("Opened");
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, RemoteException, AlreadyBoundException {
        FileReader fr = new FileReader("SanPham.txt");
        BufferedReader br = new BufferedReader(fr);

        String currentLine;

        while ((currentLine = br.readLine()) != null) {
            String[] result = currentLine.split("\\$");

            SanPham t = new SanPham();
            t.setID(result[0]);
            t.setName(result[1]);
            t.setPrice(Integer.parseInt(result[2]));
            

            listSP.add(t);
        }
        
        HayChonGiaDung_Server server = new HayChonGiaDung_Server();
    }

    
    
}
