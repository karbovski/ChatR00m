package com.karbo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        messageText=findViewById(R.id.messageText);
        messagesLayout = findViewById(R.id.messagesLayout);

        // TODO sjekke om Socket er NULL, hvis ja > gå tilbake til pålogingside ??? kanskje
        //TODO startService
        // ny intent filter
        IntentFilter intentFilter = new IntentFilter();

        // vi ønsker kun intents markert som chatmessage
        intentFilter.addAction("chatmessage");

        // her må vi sette en BroadcastReciver (som gjør noe etter broadcast ble mottat) og en intenfilter for å filtrere etter broadcast
        registerReceiver(new MessageReceiver(), intentFilter);
    }

    public void sendClick(View view)
    {
        String message=messageText.getText().toString();
        new sendMessage().execute(message);
    }



    private class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO koden som skal utføres når en broadcast ble mottat

            // her hentes det .putExtra fieldene fra intent fra broadcast
            String username = intent.getStringExtra("username");
            String messageText = intent.getStringExtra("messageText");

            TextView newMessage = new TextView(context);
            newMessage.setText(username + ": " + messageText);
            messagesLayout.addView(newMessage);

            // TODO kode som oppretter ny booble på skjermen og seter inn username:messageText
            // something like
            // TextView nymelding = new Textview...
            // nymelding.setText(username + messageText)
            // newmessage = findbyidres(R.layout.message)
            //newmessage.SETTEXT(messageText + " : " username)
            // textAlignmen.set Right/lEFT (mine og noen andres meldinger)
            // layout.addView(new message)
            // nymelding > vis den på skjermen
        }
    }

    private class sendMessage extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... strings) {

            SocketHandler.out.println(strings[0]);
            SocketHandler.out.flush();
            return null;
        }
    }



}
