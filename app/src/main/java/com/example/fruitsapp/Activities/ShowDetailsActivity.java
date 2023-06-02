package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.Helper.ManagementCart;
import com.example.fruitsapp.R;
import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

public class ShowDetailsActivity extends AppCompatActivity {
    private MaterialButton addTocartButton;
    private TextView titleDetailsText,feeText,descriptionText,numberCountOder;

    private ImageView detailsPc,Plus,Minus;
    private Popular objects;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        //cart management
        managementCart=new ManagementCart(this);

       //create a view function for details!
        intView();
        getBudle();
    }
    //initialize number of oder requested by the customer
    int numberoder=1;
    @SuppressLint("SetTextI18n")
    private void getBudle() {
        objects= (Popular) getIntent().getSerializableExtra("objects");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            int drawabaleResourceId =this.getResources().getIdentifier(objects.getPic(),"drawable",this.getOpPackageName());
            Glide.with(this).
                    load(drawabaleResourceId).
                    into(detailsPc);
            titleDetailsText.setText(objects.getTitle());
            feeText.setText("Sh "+objects.getFee());
            descriptionText.setText(objects.getDescription());
            numberCountOder.setText(String.valueOf(numberoder));

        }
        //set onclicklistener to the add  button

        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberoder = numberoder +1;
                numberCountOder.setText(String.valueOf(numberoder));
            }
        });
        //set onclicklick listener to minus button
        Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberoder>1){
                numberoder=numberoder-1;
                numberCountOder.setText(String.valueOf(numberoder));
                }
                else {
                    numberCountOder.setText(String.valueOf(numberoder));

                }
            }
        });
        //set click listener to the dd add cart button!
        addTocartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.setNumberIncart(numberoder);
                managementCart.insertPopular(objects);
                startActivity(new Intent(getApplicationContext(),CartActivity.class));

            }
        });


    }

    private void intView() {
        addTocartButton=findViewById(R.id.addTocartButton);
        titleDetailsText =findViewById(R.id.titleDetailsText);
        feeText=findViewById(R.id.feeText);
        descriptionText=findViewById(R.id.descriptionText);
        numberCountOder=findViewById(R.id.numberCountOder);
        detailsPc=findViewById(R.id.detailsPc);
        Plus=findViewById(R.id.Plus);
        Minus=findViewById(R.id.Minus);


    }
}