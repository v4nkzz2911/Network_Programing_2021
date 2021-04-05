/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiSinhManagerRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class ThiSinhManagerRMIServer extends UnicastRemoteObject implements ThiSinhManagerRMIInterface{
    
    static ArrayList<ThiSinhRMI> dsts = new ArrayList();
    @Override
    public String search(String target) throws RemoteException {
        int i,found=0;
        String output="Không tìm thấy";
        for (i=0;i<dsts.size();i++){
            if (dsts.get(i).getName().equalsIgnoreCase(target)){
                output = dsts.get(i).toString();
                found=1;
            }
        }
        if (found==1) return output;
        return output;
    }

    @Override
    public String showAll() throws RemoteException {
        int i;
        String output="";
        for(i=0;i<dsts.size();i++){
            output = output + dsts.get(i).toString() + "\n";
        }
        return output;
    }

    @Override
    public String showAdmitted() throws RemoteException {
        int i, found = 0;
        String output = "";
        for (i = 0; i < dsts.size(); i++) {
            if (dsts.get(i).getTotal()>=20) {
                output =output + dsts.get(i).toString() + "\n";
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return "Không tìm thấy";
    }

    public ThiSinhManagerRMIServer() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(6969);
        regis.rebind("Server",this);
        System.out.println("Opened connection");
    }
    
    public static void main(String[] args) throws RemoteException {
        
        ThiSinhRMI tsA = new ThiSinhRMI("Chien", "6969369", "HN", "1999", 5, 5, 5);
        ThiSinhRMI tsB = new ThiSinhRMI("Tam", "123456778", "DN", "2000", 9, 9, 9);
        ThiSinhRMI tsC = new ThiSinhRMI("Cuong", "1265423", "HB", "1997", 8, 8, 8);
        dsts.add(tsA);
        dsts.add(tsB);
        dsts.add(tsC);
        
        
        ThiSinhManagerRMIServer server = new ThiSinhManagerRMIServer();
    }
    
    
    
    
}
