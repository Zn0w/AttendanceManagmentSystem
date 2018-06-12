package com.znow.attendance_mng_system.server;

import com.znow.attendance_mng_system.server.net.Server;

public class ServerMain {

	static Server server;
	
	public static void main(String[] args)
	{
		System.out.println("Server side\n");

		if (args.length != 1)
		{
			System.out.println("Use this framework: java -jar server.jar [server port]");
			return;
		}

		server = new Server(Integer.parseInt(args[0]));
	}
	
}
