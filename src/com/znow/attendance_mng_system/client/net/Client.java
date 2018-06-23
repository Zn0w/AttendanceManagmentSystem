package com.znow.attendance_mng_system.client.net;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import com.znow.attendance_mng_system.comm_interface.*;

public class Client {

	//String ip;
	//int port;
	String id;

	BufferedReader reader;
	PrintWriter writer;

	boolean connected;

	public Client(String ip, int port, String id)
	{
		//this.ip = ip;
		//this.port = port;
		this.id = id;

		System.out.println("ip: " + ip);
		System.out.println("port: " + port);
		System.out.println("id: " + this.id);

		connected = connectToServer(ip, port);

		if (!connected)
		{
			System.out.println("Failed to connect to the server (" + ip + ", " + port + ")");
			System.out.println("1. Check if you entered the client's id correctly.");
			System.out.println("2. Check if the server you are trying to connect to is running at the moment.");

			return;
		}

		CommunicationInterface.clientMessage(writer, Message.REGISTER, id);

		while (connected)
		{
			try
			{
				String in_message = reader.readLine();
				if (in_message != null)
				{
					System.out.println("Server: " + in_message);
					CommunicationInterface.clientAnalyse(in_message, writer);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	boolean connectToServer(String ip, int port)
	{
		try
		{
			Socket cSocket = new Socket(ip, port);

			reader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			writer = new PrintWriter(cSocket.getOutputStream());

			return true;
		}
		catch (IOException e)
		{
			e.printStackTrace();

			return false;
		}
	}

};