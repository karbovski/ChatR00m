package com.karbo.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;


public class LoginActivity extends AppCompatActivity {

    EditText serverIPText;
    EditText usernameText;
    EditText portText;
    boolean connected; //Dersom false, vil ikke Main Activity starte

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        serverIPText=findViewById(R.id.serverIPText);
        usernameText=findViewById(R.id.usernameText);
        portText=findViewById(R.id.portText);
        connected=false;

    }


    public void joinChatOnClick(View view) {

        new connectToHost().execute();
    }

    private void startMainActivity()
    {
        //TODO: Start Service
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private class connectToHost extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) { //Prøver å åpne socket
            try {
                SocketHandler.socket=new Socket(serverIPText.getText().toString(),Integer.parseInt(portText.getText().toString()));
                connected=true;
            } catch (IOException e) {
                Toast toast= Toast.makeText(getApplicationContext(),"Could not connect to server",Toast.LENGTH_LONG);
                toast.show();
                e.printStackTrace();
            }catch (NumberFormatException e) {
                Toast toast= Toast.makeText(getApplicationContext(),"Invalid Port Number",Toast.LENGTH_LONG);
                toast.show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {//Dersom doInBackground lykkes, startes ny activity. Ellers vises en feilmelding som Toast
            if(connected) {
                startMainActivity();
            }
           

            super.onPostExecute(aVoid);
        }
    }
}
