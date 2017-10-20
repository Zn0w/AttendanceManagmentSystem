package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.server.dao.MessageDao;
import com.znow.communication_system.server.domain.Message;

@SuppressWarnings("serial")
class MainWindowFrame extends JPanel {
	
	MainWindowFrame(ClientWindow window, Client client) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel messagesPane = new JPanel();
		messagesPane.setLayout(new BoxLayout(messagesPane, BoxLayout.Y_AXIS));
		
		JPanel buttonPane = new JPanel();
		
		JButton showIncomingButton = new JButton("Show incoming messages");
		showIncomingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messagesPane.removeAll();
				
				List<Message> messages = new MessageDao().getIncomingMessages(client.getLogin());
				
				for (Message message : messages) {
					JButton messageButton = new JButton(message.getDate() + " " 
							+ message.getSubject() + "From " + message.getFrom());
					messageButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							window.drawMessageWindow(message);
						}
					});
					messagesPane.add(messageButton);
				}
				
				messagesPane.revalidate();
				window.pack();
			}
		});
		buttonPane.add(showIncomingButton);
		
		JButton showOutgoingButton = new JButton("Show outgoing messages");
		showOutgoingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messagesPane.removeAll();
				
				List<Message> messages = new MessageDao().getOutgoingMessages(client.getLogin());
				
				for (Message message : messages) {
					JButton messageButton = new JButton(message.getDate() + " " 
							+ message.getSubject() + "To " + message.getTo());
					messageButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							window.drawMessageWindow(message);
						}
					});
					messagesPane.add(messageButton);
				}
				
				messagesPane.revalidate();
				window.pack();
			}
		});
		buttonPane.add(showOutgoingButton);
		
		add(messagesPane);
		add(buttonPane);
	}
	
}
