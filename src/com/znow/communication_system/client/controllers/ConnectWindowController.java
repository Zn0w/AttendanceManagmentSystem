package com.znow.communication_system.client.controllers;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.client.gui.ClientWindow;

public class ConnectWindowController {
	
	private ClientWindow window;
	private Client client;
	
	public ConnectWindowController(ClientWindow window, Client client) {
		this.window = window;
		this.client = client;
	}
	
	public void onConnectButton(String ip, int port) {
		if (client.connect(ip, port))
			window.drawLoginWindow();
		else
			window.notify("Couldn't connect to server.");
	}
	
}
