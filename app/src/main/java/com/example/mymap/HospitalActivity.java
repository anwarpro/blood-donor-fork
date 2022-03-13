package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HospitalActivity extends AppCompatActivity {
    public static final String HOSP_NAME = "name";
    public static final String HOSP_PHONE = "1234";
    ListView listViewHospital;
    DatabaseReference databaseReference;
    List<Hospital> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("hospitals");

        getSupportActionBar().setTitle("Hospitals");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewHospital = findViewById(R.id.listviewhospital);
        hospitalList = new ArrayList<>();
        listViewHospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hospital hospital = hospitalList.get(i);
                Intent intent = new Intent(HospitalActivity.this,PopupActivity.class);
                intent.putExtra(HOSP_NAME,hospital.getHospitalName());
                intent.putExtra(HOSP_PHONE,hospital.getHospitalPhone());
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
