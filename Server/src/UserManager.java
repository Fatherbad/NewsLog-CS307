import java.awt.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.Socket;
import java.io.*;


public class UserManager implements Runnable{
	Socket socket;
	User user;
	BufferedReader reader;
	
	  public UserManager(Socket clientSocket ){
		try {
		    this.socket=clientSocket;
		    InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
		    reader = new BufferedReader(isReader);
		    user=new User();
		} catch ( Exception ex ) {
		    ex.printStackTrace();
		}
		  
		  
	  }

	  public void run() {
	      String message;
	      try {
		    while ( (message = reader.readLine()) != null) {
			System.out.println("read " + message);
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
