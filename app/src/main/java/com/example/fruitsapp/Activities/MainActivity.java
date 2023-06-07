package com.example.fruitsapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruitsapp.Adapter.CategoryAdapter;
import com.example.fruitsapp.Adapter.PopularAdapter;
import com.example.fruitsapp.Domain.Category;
import com.example.fruitsapp.Domain.Popular;
import com.example.fruitsapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;
    private TextView addText;
    private FirebaseAuth firebaseAuth;

    //declare the bottom nav
    private MaterialTextView userNametext;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //retrieve firebase data
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        //recycleViewlist functions for both popular and categories
        recyclerViewCategory();
        setRecyclerViewPopular();
        bottomnav();



    }
    @Override
    protected void onStart() {
        super.onStart();
        userNametext=findViewById(R.id.materialTextView);
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            userNametext.setText(email);
        }
    }

    //bottom nav functionality
    public void bottomnav(){
        ImageView homeBtton,notifactioBtton,supportButton,settingBttn;
        homeBtton=findViewById(R.id.homeBtton);
        notifactioBtton=findViewById(R.id.notifactioBtton);
        supportButton=findViewById(R.id.supportButton);
        settingBttn=findViewById(R.id.settingBttn);

        notifactioBtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });
        settingBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingIntent = new Intent(getApplicationContext(),Setting.class);
                startActivity(settingIntent);
            }
        });

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
        popular.add(new Popular("Berries","berries","Berries are fiber-rich fruits that aid digestion and provide a feeling of satiety. They contain natural sugars like fructose for quick energy",1000));
        popular.add(new Popular("Grapes","grapes","Grapes are good source of dietary fiber, which aids in digestion and promotes satiety. They contain natural sugars, mainly fructose, and provide a quick source of energy.",500));
        popular.add(new Popular("Papaw","papaw","Papaya, also known as papaw, is a tropical fruit with a sweet and juicy taste. It is a good source of vitamin C, fiber, and antioxidants, offering digestive benefits and supporting overall health.",1000));
        popular.add(new Popular("Guava","guava","Guava is a tropical fruit known for its sweet and tangy flavor. It is rich in vitamin C, fiber, and antioxidants, promoting immune health and digestion",500));
        popular.add(new Popular("Avocado","avocado","Avocado is a nutrient-dense fruit that is rich in healthy fats, fiber, and vitamins. It is packed with monounsaturated fats, which can help lower bad cholesterol levels.",1500));
        popular.add(new Popular("Pineapples","pineapples"," Pineapple is also a good source of vitamin C, manganese, and dietary fiber. Its sweet and tangy flavor adds a refreshing twist to both sweet and savory dishes.",1500));

         adapter2 = new PopularAdapter(popular);
         recyclerViewPopularList.setAdapter(adapter2);



    }


}