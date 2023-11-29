package Client;

import Interfaces.Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatClient {

        private static String userName;
        private static final String SERVER_ADDRESS = "localhost";
        private static final int SERVER_PORT = 1099;
        private static JFrame frame;
        private static JTextArea messageArea;
        private static JTextField messageField;

        public static void main(String[] args) throws RemoteException, NotBoundException {
            Registry myRegistry = LocateRegistry.getRegistry(SERVER_ADDRESS, SERVER_PORT);
            Chat chatServer = (Chat) myRegistry.lookup("ChatServer");

            frame = new JFrame("Chat Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            messageArea = new JTextArea();
            messageArea.setEditable(false);
            frame.add(new JScrollPane(messageArea), BorderLayout.CENTER);

            messageField = new JTextField();
            JButton sendButton = new JButton("Send");
            JPanel inputPanel = new JPanel(new BorderLayout());
            inputPanel.add(messageField, BorderLayout.CENTER);
            inputPanel.add(sendButton, BorderLayout.EAST);
            frame.add(inputPanel, BorderLayout.SOUTH);

            sendButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendMessage(chatServer);
                }
            });

            // Add ActionListener to messageField for Enter key
            messageField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    sendMessage(chatServer);
                }
            });

            frame.setVisible(true);

            userName = JOptionPane.showInputDialog(frame, "Enter your username: ");
            while (true) {
                try {
                    chatServer.register(userName);
                    break;
                } catch (RemoteException e) {
                    userName = JOptionPane.showInputDialog(frame, "Username is already taken. Please choose a different username: ");
                }
            }

            while (true) {
                try {
                    updateMessageArea(chatServer.receive());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        private  static void sendMessage(Chat chatServer) {
            try {
                chatServer.send(userName, messageField.getText());
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            } finally {
                messageField.setText("");
            }
        }
    private static void updateMessageArea(String message) {
        messageArea.append(message + "\n");

        // Automatically scroll to the bottom
        JScrollBar verticalScrollBar = ((JScrollPane) messageArea.getParent().getParent()).getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
    }
}

