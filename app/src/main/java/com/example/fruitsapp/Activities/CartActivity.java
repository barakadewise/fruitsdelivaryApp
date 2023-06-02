package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

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
        double percentageTax =0.18;
        double delivaryFee =1000.0;

        double tax = Math.round(managementCart.getTotalFee()*percentageTax);
        double total=Math.round(managementCart.getTotalFee());
        double itemTotal =Math.round(managementCart.getTotalFee());


        totalFeeText.setText("Sh"+itemTotal+"/=");
        taxfeeText.setText("Sh"+tax+"/=");
        totalFee.setText("Sh"+total+"/=");
//        delivaryFeeText.setText("Sh"+delivaryFee+"/=");

    }
}
