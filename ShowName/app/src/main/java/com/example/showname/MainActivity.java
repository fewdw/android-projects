package com.example.showname;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button showButton;
    private TextView nameText;
    private EditText enterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButton = findViewById(R.id.button);
        nameText = findViewById(R.id.textView);
        enterName = findViewById(R.id.editTextName);

        //nameText.setText("Hello from java");
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = enterName.getText().toString();
                if(name.isEmpty()){
                    nameText.setText("Hello James Bond!");
                }
                else{
                    nameText.setText("hello " + name);
                }
            }
        });
    }
}