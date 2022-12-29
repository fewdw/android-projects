package com.champlain.parsedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    String url="https://google.com";
    String apiUrl = "https://jsonplaceholder.typicode.com/todos";
    String getApiUrl = "https://jsonplaceholder.typicode.com/todos/3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = findViewById(R.id.textView);
        queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getApiUrl, null,
                response -> {
                    try {
                        textview.setText(response.getString("title"));
                        Log.d("Json", "onCreate: " + response.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
            Log.d("json", "onCreate: Failed!");
        });
        queue.add(jsonObjectRequest);


        //jsonArrauRequest();

        //getString(queue);
    }

    private void jsonArrauRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("json", "onCreate: " + jsonObject.getString("title"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                error -> Log.d("json", "onCreate: On Create: Failed"));
        queue.add(jsonArrayRequest);
    }

    private void getString(RequestQueue queue) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                Log.d("main", "onCreate: " + response.substring(0,300));
        }, error -> {
            Log.d("Main", "failed to get info!");
        });
        queue.add(stringRequest);
    }
}