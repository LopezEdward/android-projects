package dev.edwlopez.app3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.edwlopez.app3.views.Test;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get button
        this.loginButton = this.findViewById(R.id.button_input);

        this.loginButton.setOnClickListener(new ClickAction(this, Test.class));
    }

    private class ClickAction implements View.OnClickListener {
        private AppCompatActivity appRef;
        private Class<?> pageClass;

        public ClickAction (AppCompatActivity appRef, Class<?> pageClass) {
            this.appRef = appRef;
            this.pageClass = pageClass;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(appRef.getApplicationContext(), pageClass);
            appRef.startActivity(intent);
        }
    }
}