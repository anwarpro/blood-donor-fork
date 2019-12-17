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
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PopupActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    TextView hospnamepop;
    Button bndir,bncall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        hospnamepop = findViewById(R.id.textViewpop);
        bncall = findViewById(R.id.popbtncall);
        bndir = findViewById(R.id.popbtnmap);


        Intent intent = getIntent();
        String name = intent.getStringExtra(HospitalActivity.HOSP_NAME);
        String phone = intent.getStringExtra(HospitalActivity.HOSP_PHONE);
        String bname = intent.getStringExtra(BankActivity.BANK_NAME);
        String fname = intent.getStringExtra(FeedActivity.FEED_NAME);

        hospnamepop.setText(name);
        hospnamepop.setText(bname);
        hospnamepop.setText(fname);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*0.8),(int)(height*0.25));



        bncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
                PopupActivity.this.finish();
            }
        });


        bndir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(PopupActivity.this,DirectionActivity.class);
                startActivity(intent1);
                PopupActivity.this.finish();
            }
        });
    }
    private void makePhoneCall(){
        Intent intent = getIntent();
        String name = intent.getStringExtra(HospitalActivity.HOSP_NAME);
        String phone = intent.getStringExtra(HospitalActivity.HOSP_PHONE);
        String bname = intent.getStringExtra(BankActivity.BANK_NAME);
        String bphone = intent.getStringExtra(BankActivity.BANK_PHONE);
        String fname = intent.getStringExtra(FeedActivity.FEED_NAME);
        String fphone = intent.getStringExtra(FeedActivity.FEED_PHONE);
        if (ContextCompat.checkSelfPermission(PopupActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PopupActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            String dial = "tel:" + phone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            Toast.makeText(this, "calling " + name, Toast.LENGTH_SHORT).show();

            String dialb = "tel:" + bphone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dialb)));
            Toast.makeText(this, "calling " + bname, Toast.LENGTH_SHORT).show();

            String dialf = "tel:" + fphone;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dialf)));
            Toast.makeText(this, "calling " + fname, Toast.LENGTH_SHORT).show();
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
