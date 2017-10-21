package com.znow.communication_system.client.gui;

import javax.swing.*;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.server.domain.Message;

@SuppressWarnings("serial")
public class ClientWindow extends JFrame {
	
	private Client client;
	
	public ClientWindow(Client client) {
		this.client = client;
		
		init();
	}
	
	private void init() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void drawConnectWindow() {
		setContentPane(new ConnectWindowFrame(this, client));
		pack();
	}
	
	public void drawLoginWindow() {
		setContentPane(new LoginWindowFrame(this, client));
		pack();
	}
	
	public void drawMainWindow() {
		setContentPane(new MainWindowFrame(this, client));
		pack();
	}
	
	public void drawMessageWindow(Message message) {
		String messageContents = 	"Date: " + message.getDate() + "\n" +
				"From: " + message.getFrom() + "\n" +
				"To: "   + message.getTo()   + "\n" +
				"Subject: " + message.getSubject() + "\n" + "\n" +
				message.getContent();

		notify(messageContents);
	}
	
	public void notify(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
}
