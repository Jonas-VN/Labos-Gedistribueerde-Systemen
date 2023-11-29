package be.msec.labgrpc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class TutorialClient {
    private static final Logger logger = Logger.getLogger(TutorialClient.class.getName());

    private static boolean isFirstMessage = true;
    private static String userName;
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 50050; // Change this to your gRPC server port
    private static JFrame frame;
    private static JTextArea messageArea;
    private static JTextField messageField;

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_ADDRESS, SERVER_PORT).usePlaintext().build();
        ChatGrpc.ChatStub asyncStub = ChatGrpc.newStub(channel);

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
                sendMessage(asyncStub);
            }
        });

        // Add ActionListener to messageField for Enter key
        messageField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(asyncStub);
            }
        });

        frame.setVisible(true);


        // Start a thread to listen for messages
        Thread messageListener = new Thread(() -> {
            try {
                while (true) {
                    updateMessageArea(asyncStub, isFirstMessage);
                    isFirstMessage = false;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        messageListener.start();
        userName = JOptionPane.showInputDialog(frame, "Enter your username: ");
        register(userName, asyncStub);
    }

    private static void register(String userName, ChatGrpc.ChatStub asyncStub) {
        try {
            // Call the gRPC method to register the username
            asyncStub.register(RegisterRequest.newBuilder().setUserName(userName).build(),
                    new StreamObserver<RegisterResponse>() {
                        @Override
                        public void onNext(RegisterResponse value) {
                            // Handle the response if needed
                            logger.info("Response: " + value.getIsTaken());
                            //if (value.getIsTaken()) {
                            //    String username = JOptionPane.showInputDialog(frame, "Username is already taken. Please choose a different username: ");
                            //    register(username, asyncStub);
                            //}
                        }

                        @Override
                        public void onError(Throwable t) {
                            // Handle errors if needed
                            logger.info("Error: " + t.getMessage());
                        }

                        @Override
                        public void onCompleted() {
                            // Handle completion if needed
                            logger.info("Completed");
                        }
                    });
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void sendMessage(ChatGrpc.ChatStub asyncStub) {
        try {
            // Call the gRPC method to send a message
            messageArea.append(userName + ": " + messageField.getText() + "\n");
            asyncStub.sendMessage(Message.newBuilder().setSender(userName).setMessage(messageField.getText()).build(),
                    new StreamObserver<Empty>() {
                        @Override
                        public void onNext(Empty value) {
                            // Handle the response if needed
                        }

                        @Override
                        public void onError(Throwable t) {
                            // Handle errors if needed
                        }

                        @Override
                        public void onCompleted() {
                            // Handle completion if needed
                        }
                    });
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex);
        } finally {
            messageField.setText("");
        }
    }

    private static void updateMessageArea(ChatGrpc.ChatStub asyncStub, boolean isFirstMessage) throws InterruptedException {
        CountDownLatch finishLatch = new CountDownLatch(1);

        asyncStub.receiveMessage(IsFirstMessage.newBuilder().setIsFirst(isFirstMessage).build(), new StreamObserver<Message>() {
            boolean first = true;
            @Override
            public void onNext(Message message) {
                // Update the UI with the received message
                logger.info("Received message from " + message.getSender() + ": " + message.getMessage());
                if (first) {
                    messageArea.setText("");
                    first = false;
                }
                messageArea.append(message.getSender() + ": " + message.getMessage() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                // Handle errors if needed
                logger.info("Error: " + t.getMessage());
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                // Handle completion if needed
                logger.info("Completed");
                finishLatch.countDown();
            }
        });

        // Receiving happens asynchronously
        finishLatch.await();
    }
}