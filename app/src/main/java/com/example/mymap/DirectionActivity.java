package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DirectionActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;
    SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
            else if(ActivityCompat.checkSelfPermission(DirectionActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;

            }
            else
            {
                client.getLastLocation().addOnSuccessListener(DirectionActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location!=null)
                        {
                            googleMap.setMyLocationEnabled(true);
                            LatLng latLng = new LatLng(23.746466, 90.376015);
                            map.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
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
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}
