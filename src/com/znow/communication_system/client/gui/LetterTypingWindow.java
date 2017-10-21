package com.znow.communication_system.client.gui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.znow.communication_system.client.Client;

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
