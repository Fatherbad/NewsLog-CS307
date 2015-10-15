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
    private Socket socket;
    private User user;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public UserManager( Socket clientSocket ){
	try {
	    this.socket = clientSocket;
	    inputStreamReader = new InputStreamReader( clientSocket.getInputStream() ); 
	    bufferedReader = new BufferedReader ( inputStreamReader );
	} catch ( Exception ex ) {
	    ex.printStackTrace();
	}
    }

    public void run() {
	JSONObject jsonRequest; 
	String request;
	try {
	    while ( (request = bufferedReader.readLine() ) != null) {
		System.out.println( "Received request: " + request );
		try {
		    jsonRequest = new JSONObject( request );
		    System.out.println("Created JSON object: " + jsonRequest.get("email"));
		} catch ( JSONException ex ) {
		    ex.printStackTrace();
		}
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	} 
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
