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
        System.out.println("Thứ tự hộ\tChỉ số\tTiêu thụ");
        int i;
        for (i = 0; i < ds.size(); i++) {
            System.out.println(i+"\t\t"+ds.get(i).toString());
        }
        
        int[] d = new int[100000];

        for (i = 0; i < 100; i++) {
            d[i] = 0;
        }
        for (i = 0; i < ds.size(); i++) {
            d[ds.get(i).getChiso()]++;
        }

        System.out.println("Thống kê chi tiết số lượng SD số điện");

        System.out.println("Số điện sử dụng\tSố lượng");
        for (i = 0; i < d.length; i++) {
            if (d[i] > 0) {
                System.out.println(i + "\t\t" + d[i]);
            }

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
