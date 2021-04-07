/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThueNhaRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface ThueNhaRMIInterface extends Remote{
    public String search(String target) throws RemoteException;
    public String showAll() throws RemoteException;
    public String showFree() throws RemoteException; 
    public String rentHouse(String targetID) throws RemoteException;
    
}
