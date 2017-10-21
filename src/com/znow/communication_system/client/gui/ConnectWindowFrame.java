package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.znow.communication_system.client.Client;

@SuppressWarnings("serial")
class ConnectWindowFrame extends JPanel {
	
	ConnectWindowFrame(ClientWindow window, Client client) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel ipLabel = new JLabel("Enter server's ip:");
		add(ipLabel);
		
		JTextField ipTxt = new JTextField();
		add(ipTxt);
		
		JLabel portLabel = new JLabel("Enter server's port:");
		add(portLabel);
		
		JTextField portTxt = new JTextField();
		add(portTxt);
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (ipTxt.getText() == null || portTxt.getText() == null) {
					window.notify("All the fields must be filled!");
					return;
				}
				
				if (client.connect(ipTxt.getText(), Integer.valueOf(portTxt.getText())))
					window.drawLoginWindow();
				else
					window.notify("Couldn't connect to server.");
			}
		});
		add(connectButton);
	}
	
}
