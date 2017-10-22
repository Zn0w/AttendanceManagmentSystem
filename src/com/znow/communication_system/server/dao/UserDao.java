package com.znow.communication_system.server.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.znow.communication_system.server.dao.exceptions.UserNotFoundException;
import com.znow.communication_system.server.domain.User;

public class UserDao {
	
	public User getUser(String login, String password) throws UserNotFoundException {
		try {

            File f = new File("src/com/znow/communication_system/server/resources/users.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String line = "";

            while ((line = b.readLine()) != null) {
                String[] lineAttributes = line.split(";");
            	
            	if (lineAttributes[0].equals(login) && lineAttributes[1].equals(password))
            		return new User(lineAttributes[0], lineAttributes[1], lineAttributes[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		throw new UserNotFoundException();
	}
	
	public List<User> getUsers() {
		try {

			List<User> users = new ArrayList<User>();
			
			File f = new File("src/com/znow/communication_system/server/resources/users.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String line = "";

            while ((line = b.readLine()) != null) {
                String[] lineAttributes = line.split(";");
                users.add(new User(lineAttributes[0], lineAttributes[1], lineAttributes[2]));
            }
            
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		List<User> empty = new ArrayList<User>();
		return empty;
	}
	
}
