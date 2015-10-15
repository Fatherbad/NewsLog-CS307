import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.*;
import java.net.*;
import java.io.*;
import org.json.*;

// Some code based from
// https://github.com/CatalinPrata/funcodetuts/blob/master/JavaTCPServer/src/ro/kazy/tcp/TcpServer.java

public class Server extends Thread {
    private int portNumber;
    private boolean running;
    private ServerSocket serverSocket;

    private ArrayList<UserManager> connectedUsers;

    public Server ( int portNumber ) {
	connectedUsers = new ArrayList<UserManager>();
	this.portNumber = portNumber;
	running = false;
    }
    
    public void start() {
	running = true;
	try {
	    //create a server socket. A server socket waits for requests to 
	    //come in over the network.
	    serverSocket = new ServerSocket(portNumber);
	    while (running) {
		// create a loop and get all the incoming connections and 
		// create users with them
		System.out.println("Server: Waiting for a client ...");
		//create client socket... the method accept() listens 
		//for a connection to be made to this socket and accepts it.
		Socket client = serverSocket.accept();
		UserManager userManager = new UserManager(client);
		connectedUsers.add(userManager);
		Thread t = new Thread( userManager );
		t.start();
		System.out.println("Server: New client connected ...");
	    }

	} catch (Exception e) {
	    System.out.println("Server: Error");
	    e.printStackTrace();
	}
    }

    public static void main (String [] args) {
	int portNumber = 4444;
	Server server = new Server( portNumber );
	server.start();
    }
}
