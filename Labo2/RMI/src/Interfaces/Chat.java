package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Chat extends Remote {
    void send(String user, String message) throws RemoteException;
    String receive() throws RemoteException, InterruptedException;
    void register(String name) throws RemoteException;

}
