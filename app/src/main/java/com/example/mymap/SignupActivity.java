package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText phonetxt,passtxt,username;
    private Button signupbtn;
    private TextView logintxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        phonetxt = findViewById(R.id.txtsignupphone);
        passtxt = findViewById(R.id.txtsignuppassword);
        signupbtn = findViewById(R.id.btnsignup);
        logintxt = findViewById(R.id.txtviewlogin);
        username = findViewById(R.id.username);
        Spinner spinner = findViewById(R.id.spinnerbg);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.blood_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().trim().equalsIgnoreCase(""))
                {
                    username.setError("Enter Name");
                }
                else if (phonetxt.getText().toString().trim().equalsIgnoreCase(""))
                {
                    phonetxt.setError("Enter Number");
                }

                else if (passtxt.getText().toString().trim().equalsIgnoreCase(""))
                {
                    passtxt.setError("Enter Password");
                }
                else if (phonetxt.getText().toString().trim().length()<11 || phonetxt.getText().toString().trim().length()>11)
                {
                    phonetxt.setError("Invalid Number");
                }
                else
                {
                    Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                    startActivity(intent);
                    SignupActivity.this.finish();
                }

            }
        });
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
                SignupActivity.this.finish();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String bloodgroup = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
