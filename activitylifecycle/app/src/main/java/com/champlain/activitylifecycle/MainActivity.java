package com.champlain.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button showGuess;
    private EditText enterGuess;

    //onCreate method will run when app starts.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showGuess = findViewById(R.id.button_guess);
        enterGuess = findViewById(R.id.guess_field);

        showGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String guess = enterGuess.getText().toString().trim();

                if(!guess.isEmpty()){
                    Intent intent = new Intent(MainActivity.this,ShowGuess.class);
                    intent.putExtra("guess",guess);
                    intent.putExtra("name","James");
                    intent.putExtra("age",34);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"enter guess", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "hello from onstart()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(MainActivity.this, "hello from onresume()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this, "hello from onpause()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "hello from onstop()", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "hello from ondestroy()", Toast.LENGTH_LONG).show();
    }
*/

}