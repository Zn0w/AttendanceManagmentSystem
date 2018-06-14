package com.znow.attendance_mng_system.server.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import java.io.IOException;

public class Server {

	int port;
	boolean running;

	ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	
	public Server(int port)
	{
		this.port = port;

		System.out.println("port: " + this.port);

		try
		{
			ServerSocket sSocket = new ServerSocket(port);

			running = true;

			while (running)
			{
				Socket cSocket = sSocket.accept();

				ClientHandler clientHandler = new ClientHandler(cSocket, this);
				if (clientHandler.connected)
				{
					Thread clientThread = new Thread(clientHandler);
					clientThread.start();
					
					clients.add(clientHandler);
					System.out.println("Client has connected");
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Failed to start the server (" + port + ")");
			e.printStackTrace();
		} 
	}

};