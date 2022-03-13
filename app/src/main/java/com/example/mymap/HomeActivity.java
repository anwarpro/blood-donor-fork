package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    CardView donor, map, feed, bed, hospital, bank, profile, about, group;
    DatabaseReference databaseReference, dbd;
    TextView tvd, tvr;
    private int countd, countr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Blood Donor App");
        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("feed");
        dbd = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");


        feed = findViewById(R.id.feedcard);
        map = findViewById(R.id.mapcard);
        bed = findViewById(R.id.bedcard);
        hospital = findViewById(R.id.hospitalcard);
        bank = findViewById(R.id.bankcard);
        profile = findViewById(R.id.profilecard);
        about = findViewById(R.id.aboutcard);
        donor = findViewById(R.id.donorcard);
        group = findViewById(R.id.groupcard);
        tvd = findViewById(R.id.textViewdc);
        tvr = findViewById(R.id.textViewsc);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countr = (int) dataSnapshot.getChildrenCount();
                    tvr.setText(Integer.toString(countr) + "\n" + "Requests");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    countd = (int) dataSnapshot.getChildrenCount();
                    tvd.setText(Integer.toString(countd) + "\n" + "Donors");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, DonorActivity.class);
                startActivity(intent);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddFeedActivity.class);
                startActivity(intent);
            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HospitalActivity.class);
                startActivity(intent);
            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BankActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, GroupsActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.side_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemhelp:
                Toast.makeText(this, "Call 01712329913", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.itemlogout:
                FirebaseAuth.getInstance().signOut();
                Intent intent2 = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent2);
                HomeActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
