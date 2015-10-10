import java.awt.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.Socket;


public class UserManager {
	Socket socket;
	User user;
	
	  public UserManager(Socket clientSocket ){
		  
		  this.socket=clientSocket;
		  user=new User();
		  
		  
	  }
	    


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
}
