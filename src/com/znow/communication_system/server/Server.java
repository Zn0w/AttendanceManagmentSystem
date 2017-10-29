package com.znow.communication_system.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.znow.communication_system.server.dao.MessageDao;
import com.znow.communication_system.server.dao.UserDao;
import com.znow.communication_system.server.dao.exceptions.UserNotFoundException;
import com.znow.communication_system.server.domain.Message;
import com.znow.communication_system.server.domain.User;

public class Server {
	
	private List<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private boolean running = false;
	
	public Server(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			running = true;
			System.out.println("Server has been started");
			
			while (running) {
				Socket clientSocket = serverSocket.accept();
				
				ClientHandler clientHandler = new ClientHandler(clientSocket);
				
				Thread clientHandlerThread = new Thread(clientHandler);
				clientHandlerThread.start();
				
				System.out.println("Client has connected");
			}
		} catch (IOException e) {
			System.out.println("Failed to create a server");
			e.printStackTrace();
		}
	}
	
	private void connectClient(ClientHandler clientHandler) {
		clientHandlers.add(clientHandler);
		clientHandler.connected = true;
	}
	
	private void disconnectClient(ClientHandler clientHandler) {
		clientHandlers.remove(clientHandler);
		
		try {
			clientHandler.reader.close();
			clientHandler.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clientHandler.connected = false;
	}
	
	private void analyseMessage(String message, ClientHandler clientHandler) {
		String[] messageAttributes = message.split(";");
		
		if (messageAttributes[0].equals("LOGIN")) {
			try {
				clientHandler.user = new UserDao().getUser(messageAttributes[1], messageAttributes[2]);
				
				clientHandler.writer.println("VERIFIED;" + clientHandler.user.getLogin() + ";"
					+ clientHandler.user.getPassword() + ";" + clientHandler.user.getName()
				);
				clientHandler.writer.flush();
			} catch (UserNotFoundException e) {
				e.printStackTrace();
				
				clientHandler.writer.println("NOT VERIFIED;");
				clientHandler.writer.flush();
			}
		}
		else if (messageAttributes[0].equals("GET_MESSAGES_IN")) {
			List<Message> messages = new MessageDao().getIncomingMessages(messageAttributes[1]);
			
			for (Message m : messages) {
				clientHandler.writer.println(
						"LOAD_MESSAGE;" + 
						m.getDate() + ";" +
						m.getFrom() + ";" +
						m.getTo() + ";" +
						m.getSubject() + ";" +
						m.getContent() + ";" +
						m.isRead() + ";"
				);
				clientHandler.writer.flush();
			}
		}
	}
	
	private class ClientHandler implements Runnable {

		private Socket clientSocket;
		
		private BufferedReader reader;
		private PrintWriter writer;
		
		private User user;
		
		private boolean connected = false;
		
		private ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
			
			try {
				reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				writer = new PrintWriter(clientSocket.getOutputStream());
				
				connectClient(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				while (connected && running) {
					String message;
					if ((message = reader.readLine()) != null) {
						System.out.println("Client: " + message);
						analyseMessage(message, this);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
