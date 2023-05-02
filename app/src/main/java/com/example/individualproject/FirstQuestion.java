package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_question);
    }

    public void showDialogs(int id) {
//        Dialog Init
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                int score = 0;
                String firstanswer = "Not Correct";
//                Inccrease the score
                if (R.id.radio_linux == id) {
                    score = score + 1;
                    firstanswer = "Correct";
                }
//                Setting score in history
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FirstQuestion.this);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("firstanswer", firstanswer);
                edit.apply();

                Intent intent = new Intent(FirstQuestion.this, SecondQuestion.class);
                intent.putExtra("score", score);
                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_windows:
                if (checked) {
                    showDialogs(R.id.radio_windows);
                    break;
                }
            case R.id.radio_linux:
                if (checked) {
                    showDialogs(R.id.radio_linux);
                    break;
                }
            case R.id.radio_ios:
                if (checked) {
                    showDialogs(R.id.radio_ios);
                    break;
                }
            case R.id.radio_macos:
                if (checked) {
                    showDialogs(R.id.radio_macos);
                    break;
                }
        }
    }
}