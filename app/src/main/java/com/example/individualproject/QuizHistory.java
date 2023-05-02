package com.example.individualproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class QuizHistory extends AppCompatActivity {
    ListView listview1;
    String[] historyarr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_history);

        // Reading History score
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(QuizHistory.this);
        String history = pref.getString("history", "");
        List<String> historyarr = Arrays.asList(history.split("-"));
        for (int i = 0; i < historyarr.size(); i++) {
            String item = historyarr.get(i);
            item = "Score of Quiz " + (i+1) + ": "+ item;
            historyarr.set(i, item);
        }

//        Adapter to Display list
        ArrayAdapter<String> adapter;
        listview1 = (ListView) findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(this, R.layout.forlistview, historyarr);
        listview1.setAdapter(adapter);
    }

    public void startQ1(View view) {
        // creating a new intent
        Intent i = new Intent(this, FirstQuestion.class);

        // starting a new activity.
        startActivity(i);
    }
}