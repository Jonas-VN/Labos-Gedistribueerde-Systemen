package be.msec.labgrpc;

import java.util.ArrayList;
import java.util.logging.Logger;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class TutorialServer {
	private static final Logger logger = Logger.getLogger(TutorialServer.class.getName());
	
	private final int port;
	private final Server server;
	
	static ArrayList<Message> history;
	
	public TutorialServer(int port) {
		this(ServerBuilder.forPort(port), port);
	}
	
	public TutorialServer(ServerBuilder<?> serverBuilder, int port){
		this.port = port;
		if(history == null)
			history = new ArrayList<Message>();
		server = serverBuilder.addService(new ChatService()).build();
	}
	
	public void start() throws IOException{
		server.start();
		logger.info("Server started, listening on " + port);
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        @Override
	        public void run() {
	          // Use stderr here since the logger may has been reset by its JVM shutdown hook.
	          System.err.println("*** shutting down gRPC server since JVM is shutting down");
	          TutorialServer.this.stop();
	          System.err.println("*** server shut down");
	        }
	      });
	}

	  /** Stop serving requests and shutdown resources. */
	  public void stop() {
	    if (server != null) {
	      server.shutdown();
	    }
	  }

	  /**
	   * Await termination on the main thread since the grpc library uses daemon threads.
	   */
	  private void blockUntilShutdown() throws InterruptedException {
	    if (server != null) {
	      server.awaitTermination();
	    }
	  }

	public static void main(String[] args) throws Exception{
		TutorialServer server = new TutorialServer(50050);
	    server.start();
	    server.blockUntilShutdown();

	}
	
	private static class ChatService extends ChatGrpc.ChatImplBase {
		@Override
		public synchronized void sendMessage(Message message, StreamObserver<Empty> responseObserver){
			history.add(message);
			logger.info("Received message from " + message.getSender() + ": " + message.getMessage());
			responseObserver.onNext(Empty.newBuilder().build());
			responseObserver.onCompleted();

			notifyAll();
		}
		
		@Override
		public synchronized void receiveMessage(IsFirstMessage isFirstMessage, final StreamObserver<Message> responseObserver){
		try{
			if (isFirstMessage.getIsFirst()) {
				logger.info("Received request for chat history");
			} else {
				logger.info("Received request for new messages");
				wait();
			}
		}
		catch(InterruptedException ignored){}

            for (Message message : history) {
                responseObserver.onNext(message);
            }

			responseObserver.onCompleted();
        }

		@Override
		public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
			String requestedUsername = request.getUserName();
			boolean isUsernameTaken = isUsernameTaken(requestedUsername);

			if (isUsernameTaken) {
				logger.info("Username " + requestedUsername + " is already taken.");
			} else {
				logger.info("Username " + requestedUsername + " is available. Registering...");
				history.add(Message.newBuilder().setSender(requestedUsername).setMessage("has joined the chat").build());
			}

			responseObserver.onNext(RegisterResponse.newBuilder().setIsTaken(isUsernameTaken).build());
			responseObserver.onCompleted();

			notifyAll();
		}

		private boolean isUsernameTaken(String username) {
			// Add your logic to check if the username is taken (e.g., check against a list, database, etc.)
			// For demonstration purposes, I'm using a simple list to store registered usernames.
			return history.stream().anyMatch(message -> message.getSender().equals(username));
		}
	}

}
