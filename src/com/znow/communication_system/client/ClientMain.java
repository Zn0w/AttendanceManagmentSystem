package com.znow.communication_system.client;

import com.znow.communication_system.client.gui.ClientWindow;

public class ClientMain {

	private ClientWindow window;
	private Client client;
	
	public static void main(String[] args) {
		ClientMain main = new ClientMain();
		main.start();
	}
	
	public void start() {
		client = new Client();
		window = new ClientWindow(client);
		
		window.drawConnectWindow();
	}

}
