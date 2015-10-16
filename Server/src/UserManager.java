import jdk.nashorn.api.scripting.JSObject;
import org.apache.http.NameValuePair;

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
    private DBfetch dbfetch;

    public UserManager( Socket clientSocket ){
	try {
	    this.socket = clientSocket;
	    inputStreamReader = new InputStreamReader( socket.getInputStream() ); 
	    bufferedReader = new BufferedReader ( inputStreamReader );
	    dbfetch = new DBfetch();
	} catch ( Exception ex ) {
	    ex.printStackTrace();
	}
    }

    public void run() {
	JSONObject jsonRequest; 
	String request;
	ArrayList <NameValuePair> nameValuePairs = new ArrayList <NameValuePair>();	
	ArrayList <NameValuePair> nameValue = new ArrayList <NameValuePair>();
	try {
	    while ( (request = bufferedReader.readLine() ) != null) {
		System.out.println( "Received request: " + request );
		try {
		    jsonRequest = new JSONObject(request);
		    //System.out.println("Created JSON object: " + jsonRequest.get("email"));
		    if ( request.contains("email") == true && jsonRequest.get("email") != null && jsonRequest.get("password") != null) {
			String email = (String) jsonRequest.get("email");
			String pw = (String) jsonRequest.get("password");
			dbfetch.update( email, pw ,nameValuePairs);	
		    }
		    else {
			JSONArray array;
			String category = (String) jsonRequest.get("category");
			array = dbfetch.get( category, nameValue );
			int max = 0;
			int min = 5;
			int rand = (int) Math.floor(Math.random() * (max - min)) + min;
			System.out.println("RANDOM: " + rand);
			JSONObject n  = (JSONObject) array.get(rand);
			System.out.println(n.get("url"));
			PrintWriter writer = new PrintWriter( socket.getOutputStream() );
			writer.println( n.get("url") );
			writer.flush();
			//dbfetch.update( email, pw ,nameValuePairs);	
		    }

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
