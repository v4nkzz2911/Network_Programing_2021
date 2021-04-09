/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenToRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author er1nzz
 */
public class NguyenToRMIServer extends UnicastRemoteObject implements NguyenToRMIInterface{

    public static int isPrime(int n) {
        int i;
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        for (i = 2; i <= Math.sqrt(n); i++) {
            if ((n % i) == 0) {
                return 0;
            }
        }
        return 1;
    }
    
    @Override
    public String showAll(int n) throws RemoteException {
        int i;
        String output ="";
        for (i=2;i<=n;i++){
            if (isPrime(i)==1){
                output = output+i+"  ";
            }
        }
        return output;
    }

    public NguyenToRMIServer() throws RemoteException {
        Registry regis = LocateRegistry.createRegistry(3232);
        regis.rebind("Server", this);
        System.out.println("Openned connection!!");
    }
    
    public static void main(String[] args) throws RemoteException {
        NguyenToRMIServer server = new NguyenToRMIServer();
    }
    
    
    
}
