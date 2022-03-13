package com.example.mymap;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    DatabaseReference databaseReference;
    Button btnpat;
    Spinner spinnermain;
    private FusedLocationProviderClient client;
    private Marker MyMarker;
    TextView tv1, tv2, tv3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("feed");

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = findViewById(R.id.sv_location);
        btnpat = findViewById(R.id.btnmppat);
        spinnermain = findViewById(R.id.spinnermapbg);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_spinner_for_map, getResources().getStringArray(R.array.blood_group));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermain.setAdapter(myAdapter);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);

        spinnermain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_group = spinnermain.getSelectedItem().toString();

                if (selected_group.equals("All")) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                                Feed feed = feedSnapshot.getValue(Feed.class);
                                Double l1 = Double.parseDouble(feed.getFeedLocation());
                                Double l2 = Double.parseDouble(feed.getFeedLocation2());
                                String name = feed.getFeedName();
                                String phone = feed.getFeedPhone();
                                String blood = feed.getFeedBlood();
                                tv1.setText(name);
                                tv2.setText(blood);
                                tv3.setText(phone);


                                map.addMarker(new MarkerOptions()
                                        .position(new LatLng(l1, l2))
                                        .title(name)
                                        .snippet(blood)
                                        .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_bddd)));

                                map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                    @Override
                                    public View getInfoWindow(Marker marker) {
                                        return null;
                                    }

                                    @Override
                                    public View getInfoContents(Marker marker) {

                                        View v = getLayoutInflater().inflate(R.layout.info_window, null);

                                        TextView tvname = v.findViewById(R.id.tv_name);
                                        TextView tvblood = v.findViewById(R.id.tv_bg);
                                        TextView tvphone = v.findViewById(R.id.tv_phone);
                                        String m = marker.getTitle();
                                        String m2 = marker.getSnippet();
                                        tvname.setText(m);
                                        tvblood.setText(m2);
                                        return v;
                                    }
                                });

                                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                    @Override
                                    public void onInfoWindowClick(Marker marker) {
                                        Toast.makeText(MainActivity.this, "Make a Call.", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                } else {

                    Query query1 = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("feed")
                            .orderByChild("feedBlood")
                            .equalTo(selected_group);
                    query1.addListenerForSingleValueEvent(
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    map.clear();
                                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                        Feed feed = userSnapshot.getValue(Feed.class);
                                        Double l1 = Double.parseDouble(feed.getFeedLocation());
                                        Double l2 = Double.parseDouble(feed.getFeedLocation2());
                                        String name = feed.getFeedName();
                                        String phone = feed.getFeedPhone();
                                        String blood = feed.getFeedBlood();


                                        MyMarker = map.addMarker(new MarkerOptions()
                                                .position(new LatLng(l1, l2))
                                                .title(name)
                                                .snippet(blood)
                                                .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_bddd)));

                                        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                            @Override
                                            public View getInfoWindow(Marker marker) {
                                                return null;
                                            }

                                            @Override
                                            public View getInfoContents(Marker marker) {

                                                View v = getLayoutInflater().inflate(R.layout.info_window, null);

                                                TextView tvname = v.findViewById(R.id.tv_name);
                                                TextView tvblood = v.findViewById(R.id.tv_bg);
                                                TextView tvphone = v.findViewById(R.id.tv_phone);
                                                String m = marker.getTitle();
                                                String m2 = marker.getSnippet();
                                                tvname.setText(m);
                                                tvblood.setText(m2);
                                                return v;
                                            }
                                        });

                                        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                            @Override
                                            public void onInfoWindowClick(Marker marker) {
                                                Toast.makeText(MainActivity.this, "Make a Call.", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }

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

            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(MainActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        map = googleMap;


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.

            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.mapstyledark));

            if (!success) {
                Toast.makeText(this, "Couldn't Connect!", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;

            } else {
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            googleMap.setMyLocationEnabled(true);
                            final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                            btnpat.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    btnpat.setVisibility(View.GONE);
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));


                                    databaseReference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot feedSnapshot : dataSnapshot.getChildren()) {
                                                Feed feed = feedSnapshot.getValue(Feed.class);
                                                Double l1 = Double.parseDouble(feed.getFeedLocation());
                                                Double l2 = Double.parseDouble(feed.getFeedLocation2());
                                                String name = feed.getFeedName();
                                                String phone = feed.getFeedPhone();
                                                String blood = feed.getFeedBlood();
                                                tv1.setText(name);
                                                tv2.setText(blood);
                                                tv3.setText(phone);


                                                googleMap.addMarker(new MarkerOptions()
                                                        .position(new LatLng(l1, l2))
                                                        .title(name)
                                                        .snippet(blood)
                                                        .icon(bitmapDescriptorFromVector(getApplicationContext(), R.drawable.ic_bddd)));

                                                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                                                    @Override
                                                    public View getInfoWindow(Marker marker) {
                                                        return null;
                                                    }

                                                    @Override
                                                    public View getInfoContents(Marker marker) {

                                                        View v = getLayoutInflater().inflate(R.layout.info_window, null);

                                                        TextView tvname = v.findViewById(R.id.tv_name);
                                                        TextView tvblood = v.findViewById(R.id.tv_bg);
                                                        TextView tvphone = v.findViewById(R.id.tv_phone);
                                                        String m = marker.getTitle();
                                                        String m2 = marker.getSnippet();
                                                        tvname.setText(m);
                                                        tvblood.setText(m2);
                                                        return v;
                                                    }
                                                });

                                                googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                                    @Override
                                                    public void onInfoWindowClick(Marker marker) {
                                                        Toast.makeText(MainActivity.this, "Make a Call.", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });


                                }
                            });
                        }

                    }
                });
            }

        } catch (Resources.NotFoundException e) {
            Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show();
        }

        /*if(ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;

        }
        else
        {
            client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location!=null)
                    {
                        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,40));
                    }

                }
            });
        }*/


        /*client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null)
                {
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title("Current LOcation"));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,40));
                }

            }
        });
        LatLng Dhaka = new LatLng(23.774287, 90.366169);
        map.addMarker(new MarkerOptions().position(Dhaka).title("Shyamoli"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Dhaka));*/

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
