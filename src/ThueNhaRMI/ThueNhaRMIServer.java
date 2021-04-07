/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThueNhaRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


/**
 *
 * @author er1nzz
 */
public class ThueNhaRMIServer extends UnicastRemoteObject implements ThueNhaRMIInterface {
    
    static ArrayList<House> dsn = new ArrayList<>();
    
    @Override
    public String search(String target) throws RemoteException {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getID().equalsIgnoreCase(target)) {
                output = dsn.get(i).toString();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }
    
    @Override
    public String showAll() throws RemoteException {
        int i;
        String output = "";
        for (i = 0; i < dsn.size(); i++) {
            output = output + dsn.get(i).toString() + "\n";
        }
        return output;
    }
    
    @Override
    public String showFree() throws RemoteException {
        int i;
        String output = "";
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getStatus().equals("Còn trống")) {
                output = output + dsn.get(i).toString() + "\n";
            }
        }
        return output;
        
    }
    
    @Override
    public String rentHouse(String targetID) throws RemoteException {
        int i, found = 0;
        String output = "Không tìm thấy";
        for (i = 0; i < dsn.size(); i++) {
            if (dsn.get(i).getID().equalsIgnoreCase(targetID)) {
                dsn.get(i).setStatus("Đã thuê");
                output = "Bạn đã thuê nhà có mã số: "+ dsn.get(i).getID();
                found = 1;
            }
        }
        if (found == 1) {
            return output;
        }
        return output;
    }

    public ThueNhaRMIServer() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(100);
        regis.rebind("Server", this);
        System.out.println("Openned");
    }
    
    public static void main(String[] args) throws RemoteException {
        House A = new House("1", "Nhà cấp 4", "Hà Nội", "Còn trống");
        House B = new House("2", "Nhà tầng 2", "Hà Nội", "Đã thuê");
        House C = new House("3", "Nhà biệt thự", "Cao Bằng", "Còn trống");
        dsn.add(A);
        dsn.add(B);
        dsn.add(C);
        
        ThueNhaRMIServer server = new ThueNhaRMIServer();
    }
    
    
    
    
}
