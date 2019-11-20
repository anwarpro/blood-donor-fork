package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    public static final String USER_NAME = "name";
    public static final String USER_MAIL = "mail";
    public static final String USER_PHONE = "1234";
    public static final String USER_BLOOD = "O+";
    TextView txtstatus,txtproname,txtpromail,txtprophone,txtproblood;
    SwitchCompat switchbar;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtstatus = findViewById(R.id.txtdonatestatus);
        switchbar = findViewById(R.id.switchbtn);
        updatebtn = findViewById(R.id.textView4);
        txtproblood = findViewById(R.id.textView3);
        txtpromail = findViewById(R.id.userpromail);
        txtproname = findViewById(R.id.userproname);
        txtprophone = findViewById(R.id.userprophone);

        switchbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true)
                {
                    txtstatus.setText("Yes");
                }
                else if (b == false)
                {
                    txtstatus.setText("No");
                }
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this,EditProfileActivity.class);
                intent.putExtra(USER_NAME, txtproname.getText().toString());
                intent.putExtra(USER_MAIL, txtpromail.getText().toString());
                intent.putExtra(USER_PHONE, txtprophone.getText().toString());
                intent.putExtra(USER_BLOOD, txtproblood.getText().toString());
                startActivity(intent);
            }
        });
    }
}
