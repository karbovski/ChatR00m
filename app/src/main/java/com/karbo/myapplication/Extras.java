package com.karbo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Extras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
    }

    public void deleteAllMessagesOnClick(View view) {
        LogHandler.deleteAllMessages(getApplicationContext());
        showToast("All messages are deleted");
    }

    public void logOutOnClick(View view) {
        Intent intentActivity =new Intent(this, LoginActivity.class);
        Intent intentService = new Intent(this, ChatService.class);
        showToast("Disconnected");
        stopService(intentService);
        startActivity(intentActivity);
    }

    public void showToast(String toastText) {
        // en metode som kan gjenbrukes for enklere visning av toasts
        Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
        toast.show();
    }
}
