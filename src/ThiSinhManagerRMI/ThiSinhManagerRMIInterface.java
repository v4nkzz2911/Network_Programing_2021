/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiSinhManagerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface ThiSinhManagerRMIInterface extends Remote{
    public String search(String target) throws RemoteException;
    public String showAll() throws RemoteException;
    public String showAdmitted() throws RemoteException;
    
}
