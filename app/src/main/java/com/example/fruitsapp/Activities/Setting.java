package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitsapp.R;

public class Setting extends AppCompatActivity {
    private TextView logoutOption;
    private TextView privacyTermsOption;
    private Switch darkModeSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        // Find views by their IDs
        logoutOption = findViewById(R.id.logoutOption);
        privacyTermsOption = findViewById(R.id.privacyTermsOption);
        darkModeSwitch = findViewById(R.id.darkModeSwitch);

        // Set click listeners
        logoutOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout action
                logout();
            }
        });

        privacyTermsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open privacy terms activity or dialog
                openPrivacyTerms();
            }
        });

        // Set switch listener for dark mode
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Enable dark mode
                    enableDarkMode();
                } else {
                    // Enable light mode
                    enableLightMode();
                }
            }
        });
    }

    private void logout() {
        // Perform logout action
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
        // Start LoginActivity and clear back stack
        finishAffinity(); // Close all activities of the app
        System.exit(0);
    }

    private void openPrivacyTerms() {
        // Open privacy terms activity or dialog
        Toast.makeText(this, "Opening Privacy Terms", Toast.LENGTH_SHORT).show();
        // Example: Start PrivacyTermsActivity
        Intent intent = new Intent(this, PrivacyTerms.class);
        startActivity(intent);
    }

    private void enableDarkMode() {
        // Enable dark mode
        Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show();
        UiModeManager uiModeManager = (UiModeManager) getSystemService(Context.UI_MODE_SERVICE);
        if (uiModeManager!= null) {
            uiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
        }

        // Manually update the current theme and UI
        getDelegate().applyDayNight();


    }

    private void enableLightMode() {
        // Enable light mode
        Toast.makeText(this, "Light Mode Enabled", Toast.LENGTH_SHORT).show();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        recreate();

    }
}