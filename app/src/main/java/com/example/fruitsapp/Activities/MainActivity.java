package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fruitsapp.Adapter.CategoryAdapter;
import com.example.fruitsapp.Adapter.PopularAdapter;
import com.example.fruitsapp.Domain.Category;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private TextView addText;

    //declare the bottom nav
    private BottomNavigationView Bottom_nav;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //recycleViewlist functions for both popular and categories
        recyclerViewCategory();
        setRecyclerViewPopular();
        bottomnav();



    }
    //bottom nav functionality
    public void bottomnav(){
        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<Category> category =new ArrayList<>();
        category.add(new Category("Mango","banana"));
        category.add(new Category("Banana","apple"));
        category.add(new Category("Oranges","mango"));
        category.add(new Category("Apple","orange"));

        adapter =new CategoryAdapter(category);
        recyclerViewCategoryList.setAdapter(adapter);


    }
    private void setRecyclerViewPopular(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<Popular> popular=new ArrayList<>();
        popular.add(new Popular("Berries","banana","Berries are fiber-rich fruits that aid digestion and provide a feeling of satiety. They contain natural sugars like fructose for quick energy",1000));
        popular.add(new Popular("Grapes","orange","Grapes are good source of dietary fiber, which aids in digestion and promotes satiety. They contain natural sugars, mainly fructose, and provide a quick source of energy.",500));
        popular.add(new Popular("Papaw","mango","Papaya, also known as papaw, is a tropical fruit with a sweet and juicy taste. It is a good source of vitamin C, fiber, and antioxidants, offering digestive benefits and supporting overall health.",1000));
        popular.add(new Popular("Guava","mango","Guava is a tropical fruit known for its sweet and tangy flavor. It is rich in vitamin C, fiber, and antioxidants, promoting immune health and digestion",500));
        popular.add(new Popular("Avocado","orange","Avocado is a nutrient-dense fruit that is rich in healthy fats, fiber, and vitamins. It is packed with monounsaturated fats, which can help lower bad cholesterol levels.",1500));
        popular.add(new Popular("Pineapples","apple"," Pineapple is also a good source of vitamin C, manganese, and dietary fiber. Its sweet and tangy flavor adds a refreshing twist to both sweet and savory dishes.",1500));

         adapter2 = new PopularAdapter(popular);
         recyclerViewPopularList.setAdapter(adapter2);



    }


}