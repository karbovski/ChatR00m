package com.karbo.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ny intent filter
        IntentFilter intentFilter = new IntentFilter();

        // vi ønsker kun intents markert som chatmessage
        intentFilter.addAction("chatmessage");

        // her må vi sette en BroadcastReciver (som gjør noe etter broadcast ble mottat) og en intenfilter for å filtrere etter broadcast
        registerReceiver(new MessageReceiver(), intentFilter);
    }



    private class MessageReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO koden som skal utføres når en broadcast ble mottat

            // her hentes det .putExtra fieldene fra intent fra broadcast
            String username = intent.getStringExtra("username");
            String messageText = intent.getStringExtra("messageText");

            // TODO kode som oppretter ny booble på skjermen og seter inn username:messageText
            // something like
            // TextView nymelding = new Textview...
            // nymelding.setText(username + messageText)
            // nymelding > vis den på skjermen
        }
    }



}
