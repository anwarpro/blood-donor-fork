package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    public static final String FEED_NAME = "name";
    public static final String FEED_PHONE = "1234";
    ListView listViewFeed;
    List<Feed> feedList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        getSupportActionBar().setTitle("Requests For Blood");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("feed");
        listViewFeed = findViewById(R.id.listviewfeed);
        feedList = new ArrayList<>();
        listViewFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Feed feed = feedList.get(i);
                Intent intent = new Intent(FeedActivity.this,PopupActivity.class);
                intent.putExtra(FEED_NAME,feed.getFeedName());
                intent.putExtra(FEED_PHONE,feed.getFeedPhone());
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
                feedList.clear();
                for (DataSnapshot feedSnapshot : dataSnapshot.getChildren())
                {
                    Feed feed = feedSnapshot.getValue(Feed.class);
                    feedList.add(feed);
                }
                FeedList adapter = new FeedList(FeedActivity.this,feedList);
                listViewFeed.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
