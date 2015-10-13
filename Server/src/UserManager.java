import jdk.nashorn.api.scripting.JSObject;

import java.awt.List;
import java.io.*;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.System;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.Socket;
import org.json.*;



public class UserManager implements Runnable{
    Socket socket;
    User user;
    //ObjectOutputStream objectOutputStream;
    InputStreamReader is;
    

    public UserManager(Socket clientSocket ){
	try {
	    this.socket=clientSocket;
	    //objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	    is = new InputStreamReader(socket.getInputStream() );
	    user=new User();
	} catch ( Exception ex ) {
	    ex.printStackTrace();
	}


    }

    public void run() {
	//String message;
	JSONObject jsonObject = new JSONObject();	
	String userInfo;
	BufferedReader bf = new BufferedReader(is);
	try {
	    while ( (userInfo =  bf.readLine() ) != null) {
		System.out.println(userInfo);
		userInfo = userInfo.substring(userInfo.indexOf('{'),userInfo.length());
		System.out.println(userInfo);
//		System.out.println("READ " + userInfo);
		//jsonObject = new JSONObject(userInfo);
		//System.out.println("read" + jsonObject.get("email"));
	    }
	    //while ( (jsObject = objectInputStream.readObject() ) != null) {
//		System.out.println("read" + jsObject.getString("email");
//	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} 


	/*
	   public  boolean userLogin(String email,String password, DBhandler db)
	   {

	   User u=db.getUser(email);

	   if(u.equals(null))
	   {
	   return false;
	   }


	   return true;
	   }

	   public boolean addUser(String email, String password)
	   {
	// check if user already exists
	//if not then add to the database by sending request to DBhandler
	return true;
	}
	*/
    }
}
