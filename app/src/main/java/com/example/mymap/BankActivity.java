package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    public static final String BANK_NAME = "name";
    public static final String BANK_PHONE = "1234";
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
        listViewBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bank bank = bankList.get(i);
                Intent intent = new Intent(BankActivity.this,PopupActivity.class);
                intent.putExtra(BANK_NAME,bank.getBankName());
                intent.putExtra(BANK_PHONE,bank.getBankPhone());
                startActivity(intent);
            }
        });

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
