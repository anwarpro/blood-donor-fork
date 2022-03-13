package com.example.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {
    EditText adminname,adminpass;
    Button adminbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminname = findViewById(R.id.txtadminname);
        adminpass = findViewById(R.id.txtadminpass);
        adminbtn = findViewById(R.id.btnadmin);

        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adminname.getText().toString().trim().isEmpty() || adminpass.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(AdminActivity.this, "Enter Credentials", Toast.LENGTH_SHORT).show();
                }
                else if (adminname.getText().toString().trim().equals("admin") && adminpass.getText().toString().trim().equals("123456"))
                {
                    Intent intent = new Intent(AdminActivity.this,AddByAdmin.class);
                    startActivity(intent);
                    AdminActivity.this.finish();
                }
                else
                {
                    Toast.makeText(AdminActivity.this, "Invalid ID", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
