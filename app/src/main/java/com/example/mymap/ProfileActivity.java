package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    public static final String USER_NAME = "name";
    public static final String USER_MAIL = "mail";
    public static final String USER_PHONE = "1234";
    public static final String USER_BLOOD = "O+";
    public static final String USER_ID = "USER_ID";

    private String userId = null;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView txtstatus, txtproname, txtpromail, txtprophone, txtproblood;
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

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("users");

        Query query = databaseReference.orderByChild("userEmail").equalTo(firebaseUser.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = "" + ds.child("userName").getValue();
                    String phone = "" + ds.child("userPhone").getValue();
                    String blood = "" + ds.child("userBlood").getValue();
                    String email = "" + ds.child("userEmail").getValue();
                    userId = "" + ds.child("userId").getValue();

                    txtproname.setText(name);
                    txtprophone.setText(phone);
                    txtproblood.setText(blood);
                    txtpromail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Error", databaseError.getDetails());
            }
        });


        switchbar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    txtstatus.setText("Yes");
                } else if (b == false) {
                    txtstatus.setText("No");
                }
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra(USER_NAME, txtproname.getText().toString());
                intent.putExtra(USER_MAIL, txtpromail.getText().toString());
                intent.putExtra(USER_PHONE, txtprophone.getText().toString());
                intent.putExtra(USER_BLOOD, txtproblood.getText().toString());
                intent.putExtra(USER_ID, userId);
                startActivity(intent);
            }
        });
    }
}
