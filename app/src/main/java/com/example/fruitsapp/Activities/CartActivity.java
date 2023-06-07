package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitsapp.Adapter.CartListAdapter;
import com.example.fruitsapp.Helper.ManagementCart;
import com.example.fruitsapp.Interface.ChangeNumerItemsListener;
import com.example.fruitsapp.R;
import com.google.android.material.button.MaterialButton;

public class CartActivity extends AppCompatActivity {
    private MaterialButton checkOutButton;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private TextView totalFeeText,delivaryFeeText,taxfeeText,totalFee,emptyText;
    private double tax;
    private RecyclerView recyclerView;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart=new ManagementCart(this);
        intemView();
        showList();
        calculate();

    }

    private void intemView() {
        recyclerView=findViewById(R.id.recycleCartItem);
        totalFeeText=findViewById(R.id.totalFeeText);
        delivaryFeeText=findViewById(R.id.delivaryFeeText);
        taxfeeText=findViewById(R.id.taxfeeText);
        totalFee=findViewById(R.id.totalFee);
        scrollView=findViewById(R.id.scrollViewCrt);
        emptyText=findViewById(R.id.emptyText);
        checkOutButton=findViewById(R.id.checkOutButton);


    }
    @SuppressLint("SetTextI18n")
    public  void showList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(),this, new ChangeNumerItemsListener() {
            @Override
            public void change() {
                calculate();

            }
        });

        recyclerView.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("SetTextI18n")
    public void calculate(){
        //declared costs and tax inclusive

        double tax = Math.round(managementCart.getTotalFee()*0.16);
        double delivary =Math.round(managementCart.getTotalFee()*0.02);
        double itemTotal =Math.round(managementCart.getTotalFee());
        double total=Math.round(managementCart.getTotalFee())+delivary+tax;


        //set the values to cart to allow user to view costs:
        totalFeeText.setText("Sh"+itemTotal+"/=");
        taxfeeText.setText("Sh"+tax+"/=");
        totalFee.setText("Sh"+total+"/=");
        delivaryFeeText.setText("Sh"+delivary+"/=");


        //check if the total amount for payment
        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemTotal==0){
                    Toast.makeText(CartActivity.this, "No purchased cart,add cart to proceed!", Toast.LENGTH_SHORT).show();
                }
                else {
//                    ProgressBar progressBar = findViewById(R.id.progressBar);
//                    ImageView progressImage = findViewById(R.id.progressImage);
//
//                    // Set the visibility of progress bar and image to VISIBLE
//                    progressBar.setVisibility(View.VISIBLE);
//                    progressImage.setVisibility(View.VISIBLE);
//
//                   // Rotate the image
//                    ObjectAnimator rotation = ObjectAnimator.ofFloat(progressImage, "rotation", 0f, 360f);
//                    rotation.setDuration(4000);
//                    rotation.setRepeatCount(ObjectAnimator.INFINITE);
//                    rotation.setInterpolator(new LinearInterpolator());
//                    rotation.start();
                    Intent paymentIntent= new Intent(getApplicationContext(),PaymentActivity.class);
                    startActivity(paymentIntent);
                    finish();
                }
            }
        });

    }
}
