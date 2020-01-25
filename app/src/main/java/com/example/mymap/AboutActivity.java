package com.example.mymap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webview = findViewById(R.id.webviewport);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://evanemran.github.io/portfolio/");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemadmin:
                Intent intent = new Intent(AboutActivity.this,AdminActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if (webview.canGoBack())
        {
            webview.goBack();
        }
        else
        {
            super.onBackPressed();
        }
    }
}

