package com.znow.attendance_mng_system.server.net;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;

class ClientHandler {

	Socket cSocket;

	BufferedReader reader;
	PrintWriter writer;

	ClientHandler(Socket cSocket)
	{
		this.cSocket = cSocket;
	}

};