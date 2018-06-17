public class CommunicationInterface {

	public void clientMessage(PrintWriter writer, Message messageType, String info)
	{
		String command;
		if (messageType == REGISTER)
			command = "register";
		else if (messageType == SAVE)
			command = "save";
		else
			return;

		writer.println(command + " " + info);
		writer.flush();
	}

	public void serverMessage(PrintWriter writer, Message messageType)
	{

	}

	public void clientAnalyse(String message, PrintWriter writer)
	{

	}

	public void serverAnalyse(String message, PrintWriter writer)
	{

	}

};