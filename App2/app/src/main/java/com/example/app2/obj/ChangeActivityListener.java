package com.example.app2.obj;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeActivityListener implements View.OnClickListener {
    private final AppCompatActivity appRef;
    private final Class<?> activityClass;
    public ChangeActivityListener (AppCompatActivity appRef, Class<?> activityClass) {
        this.activityClass = activityClass;
        this.appRef = appRef;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(appRef.getApplicationContext(), activityClass);

        appRef.startActivity(intent);
    }
}
