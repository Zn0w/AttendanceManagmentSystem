package com.znow.attendance_mng_system.comm_interface;

import java.io.PrintWriter;

public class CommunicationInterface {

	public static void clientMessage(PrintWriter writer, Message messageType, String info)
	{
		String command;
		if (messageType == Message.REGISTER)
			command = "register";
		else if (messageType == Message.SAVE)
			command = "save";
		else
			return;

		writer.println(command + " " + info);
		writer.flush();
	}

	public static void serverMessage(PrintWriter writer, Message messageType)
	{
		String command;
		if (messageType == Message.REGISTER_SUCCESS)
			command = "register success";
		else if (messageType == Message.REGISTER_FAIL)
			command = "register fail";
		else if (messageType == Message.SAVE_SUCCESS)
			command = "save success";
		else if (messageType == Message.SAVE_FAIL)
			command = "save fail";
		else
			return;

		writer.println(command);
		writer.flush();
	}

	public static void clientAnalyse(String message, PrintWriter writer)
	{

	}

	public static void serverAnalyse(String message, PrintWriter writer)
	{
		String[] elements = message.split(" ");
		if (elements[0].equals("register"))
		{
			if (verifyClient(Integer.parseInt(elements[1])))
				serverMessage(writer, Message.REGISTER_SUCCESS);
			else
				serverMessage(writer, Message.REGISTER_FAIL);
		}
	}

	private static boolean verifyClient(int id)
	{
		return true;
	}

};