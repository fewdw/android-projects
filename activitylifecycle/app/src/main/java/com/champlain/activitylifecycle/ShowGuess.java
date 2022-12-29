package com.champlain.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.media.TimedText;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowGuess extends AppCompatActivity {

    private TextView showGuessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_guess);

        //String value = getIntent().getStringExtra("guess");
        showGuessTextView = findViewById(R.id.received_textview);

        Bundle extra = getIntent().getExtras();
        if(extra != null){
            showGuessTextView.setText((extra.getString("guess")));
            Log.d("name extra", "oncreate + " + extra.getString("name"));
            Log.d("name extra 2", "oncreate + " + extra.getInt("age"));

        }

        /*if(getIntent().getStringExtra("guess") != null){
            Log.d("stuff"," "+ getIntent().getStringExtra("name"));
            showGuessTextView.setText(getIntent().getStringExtra("guess"));
        }*/

    }
}