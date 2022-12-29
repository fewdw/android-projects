package com.champlain.bio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.champlain.bio.data.Bio;
import com.champlain.bio.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Bio bio = new Bio();
    private EditText enterHobbies;
    private TextView hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        enterHobbies = findViewById(R.id.enter_hobbies);
        hobbies = findViewById(R.id.hobbies_text);*/
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        bio.setName("John Doe");
        binding.setBio(bio);

        binding.doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHobbies(view);
            }
        });
    }

    public void addHobbies(View view) {
        bio.setHobbies(String.format("Hobbies: %s", binding.enterHobbies.getText().toString().trim()));
        //binding.hobbiesText.setText(String.format("Hobbies: %s", binding.enterHobbies.getText().toString().trim()));
        binding.invalidateAll();
        binding.hobbiesText.setVisibility(view.VISIBLE);
        /*hobbies.setText(String.format("Hobbies: %s", enterHobbies.getText().toString().trim()));
        hobbies.setVisibility(View.VISIBLE);*/


        //
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromInputMethod(view.getWindowToken(),0);
    }
}