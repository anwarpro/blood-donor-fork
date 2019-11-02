package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView signupnow;
    private EditText phonetxt,passtxt;
    private Button loginbtn, signupbtn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        phonetxt = findViewById(R.id.txtloginphone);
        passtxt = findViewById(R.id.txtloginpassword);
        loginbtn = findViewById(R.id.btnlogin);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser!=null)
                {

                        Toast.makeText(LoginActivity.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                    loginbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (phonetxt.getText().toString().trim().equalsIgnoreCase(""))
                            {
                                phonetxt.setError("Enter E-mail");
                            }
                            else if (passtxt.getText().toString().trim().equalsIgnoreCase(""))
                            {
                                passtxt.setError("Enter password");
                            }
                            else if (!phonetxt.getText().toString().isEmpty()&&!passtxt.getText().toString().isEmpty())
                            {
                                String email = phonetxt.getText().toString();
                                String password  = passtxt.getText().toString();

                                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()){
                                            Toast.makeText(LoginActivity.this, "Login Failed, Try again", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                                            Intent intenthome = new Intent(LoginActivity.this,HomeActivity.class);
                                            startActivity(intenthome);
                                            LoginActivity.this.finish();
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        };

        //loginbtn_listener_was_here_before

        signupbtn = findViewById(R.id.btnsignin);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}
