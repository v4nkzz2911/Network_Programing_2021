/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenToRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author er1nzz
 */
public class NguyenToRMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry client = LocateRegistry.getRegistry("localhost", 3232);
        NguyenToRMIInterface regis = (NguyenToRMIInterface) client.lookup("Server");

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số cần: ");
        System.out.println(regis.showAll(sc.nextInt()));
    }
}
