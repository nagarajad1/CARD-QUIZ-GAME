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
import android.widget.CheckBox;

public class ThirdQuestion extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_question);

        score = getIntent().getIntExtra("score", 0);
    }

    public void showDialogs(int id) {
        //        Dialog Init
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                score = score + id;
                Intent intent = new Intent(ThirdQuestion.this, FourthQuestion.class);
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

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        int newscore = 0;
        String thirdanswer = "Not Correct";
        boolean javachecked = ((CheckBox) findViewById(R.id.checkbox_java)).isChecked();
        boolean pythonchecked = ((CheckBox) findViewById(R.id.checkbox_python)).isChecked();
        boolean cpluspluschecked = ((CheckBox) findViewById(R.id.checkbox_cplusplus)).isChecked();
        boolean kotlinchecked = ((CheckBox) findViewById(R.id.checkbox_kotlin)).isChecked();
        //                Inccrease the score
        if ((javachecked && kotlinchecked) && (!pythonchecked && !cpluspluschecked)){
            newscore = 1;
            thirdanswer = "Correct";
        }
//                Setting score in history
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ThirdQuestion.this);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("thirdanswer", thirdanswer);
        edit.apply();


        showDialogs(newscore);
    }
}