package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SecondQuestion extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_question);

        score = getIntent().getIntExtra("score", 0);
    }

    public void showDialogs(int id) {
//        Dialog Init
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String secondanswer = "Not Correct";
                //                Inccrease the score
                if (R.id.radio_drawable == id) {
                    score = score + 1;
                    secondanswer = "Correct";
                }
                //                Setting score in history
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SecondQuestion.this);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("secondanswer", secondanswer);
                edit.apply();


                Intent intent = new Intent(SecondQuestion.this, ThirdQuestion.class);
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

    public void onRadioButtonClicked2(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_android:
                if (checked) {
                    showDialogs(R.id.radio_android);
                    break;
                }
            case R.id.radio_drawable:
                if (checked) {
                    showDialogs(R.id.radio_drawable);
                    break;
                }
            case R.id.radio_string:
                if (checked) {
                    showDialogs(R.id.radio_string);
                    break;
                }
            case R.id.radio_color:
                if (checked) {
                    showDialogs(R.id.radio_color);
                    break;
                }
        }
    }
}