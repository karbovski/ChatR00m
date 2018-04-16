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


        // TODO kode som skal validere input fra user og vise en toast hvis noe e galt

        // TODO kode som skal starte en service

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }

}
