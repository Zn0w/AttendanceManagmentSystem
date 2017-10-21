package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.server.dao.MessageDao;
import com.znow.communication_system.server.domain.Message;
import com.znow.communication_system.server.domain.MessageCategory;

@SuppressWarnings("serial")
public class MessageListWindowFrame extends JFrame {
	
	public MessageListWindowFrame(ClientWindow window, Client client, MessageCategory category) {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JPanel messagesPane = new JPanel();
		messagesPane.setLayout(new BoxLayout(messagesPane, BoxLayout.Y_AXIS));
		
		List<Message> messages = null;
		if (category == MessageCategory.INCOMING) {
			messages = new MessageDao().getIncomingMessages(client.getLogin());
			setTitle("Incoming messages");
		}
		else if (category == MessageCategory.OUTGOING) {
			messages = new MessageDao().getOutgoingMessages(client.getLogin());
			setTitle("Outgoing messages");
		}
		
		for (Message message : messages) {
			JButton messageButton = new JButton(message.getDate() + " " 
					+ message.getSubject() + "From: " + message.getFrom() + " To: " + message.getTo());
			messageButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					window.drawMessageWindow(message);
				}
			});
			messagesPane.add(messageButton);
		}
		
		setContentPane(messagesPane);
		
		pack();
		setVisible(true);
	}
	
}
