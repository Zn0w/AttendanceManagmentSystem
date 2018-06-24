package com.znow.attendance_mng_system.client.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
	            // #####################################################
				// TODO: Test this out
				client.disconnect();
				dispose();
				// #####################################################
	        }
	    });

		JPanel root = new JPanel();
		setContentPane(root);
		
		JLabel lblInstructions = new JLabel("Fill out the text fields to check in.");
		root.add(lblInstructions);

		pack();
		setVisible(true);
	}

};