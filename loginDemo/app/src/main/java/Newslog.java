/**
 * Created by mishamalik on 10/10/15.
 */
public class Newslog {


    DBhandler db= new DBhandler();
    UserList userList= new UserList();

    boolean userLogin(String email,String password)
    {
        return (userList.userLogin(email,password,db));


    }



}
