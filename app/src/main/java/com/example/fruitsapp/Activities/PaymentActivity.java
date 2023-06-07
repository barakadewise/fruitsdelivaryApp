package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.fruitsapp.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //gif display function
        viewGifs();
    }

    private void viewGifs() {
        ImageView imageView =findViewById(R.id.imageView2);
        Glide.with(this).load(R.drawable.success).into(imageView);
    }
}