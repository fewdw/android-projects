package com.champlain.persistencesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MESSAGE_ID = "message_prefs";
    private Button saveButton;
    private EditText enterMessage;
    private TextView showMessageTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveButton = findViewById(R.id.button);
        enterMessage = findViewById(R.id.message_editText);
        showMessageTextview = findViewById(R.id.show_message_textview);

        saveButton.setOnClickListener(view -> {
            String message = enterMessage.getText().toString().trim();
            SharedPreferences sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("message", message);
            editor.apply();//saving to disk
        });

        //get data back from shared preferences
        SharedPreferences getSharedData = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
        String value = getSharedData.getString("message", "Nothing yet!");

        showMessageTextview.setText(value);
    }
}