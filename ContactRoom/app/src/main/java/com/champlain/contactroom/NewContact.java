package com.champlain.contactroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.champlain.contactroom.model.Contact;
import com.champlain.contactroom.model.ContactViewModel;

public class NewContact extends AppCompatActivity {
    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION = "occupation";
    private EditText enterName;
    private EditText enterOccupation;
    private Button saveInfoButton;

    private ContactViewModel contactViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        enterName = findViewById(R.id.enter_name);
        enterOccupation = findViewById(R.id.enter_occupation);
        saveInfoButton = findViewById(R.id.save_button);

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(NewContact.this.getApplication()).create(ContactViewModel.class);

        saveInfoButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if(!TextUtils.isEmpty(enterName.getText()) && !TextUtils.isEmpty(enterOccupation.getText())){

                String name = enterName.getText().toString();
                String occupation = enterOccupation.getText().toString();

                replyIntent.putExtra(NAME_REPLY, name);
                replyIntent.putExtra(OCCUPATION, name);
                setResult(RESULT_OK, replyIntent);

                /*Contact contact = new Contact(enterName.getText().toString(), enterOccupation.getText().toString());*/
                //ContactViewModel.insert(contact);
            }else{
                //Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
                setResult(RESULT_CANCELED, replyIntent);
            }

            finish();
        });

        Bundle data = getIntent().getExtras();
        if(data == null){
            int id = data.getInt(MainActivity.CONTACT_ID);
            contactViewModel.get(id).observe(this,contact -> {
                enterName.setText(contact.getName());
                enterOccupation.setText(contact.getOccupation());
            });
        }

    }
}