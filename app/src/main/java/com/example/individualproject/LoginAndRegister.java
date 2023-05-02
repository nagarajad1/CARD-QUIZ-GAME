package com.example.individualproject;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginAndRegister extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        Button registerButton = (Button) findViewById(R.id.button1);

        // Checking the state and enabling Login Button.
        if (getIntent().hasExtra("state")) {
            if (getIntent().getStringExtra("state").equals("success")) {
                registerButton.setEnabled(true);
            } else {
                registerButton.setEnabled(false);
            }
        } else {
            registerButton.setEnabled(false);
        }
    }

    public void goToRegisterView(View view) {
        Intent intent = new Intent(this, RegisterView.class);
        // starting a new activity.
        startActivity(intent);
    }
    public void goToQuizView(View view) {
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String username = pref.getString("email", "n/a");
        String password = pref.getString("password", "n/a");

        EditText editTextloginmail = (EditText) findViewById(R.id.editTextloginmail);
        String emailvallue = editTextloginmail.getText().toString();

        EditText editTextloginpassword = (EditText) findViewById(R.id.editTextloginpassword);
        String passwordvallue = editTextloginpassword.getText().toString();

        if (!emailvallue.equals(username)) {
            editTextloginmail.setError("Email not valid");
            return;
        }
        if (!passwordvallue.equals(password)) {
            editTextloginpassword.setError("Password not valid");
            return;
        }

        // creating a new intent
        Intent intent = new Intent(this, QuizHistory.class);
        // starting a new activity.
        startActivity(intent);
    }
}