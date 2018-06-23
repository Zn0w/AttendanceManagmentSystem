package com.znow.attendance_mng_system.server.net;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

import com.znow.attendance_mng_system.comm_interface.*;

public class ClientHandler implements Runnable {

	Socket cSocket;
	
	Server server;

	BufferedReader reader;
	PrintWriter writer;

	boolean connected;

	ClientHandler(Socket cSocket, Server server)
	{
		try
		{
			this.cSocket = cSocket;
			this.server = server;

			reader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			writer = new PrintWriter(cSocket.getOutputStream());

			connected = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			connected = false;
		}
	}

	public void run()
	{
		while (connected)
		{
			try
			{
				String in_message = reader.readLine();
				if (in_message != null)
				{
					System.out.println("Client: " + in_message);
					CommunicationInterface.serverAnalyse(in_message, writer, this);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void disconnectClient()
	{
		connected = false;
		
		try
		{
			reader.close();
			writer.close();
			cSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		server.clients.remove(this);
	}

};