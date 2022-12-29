package com.champlain.makeitrain;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private Button makeItRain;
    private Button showInfo;
    private TextView moneyValue;
    private int moneyCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeItRain = findViewById(R.id.buttonMakeRain);
        moneyValue = findViewById(R.id.moneyValue);

        //moneyValue.setText(R.string.test);
        //makeItRain.setOnClickListener(view -> Log.d("Main Activity", "onClick: Make it Rain"));

    }

    public void showMoney(View view) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        moneyCounter += 1000;

        if(moneyCounter>=10000){
            //change color
            moneyValue.setTextColor(Color.parseColor("#34eb5e"));
        }
        else if(moneyCounter>=5000){
            //change color
            moneyValue.setTextColor(Color.parseColor("#eb4034"));
        }

        moneyValue.setText(String.valueOf(numberFormat.format(moneyCounter)));
        Log.d("MIR", "onClick: Make it Rain " + moneyCounter);
    }

    public void showInfo(View view) {
        /*Toast.makeText(MainActivity.this,R.string.app_info,Toast.LENGTH_SHORT)
                .show();*/
        Snackbar.make(moneyValue,R.string.app_info, Snackbar.LENGTH_LONG)
                .setAction("More", view1 -> {
                    Log.d("snack", "showInfo: snackbar more");
                })
                .show();
    }
}