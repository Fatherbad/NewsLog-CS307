package com.example.paul.demo;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by stephen on 10/14/15.
 */
public class SocketHandler {
    private static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }

    public static synchronized void closeSocket () {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
