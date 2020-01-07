package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    EditText etname,etmail,etphone,etblood;
    Button btndone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etmail = findViewById(R.id.etmail);
        etphone = findViewById(R.id.etphone);
        etblood = findViewById(R.id.etblood);
        btndone = findViewById(R.id.btncon);

        Intent intent = getIntent();
        String name = intent.getStringExtra(ProfileActivity.USER_NAME);
        String mail = intent.getStringExtra(ProfileActivity.USER_MAIL);
        String phone = intent.getStringExtra(ProfileActivity.USER_PHONE);
        String blood = intent.getStringExtra(ProfileActivity.USER_BLOOD);

        etmail.setText(name);
        etphone.setText(phone);
        etblood.setText(blood);

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
