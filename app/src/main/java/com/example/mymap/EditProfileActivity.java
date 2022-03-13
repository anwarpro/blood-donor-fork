package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {
    EditText etname, etmail, etphone, etblood;
    Button btndone;
    private DatabaseReference databaseReference;

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
        final String userId = intent.getStringExtra(ProfileActivity.USER_ID);

        if (userId == null) {
            finish();
            return;
        }

        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");

        etmail.setText(name);
        etphone.setText(phone);
        etblood.setText(blood);

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("userName", etmail.getText().toString());
                map.put("userPhone", etphone.getText().toString());
                map.put("userBlood", etblood.getText().toString());
                databaseReference.child(userId).updateChildren(map);
                Toast.makeText(EditProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
