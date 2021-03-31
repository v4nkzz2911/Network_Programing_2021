/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author er1nzz
 */
public interface BankInt extends Remote{
    public String transfer(int amount,String source, String target)throws RemoteException;
    public String withdrawal(int amount, String target)throws RemoteException;
    public int checkBalance(String target)throws RemoteException;
    public String checkHistory()throws RemoteException;
}
