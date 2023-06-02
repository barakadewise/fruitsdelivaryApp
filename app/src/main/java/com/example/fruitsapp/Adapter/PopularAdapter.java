package com.example.fruitsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitsapp.Activities.ShowDetailsActivity;
import com.example.fruitsapp.Domain.Category;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.R;

import java.util.ArrayList;
import com.bumptech.glide.Glide;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<Popular>popular;
    public PopularAdapter(ArrayList<Popular> popular) {
        this.popular = popular;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.popularTitle.setText(popular.get(position).getTitle());
        holder.popularFee.setText(String.valueOf(popular.get(position).getFee()));


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popular.get(position).getPic(),"drawable",holder.itemView.getContext().getOpPackageName());
            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .into(holder.popularPic);

            holder.addCartText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent holderItemIntent =new Intent(holder.itemView.getContext(),ShowDetailsActivity.class);
                    holderItemIntent.putExtra("objects",popular.get(holder.getAdapterPosition()));
                    holder.itemView.getContext().startActivity(holderItemIntent);
                }
            });
        }



    }


    @Override
    public int getItemCount() {
        return popular.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView popularTitle,popularFee,addCartText;
        ImageView popularPic;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            popularTitle=itemView.findViewById(R.id.textTitle);
            popularFee =itemView.findViewById(R.id.textAmount);
            addCartText =itemView.findViewById(R.id.addCartText);
            popularPic=itemView.findViewById(R.id.imagePopular);


        }
    }


}
