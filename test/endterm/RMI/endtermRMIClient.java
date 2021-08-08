/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class endtermRMIClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry cl = LocateRegistry.getRegistry(10);
        endtermRMIInterface regis = (endtermRMIInterface) cl.lookup("server");
        System.out.println("Hóa đơn tiền điện");
        while (true) {
            System.out.println("Chỉ số điện tiêu thụ: ");
            int sd = new Scanner(System.in).nextInt();
            System.out.println("Hóa đơn của bạn: " + regis.thanhtoan(sd));
        }
    }
}
