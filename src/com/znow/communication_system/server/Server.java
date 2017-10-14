package com.znow.communication_system.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public Server(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server has been started");
			
			while (true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Client has connected");
			}
		} catch (IOException e) {
			System.out.println("Failed to create a server");
			e.printStackTrace();
		}
	}
	
	private class ClientHandler implements Runnable {

		private BufferedReader reader;
		private PrintWriter writer;
		
		private String name;
		private String login;
		private String password;
		
		@Override
		public void run() {
			
		}
		
	}
	
}
