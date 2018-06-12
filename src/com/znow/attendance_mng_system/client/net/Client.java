package com.znow.attendance_mng_system.client.net;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Client {

	String ip;
	int port;
	int id;

	BufferedReader reader;
	PrintWriter writer;

	public Client(String ip, int port, int id)
	{
		this.ip = ip;
		this.port = port;
		this.id = id;

		System.out.println("ip: " + this.ip);
		System.out.println("port: " + this.port);
		System.out.println("id: " + this.id);

		/*if (connectToServer())
		{
			System.out.println("Failed to connect to the server (" + ip + ", " + port + ")");
			System.out.println("1. Check if you entered the client's id correctly.");
			System.out.println("2. Check if the server you are trying to connect to is running at the moment.");
		}*/
	}

	boolean connectToServer()
	{
		return true;
	}

};