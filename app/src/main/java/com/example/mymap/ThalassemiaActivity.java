package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ThalassemiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thalassemia);

        getSupportActionBar().setTitle("Thalassemia Patients");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
