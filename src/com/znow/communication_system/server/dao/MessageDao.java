package com.znow.communication_system.server.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.znow.communication_system.server.domain.Message;

public class MessageDao {
	
	public List<Message> getIncomingMessages(String login) {
		try {
            File f = new File("src/com/znow/communication_system/server/resources/messages.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));

            List<Message> userMessages = new ArrayList<Message>();
            
            String line = "";

            while ((line = b.readLine()) != null) {
                String[] lineAttributes = line.split(";");
            	
            	if (lineAttributes[1].equals(login) || lineAttributes[1].equals("public"))
            		userMessages.add(new Message(lineAttributes[3], lineAttributes[4], 
            				lineAttributes[5], lineAttributes[6], lineAttributes[7], lineAttributes[2]
            				));
            }
            
            return userMessages;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		List<Message> empty = new ArrayList<Message>();
		return empty;
	}
	
	public List<Message> getOutgoingMessages(String login) {
		try {
            File f = new File("src/com/znow/communication_system/server/resources/messages.txt");
            BufferedReader b = new BufferedReader(new FileReader(f));

            List<Message> userMessages = new ArrayList<Message>();
            
            String line = "";

            while ((line = b.readLine()) != null) {
                String[] lineAttributes = line.split(";");
            	
            	if (lineAttributes[0].equals(login))
            		userMessages.add(new Message(lineAttributes[3], lineAttributes[4], 
            				lineAttributes[5], lineAttributes[6], lineAttributes[7], lineAttributes[2]
            				));
            }
            
            return userMessages;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		List<Message> empty = new ArrayList<Message>();
		return empty;
	}
	
}
