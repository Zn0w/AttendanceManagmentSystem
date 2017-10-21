package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.server.domain.MessageCategory;

@SuppressWarnings("serial")
class MainWindowFrame extends JPanel {
	
	MainWindowFrame(ClientWindow window, Client client) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel buttonPane = new JPanel();
		
		JButton showIncomingButton = new JButton("Show incoming messages");
		showIncomingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MessageListWindowFrame(window, client, MessageCategory.INCOMING);
			}
		});
		buttonPane.add(showIncomingButton);
		
		JButton showOutgoingButton = new JButton("Show outgoing messages");
		showOutgoingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MessageListWindowFrame(window, client, MessageCategory.OUTGOING);
			}
		});
		buttonPane.add(showOutgoingButton);
		
		add(buttonPane);
	}
	
}
