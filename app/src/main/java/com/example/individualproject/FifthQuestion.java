package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FifthQuestion extends AppCompatActivity {
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_question);

        score = getIntent().getIntExtra("score", 0);
    }

    public void showDialogs(int id) {
//        Dialog Init
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                int scores = score + id;

//                saving score to history
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FifthQuestion.this);
                SharedPreferences.Editor edit = pref.edit();
                String history = pref.getString("history", "");
                List<String> splithistory = new LinkedList<String>(Arrays.asList(history.split("-")));
                splithistory.add(String.valueOf(scores));

                String listScores = String.join("-", splithistory);
                if (listScores.startsWith("-")) {
                    listScores = listScores.substring(1);
                }

                edit.putString("history", listScores);
                edit.apply();
                // creating a new intent
                Intent intent = new Intent(FifthQuestion.this, Result.class);
                // starting a new activity.
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

    public void onCheckboxClicked2(View view) {
        // Is the view now checked?
        int newscore = 0;
        String fifthanswer = "Not Correct";

        boolean controllerchecked = ((CheckBox) findViewById(R.id.checkbox_controller)).isChecked();
        boolean serviceschecked = ((CheckBox) findViewById(R.id.checkbox_services)).isChecked();
        boolean broadcastchecked = ((CheckBox) findViewById(R.id.checkbox_broadcast)).isChecked();
        boolean contentchecked = ((CheckBox) findViewById(R.id.checkbox_content)).isChecked();
        //                Inccrease the score
        if ((serviceschecked && broadcastchecked && contentchecked) && (!controllerchecked)) {
            newscore = 1;
            fifthanswer = "Correct";
        }
//                Setting score in history
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(FifthQuestion.this);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("fifthanswer", fifthanswer);
        edit.apply();

        showDialogs(newscore);
    }
}