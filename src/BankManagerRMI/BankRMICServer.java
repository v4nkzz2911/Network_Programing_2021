/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerRMI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 *
 * @author er1nzz
 */
public class BankRMICServer extends UnicastRemoteObject implements BankInt{
    
    static Account A = new Account("A",100000);
    static Account B = new Account("B", 200000);
    static ArrayList<Account> dstk = new ArrayList();
    
    @Override
    public String transfer(int amount, String source, String target)throws RemoteException{
        int i,j;
        for (i=0;i<dstk.size();i++){
            for(j=0;j<dstk.size();j++){
                if((dstk.get(i).getName().equals(source)) && (dstk.get(j).getName().equals(target)) ){
                    if (dstk.get(i).getBalance() < amount) {
                        return "Lỗi! Số dư không đủ!";
                    }
                    dstk.get(i).setBalance(dstk.get(i).getBalance()-amount);
                    dstk.get(j).setBalance(dstk.get(j).getBalance()+amount);
                }
            }
        }
        
        
        try {
            FileWriter fw = new FileWriter("HistoryTrans.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(source+" transfer to "+target+" "+amount);
            bw.newLine();
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thành công!"+ source + " transfer to " + target + " " + amount;
        
    }
    

    @Override
    public String withdrawal(int amount, String target) throws RemoteException{
        int i;
        for (i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getName().equals(target)) {
                if(dstk.get(i).getBalance()<amount){
                    return "Lỗi! Số dư không đủ!";
                }
                dstk.get(i).setBalance(dstk.get(i).getBalance()-amount) ;
                
            }
        }
        
        try {
            FileWriter fw = new FileWriter("HistoryTrans.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(target + " withdrawal " + amount);
            bw.newLine();
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thành công! "+target + " withdrawal " + amount;
    }

    @Override
    public int checkBalance(String target) throws RemoteException{
        int i,output=0;
        for (i = 0; i < dstk.size(); i++) {
            if (dstk.get(i).getName().equals(target)){
                output=dstk.get(i).getBalance();
                System.out.println(output);
            }
        }
        return output;
    }

    @Override
    public String checkHistory()throws RemoteException {
        try {
            FileReader fr = new FileReader("HistoryTrans.txt");
            BufferedReader br = new BufferedReader(fr);
            String temp,output="";
            while ((temp = br.readLine()) != null) {
                output=output+temp+"\n";
            }
            return output;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "lỗi!";
        
    }

    public BankRMICServer() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(3232);
        regis.rebind("Server", this);
        System.out.println("Openned connection!!");
    }
    
    public static void main(String[] args) throws RemoteException, IOException {
        dstk.add(A);
        dstk.add(B);
        BankRMICServer sv = new BankRMICServer();
        
        FileWriter fw = new FileWriter("HistoryTrans.txt");
        fw.close();
        
    }
    
    
}
