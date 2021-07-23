/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainRMI23_7;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class TrainRMI23_7Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry regis = LocateRegistry.getRegistry("localhost",10);
        TrainRMI23_7Interface client = (TrainRMI23_7Interface) regis.lookup("Server");
        
        System.out.println("Nhập chuỗi: ");
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Độ dài:"+client.dodai(sc.nextLine()));
    }
}
