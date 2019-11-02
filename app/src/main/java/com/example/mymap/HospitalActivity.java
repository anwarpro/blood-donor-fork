package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HospitalActivity extends AppCompatActivity {
    ListView listViewHospital;
    DatabaseReference databaseReference;
    List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        databaseReference = FirebaseDatabase.getInstance().getReference("hospitals");

        getSupportActionBar().setTitle("Hospitals");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewHospital = findViewById(R.id.listviewhospital);
        hospitalList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                hospitalList.clear();
                for (DataSnapshot hospitalSnapshot : dataSnapshot.getChildren())
                {
                    Hospital hospital = hospitalSnapshot.getValue(Hospital.class);
                    hospitalList.add(hospital);
                }
                HospitalList adapter = new HospitalList(HospitalActivity.this,hospitalList);
                listViewHospital.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
