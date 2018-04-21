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

        serverIPText = findViewById(R.id.serverIPText);
        usernameText = findViewById(R.id.usernameText);
        portText = findViewById(R.id.portText);
        connected = false;
    }

    public void joinChatOnClick(View view) {

        //TODO:Sjekk om username inneholder ':'
        new connectToHost().execute();
    }

    private void startMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class connectToHost extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) { //Prøver å åpne socket
            try {
                String ipAddress = serverIPText.getText().toString();
                int portNumber = Integer.parseInt(portText.getText().toString());

                Socket socket = new Socket(ipAddress, portNumber);
                String username = usernameText.getText().toString();
                SocketHandler.setSocket(socket,username);
                connected=true;
            } catch (IOException e) {
                showToast("Check your IP or port number!");
                e.printStackTrace();
            } catch (NumberFormatException e) {
                showToast("Invalid port number.");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            //Dersom doInBackground lykkes, startes ny activity. Ellers vises en feilmelding som Toast

            if(connected) startMainActivity();
            else showToast("Could not connect to server.");

            super.onPostExecute(aVoid);
        }
    }

    public void logoOnClick(View view){
        showToast("Utviklet av Ole og Dominik fra 16HKOM");
    }

    public void showToast(String toastText) {
        // en metode som kan gjenbrukes for enklere visning av toasts
        Toast toast = Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG);
        toast.show();
    }
}
