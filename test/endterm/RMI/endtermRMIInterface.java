/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endterm.RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface endtermRMIInterface extends Remote{
    public Double thanhtoan(int sd) throws RemoteException;

   
}
