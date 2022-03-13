package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DonorActivity extends AppCompatActivity {

    public static final String USER_NAME = "name";
    public static final String USER_PHONE = "1234";
    public static final String USER_BLOOD = "O+";
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Toolbar toolbar;
    Spinner spinner, spinnerarea;
    private FirebaseAuth.AuthStateListener authStateListener;

    ListView listView;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        spinner = findViewById(R.id.spinner);
        spinnerarea = findViewById(R.id.spinnerarea);
        toolbar = findViewById(R.id.toolview);
        toolbar.setTitle("Donor List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(DonorActivity.this, R.layout.custom_spinner, getResources().getStringArray(R.array.blood_sort));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        ArrayAdapter<String> myareaAdapter = new ArrayAdapter<String>(DonorActivity.this, R.layout.custom_spinner, getResources().getStringArray(R.array.blood_area));
        myareaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerarea.setAdapter(myareaAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_group = spinner.getSelectedItem().toString();

                if (selected_group.equals("All")) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            userList.clear();
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                User user = userSnapshot.getValue(User.class);
                                userList.add(user);
                            }
                            UserList adapter = new UserList(DonorActivity.this, userList);
                            listView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {

                    Query query1 = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users")
                            .orderByChild("userBlood")
                            .equalTo(selected_group);
                    query1.addListenerForSingleValueEvent(
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    userList.clear();
                                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                        User user = userSnapshot.getValue(User.class);
                                        userList.add(user);
                                    }
                                    UserList adapter = new UserList(DonorActivity.this, userList);
                                    listView.setAdapter(adapter);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            })
                    );
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userList.clear();
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            User user = userSnapshot.getValue(User.class);
                            userList.add(user);
                        }
                        UserList adapter = new UserList(DonorActivity.this, userList);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        spinnerarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_area = spinnerarea.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");


        listView = (ListView) findViewById(R.id.listViewUsers);
        userList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = userList.get(i);
                Intent intent = new Intent(getApplicationContext(), DonorDataActivity.class);
                intent.putExtra(USER_NAME, user.getUserPhone());
                intent.putExtra(USER_PHONE, user.getUserName());
                intent.putExtra(USER_BLOOD, user.getUserBlood());
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
                userList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    userList.add(user);
                }
                UserList adapter = new UserList(DonorActivity.this, userList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
