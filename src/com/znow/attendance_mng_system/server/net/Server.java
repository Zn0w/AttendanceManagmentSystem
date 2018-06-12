package com.znow.attendance_mng_system.server.net;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	int port;
	boolean running;
	
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
				Socket cSocket = sScoket.accept();
			}
		}
		catch (IOException e)
		{
			System.out.println("Failed to start the server (" + port + ")");d
			e.printStackTrace();
		} 
	}

};