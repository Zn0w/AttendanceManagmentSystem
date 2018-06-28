package com.znow.attendance_mng_system.comm_interface;

import java.io.*;
import java.util.Date;
import java.text.*;

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

		else if (elements[0].equals("save"))
		{
			client.window.notifyOnSave(elements[1].equals("success"));
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

		else if (elements[0].equals("save"))
		{
			if (saveClient(elements[1]))
				serverMessage(writer, Message.SAVE_SUCCESS);
			else
				serverMessage(writer, Message.SAVE_FAIL);
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

	private static String verifyClientId(String id)
	{
		try
		{
            // Now absolute filepath is used, will be changed when release version comes out
			File employeesFile = new File("D://dev/AttendanceManagmentSystem/resources/employees.txt");

            BufferedReader fileReader = new BufferedReader(new FileReader(employeesFile));

            String line = "";
            while ((line = fileReader.readLine()) != null)
			{
                String[] elements = line.split(";");
				if (elements.length > 1 && id.equals(elements[0]))
					return elements[1];
            }

			return null;
        }
		catch (IOException e)
		{
            e.printStackTrace();
			return null;
        }
	}

	private static boolean saveClient(String id)
	{	
		String name = verifyClientId(id);
		if (name == null)
			return false;
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
            // Now absolute filepath is used, will be changed when release version comes out
			File savesFile = new File("D://dev/AttendanceManagmentSystem/resources/saves.txt");

            fw = new FileWriter(savesFile, true);
			bw = new BufferedWriter(fw);

			// Get current time
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date date = new Date();
			
			bw.write(id + " | " + name + " | " + dateFormat.format(date));
			bw.newLine();
			bw.flush();

			return true;
        }
		catch (IOException e)
		{
            e.printStackTrace();
			return false;
        }
		finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException e) {

				e.printStackTrace();

			}
		}
	}

};