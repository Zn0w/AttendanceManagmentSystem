package com.znow.communication_system.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.znow.communication_system.client.controllers.LoginWindowController;

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
		
		JLabel loginLabel = new JLabel("Enter your login:");
		root.add(loginLabel);
		
		JTextField loginTxt = new JTextField();
		root.add(loginTxt);
		
		JLabel passwordLabel = new JLabel("Enter your password:");
		root.add(passwordLabel);
		
		JTextField passwordTxt = new JTextField();
		root.add(passwordTxt);
		
		JButton connectButton = new JButton("Log in");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoginWindowController().onConnectButton();
			}
		});
		root.add(connectButton);
		
		setContentPane(root);
		pack();
	}
	
}
