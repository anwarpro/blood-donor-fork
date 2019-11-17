package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        getSupportActionBar().setTitle("Social Groups");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
