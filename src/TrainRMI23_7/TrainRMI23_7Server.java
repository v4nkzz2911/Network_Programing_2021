/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainRMI23_7;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author er1nzz
 */
public class TrainRMI23_7Server extends UnicastRemoteObject implements TrainRMI23_7Interface{

    @Override
    public int dodai(String s) throws RemoteException {
        return s.length();
    }

    public TrainRMI23_7Server() throws RemoteException, AlreadyBoundException{
        Registry regis = LocateRegistry.createRegistry(10);
        regis.bind("Server", this);
        System.out.println("Opened");
    }
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        TrainRMI23_7Server server = new TrainRMI23_7Server();
    }
    
}
