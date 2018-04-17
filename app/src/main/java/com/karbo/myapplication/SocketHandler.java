package com.karbo.myapplication;

import java.net.Socket;

public class SocketHandler {

    public static Socket socket;
    public static boolean connected;

    public static Socket getSocket() {
        return socket;
    }

}
