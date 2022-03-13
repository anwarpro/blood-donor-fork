package com.example.mymap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedActivity extends AppCompatActivity {
    EditText txtName,txtPhone,txtdate,txthosp;
    Spinner spinnerBlood,spinnerBag;
    Button btnPost,btnLoc;
    DatabaseReference databaseReference;
    Spinner spinneraddarea;
    TextView txtloc1,txtloc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed);

        getSupportActionBar().setTitle("Add Request");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("feed");


        txtName = findViewById(R.id.txt_feedname);
        spinneraddarea = findViewById(R.id.txt_feedaddress);
        txtPhone = findViewById(R.id.txt_feedphone);
        spinnerBag = findViewById(R.id.spinnerbloodbag);
        spinnerBlood = findViewById(R.id.spinnerbloodgrp);
        btnPost = findViewById(R.id.btnpost);
        txtdate = findViewById(R.id.txt_feeddate);
        txthosp = findViewById(R.id.txt_feedhosp);
        btnLoc = findViewById(R.id.btnaddloc);
        txtloc1 = findViewById(R.id.txtvlocation1);
        txtloc2 = findViewById(R.id.txtvlocation2);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddFeedActivity.this,R.layout.custom_spinner_for_area,getResources().getStringArray(R.array.blood_area));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneraddarea.setAdapter(myAdapter);

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<String>(AddFeedActivity.this,R.layout.custom_spinner,getResources().getStringArray(R.array.blood_sort));
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBlood.setAdapter(myAdapter1);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(AddFeedActivity.this,R.layout.custom_spinner,getResources().getStringArray(R.array.blood_bag));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBag.setAdapter(myAdapter2);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeed();

            }
        });
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddFeedActivity.this,AddLocationActivity.class);
                startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                txtloc1.setText(data.getStringExtra("location1"));
                txtloc2.setText(data.getStringExtra("location2"));
                btnLoc.setText("added");


            }
        }
    }

    private void addFeed()
    {
        String name = txtName.getText().toString().trim();
        String address = spinneraddarea.getSelectedItem().toString();
        String phone = txtPhone.getText().toString().trim();
        String date = txtdate.getText().toString().trim();
        String hosp = txthosp.getText().toString().trim();
        String bloodgrp = spinnerBlood.getSelectedItem().toString();
        String bloodbag = spinnerBag.getSelectedItem().toString();
        String location = txtloc1.getText().toString();
        String location2 = txtloc2.getText().toString();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(address) && !TextUtils.isEmpty(date) && !location.equals("latitude") && !location2.equals("longitude") && !address.equals("Select Area"))
        {
            String id = databaseReference.push().getKey();
            Feed feed = new Feed(id,name,address,bloodgrp,phone,bloodbag,date,hosp,location,location2);
            databaseReference.child(id).setValue(feed);
            Toast.makeText(this, "Posted", Toast.LENGTH_SHORT).show();
            AddFeedActivity.this.finish();

        }
        else
        {
            Toast.makeText(this, "Please fill all the required fields and add a location.", Toast.LENGTH_LONG).show();
        }
    }
}
