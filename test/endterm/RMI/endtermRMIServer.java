/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.RMI;

import endterm.UDP.hoadon;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author er1nzz
 */
public class endtermRMIServer extends UnicastRemoteObject implements endtermRMIInterface {
    static ArrayList<hoadon> ds = new ArrayList<>();
    @Override
    public Double thanhtoan(int sd) throws RemoteException {
        if (sd < 51) {
            hoadon t = new hoadon(sd, sd * 1.7);
            ds.add(t);
            thongke();
            return sd * 1.7;
        }
        if (sd > 50 && sd < 101) {
            hoadon t = new hoadon(sd, 50 * 1.7 + (sd - 50) * 2.0);
            ds.add(t);
            thongke();
            return 50 * 1.7 + (sd - 50) * 2.0;
        } else {
            hoadon t = new hoadon(sd, 50 * 1.7 + 50 * 2.0 + (sd - 100) * 2.5);
            ds.add(t);
            thongke();
            return 50 * 1.7 + 50 * 2.0 + (sd - 100) * 2.5;
        }
    }

    
    public void thongke()  {
        System.out.println("Thống kê");
        System.out.println("Chỉ số\tTiêu thụ");
        int i;
        for (i = 0; i < ds.size(); i++) {
            System.out.println(ds.get(i).toString());
        }
    }
    
    protected endtermRMIServer() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(10);
        regis.rebind("server", this);
    }
    
    public static void main(String[] args) throws RemoteException {
        endtermRMIServer sv = new endtermRMIServer();
    }
}
