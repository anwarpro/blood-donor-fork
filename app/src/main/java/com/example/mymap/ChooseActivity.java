package com.example.mymap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ChooseActivity extends AppCompatActivity {
    public static final String USER_BLOOD = "A+";
    TextView a1,a2,b1,b2,o1,o2,ab1,ab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        a1 = findViewById(R.id.bApos);
        a2 = findViewById(R.id.bAneg);
        b1 = findViewById(R.id.bBpos);
        b2 = findViewById(R.id.bBneg);
        o1 = findViewById(R.id.bOpos);
        o2 = findViewById(R.id.bOneg);
        ab1 = findViewById(R.id.bABpos);
        ab2 = findViewById(R.id.bABneg);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseActivity.this,DonorActivity.class);
                intent.putExtra(USER_BLOOD,"A+");
                startActivity(intent);
            }
        });
    }
}
