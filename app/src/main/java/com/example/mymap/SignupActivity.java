package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText phonetxt, passtxt, username, emailid;
    private Button signupbtn;
    private TextView logintxt;
    private Spinner spinner;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://mymap-9ae65-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users");


        phonetxt = findViewById(R.id.txtsignupphone);
        passtxt = findViewById(R.id.txtsignuppassword);
        signupbtn = findViewById(R.id.btnsignup);
        logintxt = findViewById(R.id.txtviewlogin);
        username = findViewById(R.id.username);
        emailid = findViewById(R.id.txtsignupemail);
        spinner = findViewById(R.id.spinnerbg);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignupActivity.this, R.layout.custom_spinner, getResources().getStringArray(R.array.blood_group));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (username.getText().toString().trim().equalsIgnoreCase("")) {
                    username.setError("Enter Name");
                } else if (phonetxt.getText().toString().trim().equalsIgnoreCase("")) {
                    phonetxt.setError("Enter Number");
                } else if (emailid.getText().toString().isEmpty()) {
                    emailid.setError("Enter E-mail");
                } else if (passtxt.getText().toString().trim().equalsIgnoreCase("")) {
                    passtxt.setError("Enter Password");
                } else if (phonetxt.getText().toString().trim().length() < 11 || phonetxt.getText().toString().trim().length() > 11) {
                    phonetxt.setError("Invalid Number");
                } else {
                    final String email = emailid.getText().toString();
                    String password = passtxt.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this, "Error, Please try again", Toast.LENGTH_SHORT).show();

                            } else {
                                signupbtn.setEnabled(false);
                                Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                startActivity(intent);
                                SignupActivity.this.finish();
                                adduser(email);
                            }
                        }
                    });

                }

            }
        });
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
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

    private void adduser(String email) {
        String name = username.getText().toString().trim();
        String phone = phonetxt.getText().toString().trim();
        String bloodgroup = spinner.getSelectedItem().toString();
        String id = databaseReference.push().getKey();
        User user = new User(id, name, phone, bloodgroup, email);
        databaseReference.child(id).setValue(user);
        Toast.makeText(this, "Info Added to database", Toast.LENGTH_SHORT).show();

    }
}
