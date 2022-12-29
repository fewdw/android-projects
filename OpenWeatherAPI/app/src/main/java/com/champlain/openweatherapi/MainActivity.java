package com.champlain.openweatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.city_field);
        textView = findViewById(R.id.temp_text);
    }



    public void get(View v){
        String city = editText.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=8466a074d8998d00c57a32e41ae9543f";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONObject obj = response.getJSONObject("main");
                int temp = obj.getInt("temp");
                textView.setText(String.valueOf(temp-273) + " degrees celsius");
            } catch (JSONException e) {
                e.printStackTrace();
                textView.setText("error getting the city temperature");
            }
        }, error -> {
            textView.setText("error getting the city temperature");
        });
        queue.add(jsonObjectRequest);
    }
}