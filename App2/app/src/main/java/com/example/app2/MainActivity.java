package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app2.obj.ChangeActivityListener;

public class MainActivity extends AppCompatActivity {
    // This need set in same positions of buttons in screen
    private final static Class<?>[] ACTIVITIES_TO_SHOW = {Web.class, Facebook.class, Instagram.class, Whatsapp.class};
    private View[] buttons;

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

        this.buttons = this.getButtons(this.findViewById(R.id.buttonsView));
        this.addOnClickEvent(this.buttons);
    }

    private View[] getButtons (ViewGroup parent) {
        int numberOfChildren = parent.getChildCount();
        View[] buttons = new View[numberOfChildren];

        for (int i = 0; i < numberOfChildren; i++) {
            View view = parent.getChildAt(i);

            if (view instanceof ImageView) buttons[i] = view;
        }

        return buttons;
    }

    private void addOnClickEvent (View[] targets) {
        for (int i = 0; i < targets.length; i++) {
            targets[i].setOnClickListener(new ChangeActivityListener(this, ACTIVITIES_TO_SHOW[i]));
        }
    }
}