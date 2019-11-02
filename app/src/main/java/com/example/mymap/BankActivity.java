package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BankActivity extends AppCompatActivity {
    ListView listViewBank;
    DatabaseReference databaseReference;
    List<Bank> bankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        databaseReference = FirebaseDatabase.getInstance().getReference("banks");

        getSupportActionBar().setTitle("Blood Banks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewBank = findViewById(R.id.listviewbank);
        bankList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bankList.clear();
                for (DataSnapshot bankSnapshot : dataSnapshot.getChildren())
                {
                    Bank bank = bankSnapshot.getValue(Bank.class);
                    bankList.add(bank);
                }
                BankList adapter = new BankList(BankActivity.this,bankList);
                listViewBank.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
