package com.example.app2;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Instagram extends AppCompatActivity {
    // Static url for Instagram web site.
    private static final String INSTAGRAM_URL = "https://instagram.com";
    // Get the WebView element in the Activity
    //private final WebView MAIN_WEB_VIEW = this.findViewById(R.id.seeInstagram);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instagram);

        // Set the URL to WebView in the activity
        WebView vWeb = (WebView) findViewById(R.id.seeInstagram);
        vWeb.loadUrl(INSTAGRAM_URL);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}