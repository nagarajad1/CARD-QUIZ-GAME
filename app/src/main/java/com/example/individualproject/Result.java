package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
//        Reading scores from history
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(Result.this);
        String firstanswer = pref.getString("firstanswer", "Not Correct");
        String secondanswer = pref.getString("secondanswer", "Not Correct");
        String thirdanswer = pref.getString("thirdanswer", "Not Correct");
        String fourthanswer = pref.getString("fourthanswer", "Not Correct");
        String fifthanswer = pref.getString("fifthanswer", "Not Correct");

        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView11 = (TextView) findViewById(R.id.textView11);
        TextView textView12 = (TextView) findViewById(R.id.textView12);
//        Display result
        textView3.setText("Quiz Question 1 : " + firstanswer);
        textView4.setText("Quiz Question 2 : " + secondanswer);
        textView10.setText("Quiz Question 3 : " + thirdanswer);
        textView11.setText("Quiz Question 4 : " + fourthanswer);
        textView12.setText("Quiz Question 5 : " + fifthanswer);

    }

    public void gohome(View view) {
        Intent intent = new Intent(this, QuizHistory.class);
        // starting a new activity.
        startActivity(intent);
    }
}