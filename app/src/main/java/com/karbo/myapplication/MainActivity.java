package com.karbo.myapplication;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText messageText;
    LinearLayout messagesLayout;
    final static String LOGOUT="logout";
    Intent chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageText = findViewById(R.id.messageText);
        messagesLayout = findViewById(R.id.messagesLayout);

        // ny intent filter
        IntentFilter intentFilter = new IntentFilter();

        // vi ønsker kun intents markert som chatmessage
        intentFilter.addAction("chatmessage");

        IntentFilter intentFilterLogout=new IntentFilter();
        intentFilterLogout.addAction(LOGOUT);

        // her må vi sette en BroadcastReciver (som gjør noe etter broadcast ble mottat) og en intenfilter for å filtrere etter broadcast
        registerReceiver(new MessageReceiver(), intentFilter);
        registerReceiver(new DisconnectReceiver(),intentFilterLogout );
        chatService=new Intent(getApplicationContext(),ChatService.class);
        startService(chatService);//Starter service. Riktig sted?
    }

    @Override
    protected void onResume() {
        super.onResume();
        writeLogToScreen();
    }


    @Override
    public void onBackPressed() {//Sørger for at man ikke kan gå tilbake
        //super.onBackPressed();
    }

    public void sendClick(View view) {
        String message = messageText.getText().toString();
        addMessageOnScreen(SocketHandler.username+": "+message);
        new sendMessage().execute(message);
    }

    private class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // her hentes det .putExtra fieldene fra intent fra broadcast
            String username = intent.getStringExtra("username");
            String messageText = intent.getStringExtra("messageText");
            addMessageOnScreen(username+":"+messageText);


            // ny metode med XML mal, just testing
            //addMessageOnScreen(username,messageText);

        }
    }

    private  class DisconnectReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            logOut();
        }
    }

    private class sendMessage extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {

            SocketHandler.out.println(strings[0]);
            SocketHandler.out.flush();
            return null;
        }
    }

    private void logOut()
    {
        Intent intent =new Intent(this, LoginActivity.class);
        Toast toast = Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_LONG);
        toast.show();
        stopService(chatService);
        startActivity(intent);

    }

    private void addMessageOnScreen(String messageText) {


        TextView newMessage = new TextView(getApplicationContext());
        newMessage.setText(messageText);

        Log.i("Crab2",messageText);
        messagesLayout.addView(newMessage);
    }

    private void writeLogToScreen()
    {
        messagesLayout.removeAllViews();
        ArrayList<String> logArray=LogHandler.getAllMessagesFromLog(getApplicationContext());
        for(String s:logArray) addMessageOnScreen(s);
    }
}