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

    public static void setSocket(Socket _socket, String username) {
        socket = _socket;
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println(username);//Sender brukernavn til server etter at alt annet er klart
            out.flush();
        } catch (IOException e){

        }

    }



}
