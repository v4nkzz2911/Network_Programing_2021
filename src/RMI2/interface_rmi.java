package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interface_rmi extends Remote {
    public int sum(int a, int b) throws RemoteException, Exception;
    public int substract(int a, int b) throws RemoteException, Exception;
}
