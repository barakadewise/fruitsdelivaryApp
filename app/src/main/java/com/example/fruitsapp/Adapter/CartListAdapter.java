package com.example.fruitsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.Helper.ManagementCart;
import com.example.fruitsapp.Interface.ChangeNumerItemsListener;
import com.example.fruitsapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<Popular>popular;
    private ManagementCart managementCart;
    private ChangeNumerItemsListener changeNumerItemsListener;

    public CartListAdapter(ArrayList<Popular> popular, Context context, ChangeNumerItemsListener changeNumerItemsListener) {
        this.popular = popular;
        this.managementCart = new ManagementCart(context);
        this.changeNumerItemsListener = changeNumerItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(String.valueOf(popular.get(position).getTitle()));
        holder.feeItemtext.setText(String.valueOf(popular.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round(popular.get(position).getNumberIncart()*popular.get(position).getFee()*100)/100));
        holder.num.setText(String.valueOf(popular.get(position).getNumberIncart()));

        int drawableResourceId= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popular.get(position).getPic(),"drawable",holder.itemView.getContext().getOpPackageName());
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.imageView);
        }
        //add more item when add button is clicked
        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberItems(popular, position, new ChangeNumerItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumerItemsListener.change();
                    }
                });
            }
        });

        //reduce cart elements when the minus button is clicked
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberItems(popular, position, new ChangeNumerItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumerItemsListener.change();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return popular.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeItemtext,totalEachItem,num;
        ImageView imageView,plusBtn,minusBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textTitle);
            feeItemtext=itemView.findViewById(R.id.feeItemtext);
            imageView=itemView.findViewById(R.id.imageView);
            totalEachItem=itemView.findViewById(R.id.feeItemtextTotal);
            plusBtn=itemView.findViewById(R.id.plusBtn);
            minusBtn=itemView.findViewById(R.id.minusBtn);
            num=itemView.findViewById(R.id.textNumber);
        }
    }
}
