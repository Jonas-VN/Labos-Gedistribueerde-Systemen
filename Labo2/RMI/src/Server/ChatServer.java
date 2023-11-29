package Server;

import Interfaces.Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatServer extends UnicastRemoteObject implements Chat {
    private final ArrayList<String> users = new ArrayList<>();
    private final ArrayList<String> messages = new ArrayList<>();

    protected ChatServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void send(String user, String message) throws RemoteException {
        String formattedMessage = user + ": " + message;
        messages.add(formattedMessage);
        System.out.println(formattedMessage);
        notifyAll();
    }

    public synchronized String receive() throws RemoteException, InterruptedException {
        wait();
        return messages.getLast();
    }

    @Override
    public synchronized void register(String name) throws RemoteException {
        if (users.contains(name)) {
            throw new RemoteException("User already exists");
        }
        else {
            users.add(name);
            this.messages.add(name + " has joined the chat");
            System.out.println(name + " has joined the chat");
        }
        notifyAll();
    }
}
