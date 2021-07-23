/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HayChonGiaDungRMI_23_7;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface HayChonGiaDung_Interface extends Remote{
    public int randomChoice() throws RemoteException;
    public String checkPredict(int predict,int target) throws RemoteException;
    public String currentProduct(int num) throws RemoteException;
}
