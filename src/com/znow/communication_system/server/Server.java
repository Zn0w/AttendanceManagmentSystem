package com.znow.communication_system.server;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.PrintWriter;

class Server {
	
	Server(int port) {
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
	
}
