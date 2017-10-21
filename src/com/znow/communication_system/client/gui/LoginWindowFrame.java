package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.znow.communication_system.client.Client;

@SuppressWarnings("serial")
class LoginWindowFrame extends JPanel {
	
	LoginWindowFrame(ClientWindow window, Client client) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel loginLabel = new JLabel("Enter your login:");
		add(loginLabel);
		
		JTextField loginTxt = new JTextField();
		add(loginTxt);
		
		JLabel passwordLabel = new JLabel("Enter your password:");
		add(passwordLabel);
		
		JPasswordField passwordTxt = new JPasswordField();
		add(passwordTxt);
		
		JButton connectButton = new JButton("Log in");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (loginTxt.getText() == null || passwordTxt.getPassword().toString() == null) {
					window.notify("All the fields must be filled!");
					return;
				}
				
				client.logIn(loginTxt.getText(), passwordTxt.getText(), window);
			}
		});
		add(connectButton);
	}
	
}
