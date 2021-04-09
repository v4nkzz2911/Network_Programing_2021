/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenToRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface NguyenToRMIInterface extends Remote{
    public String showAll(int n) throws RemoteException;
    
}
