package com.znow.communication_system.client.gui;

import javax.swing.*;

@SuppressWarnings("serial")
public class ClientWindow extends JFrame {
	
	public ClientWindow() {
		init();
	}
	
	private void init() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	public void drawLoginWindow() {
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		
		for (int i = 0; i < 10; i++) {
			JLabel label = new JLabel("Hello!");
			root.add(label);
		}
		
		setContentPane(root);
		pack();
	}
	
}
