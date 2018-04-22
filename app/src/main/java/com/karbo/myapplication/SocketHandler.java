package com.karbo.myapplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketHandler {

    private static Socket socket;
    public static PrintWriter out;
    public static BufferedReader in;

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket, String username) {
        socket = socket;
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(username);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}