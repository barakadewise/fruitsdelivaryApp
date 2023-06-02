package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fruitsapp.R;

public class Intro extends AppCompatActivity {
    //declare button
    private Button Getstarted ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //find by Id
        Getstarted=findViewById(R.id.Getstarted);

        //set onclick listener
        Getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Login.class);
                startActivity(intent);

            }
        });


    }
}