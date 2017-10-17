package com.znow.communication_system.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.znow.communication_system.client.gui.ClientWindow;
import com.znow.communication_system.server.dao.MessageDao;
import com.znow.communication_system.server.domain.Message;

public class Client implements Runnable {
	
	private boolean connected = false;
	private String login = "";
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	private ClientWindow window;
	
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
	
	public void logIn(String login, String password, ClientWindow window) {
		writer.println("LOGIN;" + login + ";" + password);
		writer.flush();
		
		this.window = window;
	}

	@Override
	public void run() {
		try {
			while (connected) {
				String message;
				if ((message = reader.readLine()) != null) {
					System.out.println("Server: " + message);
					
					String[] messageAttributes = message.split(";");
					
					if (messageAttributes[0].equals("VERIFIED")) {
						login = messageAttributes[1];
						
						window.drawMainWindow();
					}
					else if (messageAttributes[0].equals("NOT VERIFIED")) {
						window.notify("Couldn't log in.");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
