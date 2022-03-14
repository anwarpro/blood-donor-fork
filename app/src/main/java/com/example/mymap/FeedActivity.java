package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    public static final String IS_ADMIN = "isAdmin";
    ListView listViewFeed;
    List<Feed> feedList;
    DatabaseReference databaseReference;
    private Feed feed = null;

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
                feed = feedList.get(i);
                Intent intent = new Intent(FeedActivity.this, PopupActivity.class);
                intent.putExtra(FEED_NAME, feed.getFeedName());
                intent.putExtra(FEED_PHONE, feed.getFeedPhone());
                intent.putExtra(IS_ADMIN, getIntent().getBooleanExtra(IS_ADMIN, false));
                startActivityForResult(intent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            //delete current node
            if (feed != null) {
                databaseReference.child(feed.feedId)
                        .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(FeedActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Log.e("FEED", "Feed null");
            }
        } else {
            Log.e("FEED", "Not in result");
        }
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra(IS_ADMIN, false)) {
            FeedActivity.this.finish();
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                feedList.clear();
                for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                    Feed feed = feedSnapshot.getValue(Feed.class);
                    feedList.add(feed);
                }
                FeedList adapter = new FeedList(FeedActivity.this, feedList);
                listViewFeed.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
