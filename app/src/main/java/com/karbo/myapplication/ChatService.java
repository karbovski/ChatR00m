package com.karbo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatService extends Service {

    //private static Socket chatSocket;

    public ChatService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //intent.getStringExtra() for å få ip og port fra activity
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        String rawMessage = SocketHandler.in.readLine();
                        int colonPosition = rawMessage.indexOf(':');

                        String username = rawMessage.substring(0, colonPosition);
                        String message = rawMessage.substring(colonPosition + 1);
                        sendChatMessageAsBroadcast(message,username);
                        if(username!="SERVER")LogHandler.saveMessageToLog(rawMessage,getApplicationContext());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent logOutIntent=new Intent();
                    Log.i("Crab2","Connection lost");
                    logOutIntent.setAction(MainActivity.LOGOUT);
                    sendBroadcast(logOutIntent);
                }

            }
        }).start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendChatMessageAsBroadcast(String messageText,String username) {
        // trenger ny intent for å sende broadcast
        Intent chatMessageIntent = new Intent();

        // setter opp action slik at vi kan filtrere det i en activity vha IntentFilter
        chatMessageIntent.setAction("chatmessage");

        // setter dataene i intenten slik at vi kan hente de i activity
        chatMessageIntent.putExtra("username", username);
        chatMessageIntent.putExtra("messageText", messageText);

        // bruker intent vi har opprettet og sender den i broadcast
        sendBroadcast(chatMessageIntent);
    }

    private void writeFileToScreen(String username,String message)
    {

    }
}