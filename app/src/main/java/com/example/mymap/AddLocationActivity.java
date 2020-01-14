package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    Button btncon;
    private FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        requestPermission();
        Intent intent = getIntent();

        client = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView = findViewById(R.id.sv_location);
        btncon = findViewById(R.id.btnconloc);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                final String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if(location!=null || !location.equals(""))
                {
                    Geocoder geocoder = new Geocoder(AddLocationActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    final LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

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
            }
            else if(ActivityCompat.checkSelfPermission(AddLocationActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;

            }
            else
            {
                client.getLastLocation().addOnSuccessListener(AddLocationActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null)
                        {
                            googleMap.setMyLocationEnabled(true);
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                @Override
                                public void onMapClick(final LatLng latLng) {
                                    MarkerOptions markerOptions = new MarkerOptions();
                                    markerOptions.position(latLng);
                                    markerOptions.title(latLng.latitude+" : " + latLng.longitude);
                                    map.clear();
                                    map.addMarker(markerOptions);
                                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                                    btncon.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent l = new Intent();
                                            Double l1 = latLng.latitude;
                                            Double l2 = latLng.longitude;
                                            String c1 = l1.toString();
                                            String c2 = l2.toString();

                                            l.putExtra("location1", c1);
                                            l.putExtra("location2", c2);
                                            setResult(RESULT_OK, l);
                                            finish();
                                        }
                                    });

                                }
                            });
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                        }

                    }
                });
            }

        } catch (Resources.NotFoundException e) {
            Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show();
        }


    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
