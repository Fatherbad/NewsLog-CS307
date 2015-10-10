
public class DBhandler {
	List<User> myList= new ArrayList<User>();

    User getUser(String email)
    {

        //read from database and return that user object
        // hard coding for now

        List<User> myList1= new ArrayList<User>();
        User u= new User("subramad@purdue.edu","abc");
        myList1.add(u);
        User m= new User("malik16@purdue.edu","abc");
        myList1.add(m);
        User s= new User("pheldrin@purdue.edu","abc");
        myList1.add(s);

        for(int i=0;i<myList1.size();i++)
        {
            if( myList1.get(i).getEmail().equalsIgnoreCase("email"))
            {
                return myList1.get(i);
            }
        }

        return null;

    }



}
