package com.znow.communication_system.client.gui;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.znow.communication_system.client.Client;

@SuppressWarnings("serial")
class LetterTypingWindow extends JFrame {
	
	LetterTypingWindow(Client client) {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		JLabel helloLbl = new JLabel("Hello");
		root.add(helloLbl);
		
		setContentPane(root);
		pack();
		setVisible(true);
	}
	
}
