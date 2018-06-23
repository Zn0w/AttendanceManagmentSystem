package com.znow.attendance_mng_system.client;

import com.znow.attendance_mng_system.client.net.Client;

public class ClientMain {
	
	static Client client;
	
	public static void main(String[] args)
	{
		System.out.println("Client side\n");

		if (args.length != 3)
		{
			System.out.println("Use this framework: java -jar client.jar [ip] [port] [client id]");
			return;
		}

		client = new Client(args[0], Integer.parseInt(args[1]), args[2]);
	}
	
}
