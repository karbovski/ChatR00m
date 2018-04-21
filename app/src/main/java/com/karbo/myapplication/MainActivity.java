package com.karbo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    EditText messageText;
    LinearLayout messagesLayout;

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

        // her må vi sette en BroadcastReciver (som gjør noe etter broadcast ble mottat) og en intenfilter for å filtrere etter broadcast
        registerReceiver(new MessageReceiver(), intentFilter);
        startService(new Intent(getApplicationContext(),ChatService.class));//Starter service. Riktig sted?
    }

    public void sendClick(View view)
    {
        String message = messageText.getText().toString();
        new sendMessage().execute(message);
    }



    private class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // her hentes det .putExtra fieldene fra intent fra broadcast
            String username = intent.getStringExtra("username");
            String messageText = intent.getStringExtra("messageText");

            TextView newMessage = new TextView(context);
            newMessage.setText(username + ": " + messageText);
            Log.i("Crab2",username+messageText);
            messagesLayout.addView(newMessage);

            // ny metode med XML mal, just testing
            addMessageOnScreen(username,messageText);

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

    private void addMessageOnScreen(String username, String messageString){
        // vi henter XML fra res/layout/message.xml og setter det in i parent layout, i det tilfellet>messagesLayout
        // den funker ikke enna
        View message = getLayoutInflater().inflate(R.layout.message, messagesLayout);

        EditText messageEditText = message.findViewById(R.id.messageTextView);
        EditText usernameEditText = message.findViewById(R.id.usernameTextView);

        usernameEditText.setText(username);
        messageEditText.setText(messageString);


    }



}
