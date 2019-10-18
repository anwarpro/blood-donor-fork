package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView signupnow;
    private EditText phonetxt,passtxt;
    private Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phonetxt = findViewById(R.id.txtloginphone);
        passtxt = findViewById(R.id.txtloginpassword);
        loginbtn = findViewById(R.id.btnlogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phonetxt.getText().toString().trim().equalsIgnoreCase(""))
                {
                    phonetxt.setError("Enter Number");
                }
                else if (passtxt.getText().toString().trim().equalsIgnoreCase(""))
                {
                    passtxt.setError("Enter password");
                }
                else
                {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
            }
        });

        signupnow = findViewById(R.id.txtviewsignup);
        signupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });
    }
}
