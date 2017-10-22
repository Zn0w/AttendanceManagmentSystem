package com.znow.communication_system.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.znow.communication_system.client.gui.ClientWindow;
import com.znow.communication_system.server.dao.UserDao;
import com.znow.communication_system.server.dao.exceptions.UserNotFoundException;
import com.znow.communication_system.server.domain.Message;
import com.znow.communication_system.server.domain.User;

public class Client implements Runnable {
	
	private boolean connected = false;
	private User user = null;
	
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
	
	public void disconnect() {
		System.out.println("disconnect from server (unimplemented)");
	}
	
	public void logIn(String login, String password, ClientWindow window) {
		writer.println("LOGIN;" + login + ";" + password);
		writer.flush();
		
		this.window = window;
	}
	
	public void sendNewMessage(Message message) {
		System.out.println(message.getDate());
		System.out.println(message.getFrom());
		System.out.println(message.getTo());
		System.out.println(message.getSubject());
		System.out.println(message.getContent());
		System.out.println(message.isRead());
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
						try {
							user = new UserDao().getUser(messageAttributes[1]);
						} catch (UserNotFoundException e) {
							e.printStackTrace();
						}
						
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
	
	public User getUser() {
		return user;
	}
	
}
