
public class UserList {
    List<User> userList= new ArrayList<User>();


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
