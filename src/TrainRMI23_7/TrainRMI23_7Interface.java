/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainRMI23_7;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface TrainRMI23_7Interface  extends Remote{
    public int dodai(String s) throws RemoteException;
}
