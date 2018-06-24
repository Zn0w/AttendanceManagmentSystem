package com.znow.attendance_mng_system.comm_interface;

import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.znow.attendance_mng_system.server.net.ClientHandler;
import com.znow.attendance_mng_system.client.net.Client;

public class CommunicationInterface {

	public static void clientMessage(PrintWriter writer, Message messageType, String info)
	{
		String command;
		if (messageType == Message.REGISTER)
			command = "register";
		else if (messageType == Message.SAVE)
			command = "save";
		else if (messageType == Message.DISCONNECT)
			command = "disconnect";
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

	public static void clientAnalyse(String message, PrintWriter writer, Client client)
	{
		String[] elements = message.split(" ");
		if (elements[0].equals("register"))
		{
			if (elements[1].equals("success"))
			{
				client.window.startWindow();
			}
			else if (elements[1].equals("fail"))
			{
				client.disconnect();
			}
		}
	}

	public static void serverAnalyse(String message, PrintWriter writer, ClientHandler clientHandler)
	{
		String[] elements = message.split(" ");
		
		if (elements[0].equals("register"))
		{
			if (verifyClient(elements[1]))
				serverMessage(writer, Message.REGISTER_SUCCESS);
			else
				serverMessage(writer, Message.REGISTER_FAIL);
		}

		else if (elements[0].equals("disconnect"))
		{
			clientHandler.disconnectClient();
		}
	}

	private static boolean verifyClient(String id)
	{
		try
		{
            // Now absolute filepath is used, will be changed when release version comes out
			File file = new File("D://dev/AttendanceManagmentSystem/resources/clients.txt");

            BufferedReader fileReader = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = fileReader.readLine()) != null)
			{
                if (id.equals(line))
					return true;
            }

			return false;
        }
		catch (IOException e)
		{
            e.printStackTrace();
			return false;
        }
	}

};