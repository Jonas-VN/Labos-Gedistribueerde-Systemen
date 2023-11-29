import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static Map<String, PrintWriter> clientWriters = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private String userName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    userName = in.readLine();

                    synchronized (clientWriters) {
                        if (!clientWriters.containsKey(userName)) {
                            clientWriters.put(userName, out);
                            break;
                        } else {
                            out.println("Username is already taken. Please choose a different username.");
                            out.println("Enter your username: ");
                        }
                    }
                }

                System.out.println(userName + " has joined the chat.");
                broadcast(userName + " has joined the chat.");

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(userName + ": " + message);
                    broadcast(userName + ": " + message);
                }
            } catch (IOException e) {
                // Do nothing, just quit
            } finally {
                if (userName != null) {
                    synchronized (clientWriters) {
                        System.out.println(userName + " left the chat.");
                        clientWriters.remove(userName);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters.values()) {
                    writer.println(message);
                }
            }
        }
    }

}