package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.client.controllers.ConnectWindowController;
import com.znow.communication_system.client.controllers.LoginWindowController;

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
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JLabel ipLabel = new JLabel("Enter server's ip:");
		root.add(ipLabel);
		
		JTextField ipTxt = new JTextField();
		root.add(ipTxt);
		
		JLabel portLabel = new JLabel("Enter server's port:");
		root.add(portLabel);
		
		JTextField portTxt = new JTextField();
		root.add(portTxt);
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ipTxt.getText() == null || portTxt.getText() == null) {
					ClientWindow.this.notify("All the fields must be filled!");
					return;
				}
				
				new ConnectWindowController(ClientWindow.this, client).onConnectButton(
						ipTxt.getText(), Integer.valueOf(portTxt.getText())
						);
			}
		});
		root.add(connectButton);
		
		setContentPane(root);
		pack();
	}
	
	public void drawLoginWindow() {
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JLabel loginLabel = new JLabel("Enter your login:");
		root.add(loginLabel);
		
		JTextField loginTxt = new JTextField();
		root.add(loginTxt);
		
		JLabel passwordLabel = new JLabel("Enter your password:");
		root.add(passwordLabel);
		
		JPasswordField passwordTxt = new JPasswordField();
		root.add(passwordTxt);
		
		JButton connectButton = new JButton("Log in");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (loginTxt.getText() == null || passwordTxt.getPassword().toString() == null) {
					ClientWindow.this.notify("All the fields must be filled!");
					return;
				}
				
				new LoginWindowController(ClientWindow.this, client).onConnectButton(
						loginTxt.getText(), passwordTxt.getText()
						);
			}
		});
		root.add(connectButton);
		
		setContentPane(root);
		pack();
	}
	
	public void drawMainWindow() {
		JPanel root = new JPanel();
		
		JButton showIncomingButton = new JButton();
		
		setContentPane(root);
		pack();
	}
	
	public void notify(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
}
