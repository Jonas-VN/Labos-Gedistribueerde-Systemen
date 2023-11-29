import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static PrintWriter out;
    private static String userName;
    private static JFrame frame;
    private static JTextArea messageArea;
    private static JTextField messageField;

    public static void main(String[] args) {
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
                sendMessage();
            }
        });

        // Add ActionListener to messageField for Enter key
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        frame.setVisible(true);

        // Initialize socket and streams
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Get username
            userName = JOptionPane.showInputDialog(frame, "Enter your username:");

            if (userName != null && !userName.trim().isEmpty()) {
                out.println(userName);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username. Exiting...");
                System.exit(0);
            }

            // Start a thread to listen for messages
            Thread messageListener = new Thread(() -> {
                try {
                    String message;
                    while ((message = in.readLine()) != null) {
                        updateMessageArea(message);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            messageListener.start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void sendMessage() {
        String message = messageField.getText();
        if (!message.trim().isEmpty()) {
            out.println(message);
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
