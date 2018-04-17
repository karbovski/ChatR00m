package com.karbo.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.Socket;


public class ChatService extends Service {

    private static Socket chatSocket;



    public ChatService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //intent.getStringExtra() for å få ip og port fra activity

        //TODO: her kan vi legge kode som lytter på socketen, og det kan vi putte i en vanlig java thread

        // TODO multithread: by default kjøres service i samme thread med UI så vi må ha flere threads to do it legit

        /* while(true){
        socket.listen()
        readline > sendChatMessageAsBroadcast
        }
         */


        // START_STICKY gjør at service kjører i evighet, ikke spør meg hvorfor
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void sendChatMessageAsBroadcast(String username, String messageText){

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
}
