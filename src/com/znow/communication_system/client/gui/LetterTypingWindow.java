package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.znow.communication_system.client.Client;
import com.znow.communication_system.server.dao.UserDao;
import com.znow.communication_system.server.domain.User;

@SuppressWarnings("serial")
class LetterTypingWindow extends JFrame {
	
	LetterTypingWindow(Client client) {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JLabel helloLabel = new JLabel("Hello, type your letter here");
		root.add(helloLabel);
		
		JLabel toLabel = new JLabel("To:");
		root.add(toLabel);
		
		List<User> users = new UserDao().getUsers();
		String[] userOptions = new String[users.size() + 1];
		userOptions[0] = "Everyone";
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getLogin());
			userOptions[i + 1] = users.get(i).getName();
		}
		
		JComboBox toSelector = new JComboBox(userOptions);
		toSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String to = (String) toSelector.getSelectedItem();
			}
		});
		root.add(toSelector);
		
		JLabel subjectLabel = new JLabel("Subject:");
		root.add(subjectLabel);
		
		JTextField subjectTxt = new JTextField();
		root.add(subjectTxt);
		
		JLabel contentLabel = new JLabel("Content:");
		root.add(contentLabel);
		
		JTextArea contentTxt = new JTextArea();
		root.add(contentTxt);
		
		JButton sendButton = new JButton("Send");
		root.add(sendButton);
		
		setContentPane(root);
		pack();
		setVisible(true);
	}
	
}
