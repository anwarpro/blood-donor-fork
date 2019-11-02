package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonorDataActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    TextView txtcallname,txtcallphone,txtcallblood;
    FloatingActionButton tabcall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_data);

        txtcallname = findViewById(R.id.txtdonorcallname);
        txtcallphone = findViewById(R.id.txtdonorcallphone);
        txtcallblood = findViewById(R.id.txtdonorcallblood);
        tabcall = findViewById(R.id.button_call);

        Intent intent = getIntent();
        String name = intent.getStringExtra(DonorActivity.USER_NAME);
        String phone = intent.getStringExtra(DonorActivity.USER_PHONE);
        String blood = intent.getStringExtra(DonorActivity.USER_BLOOD);

        txtcallname.setText(name);
        txtcallphone.setText(phone);
        txtcallblood.setText(blood);
        tabcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });
    }
    private void makePhoneCall(){
        String phonenum = txtcallphone.getText().toString();
        if (ContextCompat.checkSelfPermission(DonorDataActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DonorDataActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String dial = "tel:" + phonenum;
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
        }
    }
}
