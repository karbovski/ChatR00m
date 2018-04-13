package com.karbo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void joinChatOnClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);


    }

    public void ReadInt()
    {

        String name = "Dominik";
        //jeg vil ha den koden med
        int y=40;
        y++;
        int x = 5;

        //commit som skal til master

    }
}
