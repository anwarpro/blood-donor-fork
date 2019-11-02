package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddByAdmin extends AppCompatActivity {
    EditText hospName,hospAddress,hospPhone,bankName,bankAddress,bankPhone;
    Button addbank,addhosp;
    DatabaseReference databaseReference,databaseBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_by_admin);

        databaseReference = FirebaseDatabase.getInstance().getReference("hospitals");
        databaseBank = FirebaseDatabase.getInstance().getReference("banks");


        hospName = findViewById(R.id.txthospitalname);
        hospAddress = findViewById(R.id.txthospitaladdress);
        hospPhone = findViewById(R.id.txthospitalphone);
        bankName = findViewById(R.id.txtbankname);
        bankAddress = findViewById(R.id.txtbankaddress);
        bankPhone = findViewById(R.id.txtbankphone);
        addbank = findViewById(R.id.btnaddbank);
        addhosp = findViewById(R.id.btnaddhospital);

        addhosp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHospital();

            }
        });

        addbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBank();

            }
        });
    }
    private void addHospital()
    {
        String name = hospName.getText().toString().trim();
        String address = hospAddress.getText().toString().trim();
        String phone = hospPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(address))
        {
            String id = databaseReference.push().getKey();
            Hospital hospital = new Hospital(id,name,address,phone);
            databaseReference.child(id).setValue(hospital);
            Toast.makeText(this, "Hospital Added", Toast.LENGTH_SHORT).show();
            hospName.setText("");
            hospAddress.setText("");
            hospPhone.setText("");

        }
        else
        {
            Toast.makeText(this, "Enter all Fields", Toast.LENGTH_SHORT).show();
        }
    }
    private void addBank()
    {
        String name = bankName.getText().toString().trim();
        String address = bankAddress.getText().toString().trim();
        String phone = bankPhone.getText().toString().trim();
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(address))
        {
            String id = databaseBank.push().getKey();
            Bank bank = new Bank(id,name,address,phone);
            databaseBank.child(id).setValue(bank);
            Toast.makeText(this, "Bank Added", Toast.LENGTH_SHORT).show();
            bankName.setText("");
            bankAddress.setText("");
            bankPhone.setText("");

        }
        else
        {
            Toast.makeText(this, "Enter all Fields", Toast.LENGTH_SHORT).show();
        }
    }
}
