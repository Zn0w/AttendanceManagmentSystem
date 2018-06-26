package com.znow.attendance_mng_system.client.gui;

import javax.swing.*;
import java.awt.event.*;

import com.znow.attendance_mng_system.client.net.Client;
import com.znow.attendance_mng_system.comm_interface.*;

public class Window extends JFrame {

	Client client;

	public Window(Client client)
	{
		this.client = client;
	}
	
	public void startWindow()
	{
		setTitle("Attendance Management System Client");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent event) {
				client.disconnect();
				dispose();
	        }
	    });

		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setContentPane(root);
		
		JLabel lblInstructions = new JLabel("Please enter your Employee ID");
		root.add(lblInstructions);

		JTextField txtId = new JTextField(10);
		root.add(txtId);

		JButton btnRegister = new JButton("Check in");
		btnRegister.addActionListener(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// Send save request to the server via client class
				}
			}
		);
		root.add(btnRegister);

		pack();
		setVisible(true);
	}

};