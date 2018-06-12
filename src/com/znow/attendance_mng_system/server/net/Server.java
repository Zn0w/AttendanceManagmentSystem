package com.znow.attendance_mng_system.server.net;

public class Server {

	int port;
	
	public Server(int port)
	{
		this.port = port;

		System.out.println("port: " + this.port);
	}

};