
public class NewsLog {


    DBhandler db= new DBhandler();
    UserList userList= new UserList();

    boolean userLogin(String email,String password)
    {
        return (userList.userLogin(email,password,db));


    }
	
}
