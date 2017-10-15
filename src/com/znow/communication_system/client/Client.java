package com.znow.communication_system.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {
	
	private boolean connected = false;
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	public boolean connect(String ip, int port) {
		try {
			Socket socket = new Socket(ip, port);
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			
			connected = true;
			
			Thread thread = new Thread(this);
			thread.start();
			
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void logIn(String login, String password) {
		writer.println("LOGIN;" + login + ";" + password);
		writer.flush();
	}

	@Override
	public void run() {
		while (connected) {
			String message;
			try {
				if ((message = reader.readLine()) != null) {
					System.out.println("Server: " + message);
					
					String[] messageAttributes = message.split(";");
					
					if (messageAttributes[0].equals("DISCONNECT")) {
						reader.close();
						writer.close();
						
						connected = false;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
