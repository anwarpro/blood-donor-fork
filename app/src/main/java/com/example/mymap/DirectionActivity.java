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

public class DirectionActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    GoogleMap map;
    SupportMapFragment mapFragment;
    MarkerOptions place1,place2;
    SearchView searchView;
    Polyline currentpolyline;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapdir);
        mapFragment.getMapAsync(this);
        mapFragment.getMapAsync(this);

        place1 = new MarkerOptions().position(new LatLng(23.755044,90.389406)).title("My Location");
        place2 = new MarkerOptions().position(new LatLng(23.741502,90.375129)).title("Hospital");
        String url = getUrl(place1.getPosition(),place2.getPosition(),"driving");

        new FetchURL(DirectionActivity.this).execute(url, "driving");

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        map = googleMap;

        map.addMarker(place1);
        map.addMarker(place2);

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode)
    {
        String str_origin = "origin" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination" + dest.latitude + "," + dest.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "$" + str_dest + "$" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.map_key);
        return url;

    }

    /*private String requestDirection(String requrl)
    {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(requrl);
            httpURLConnection = url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }*/
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentpolyline !=null)
            currentpolyline.remove();
        currentpolyline = map.addPolyline((PolylineOptions) values[0]);
    }
}
