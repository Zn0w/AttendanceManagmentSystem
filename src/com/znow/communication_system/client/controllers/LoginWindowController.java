package com.znow.communication_system.client.controllers;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.client.gui.ClientWindow;

public class LoginWindowController {
	
	private ClientWindow window;
	private Client client;
	
	public LoginWindowController(ClientWindow window, Client client) {
		this.window = window;
		this.client = client;
	}
	
	public void onConnectButton(String ip, int port, String login, String password) {
		if (client.connect(ip, port)) {
			if (client.logIn(login, password))
				window.drawMainWindow();
			else
				window.notify("Couldn't log in");
		}
		else {
			window.notify("Couldn't connect to server.");
		}
	}
	
}
