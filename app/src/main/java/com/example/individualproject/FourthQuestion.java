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

public class FourthQuestion extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_question);

        score = getIntent().getIntExtra("score", 0);
    }

    public void showDialogs(int id) {
//        Dialog Init
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String fourthanswer = "Not Correct";
                //                Inccrease the score
                if (R.id.radio_sqlite == id) {
                    score = score + 1;
                    fourthanswer = "Correct";
                }
//                Setting score in history
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FourthQuestion.this);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("fourthanswer", fourthanswer);
                edit.apply();


                Intent intent = new Intent(FourthQuestion.this, FifthQuestion.class);
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

    public void onRadioButtonClicked3(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_sqlite:
                if (checked) {
                    showDialogs(R.id.radio_sqlite);
                    break;
                }
            case R.id.radio_oracle:
                if (checked) {
                    showDialogs(R.id.radio_oracle);
                    break;
                }
            case R.id.radio_mysql:
                if (checked) {
                    showDialogs(R.id.radio_mysql);
                    break;
                }
            case R.id.radio_postgresql:
                if (checked) {
                    showDialogs(R.id.radio_postgresql);
                    break;
                }
        }
    }
}