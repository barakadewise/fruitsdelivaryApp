package com.example.fruitsapp.Activities;




import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitsapp.R;
import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {
    private EditText name,address,email,password,confirmPassword;
    private Button Register;
    private TextView textGotologin;
    private CheckBox checkbox;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //find by respective id
        name =findViewById(R.id.name);
        address =findViewById(R.id.address);
        email =findViewById(R.id.email);
        password =findViewById(R.id.password);
        confirmPassword =findViewById(R.id.confirmPassword);
        Register=findViewById(R.id.Register);
        textGotologin=findViewById(R.id.textGotologin);
        checkbox=findViewById(R.id.checkbox);
        progressBar=findViewById(R.id.progressBar);

        //set onclick listerner to register button
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //create string for the inputs
                String user_name=name.getText().toString().trim();
                String user_address =address.getText().toString().trim();
                String user_email=email.getText().toString().trim();
                String user_password=password.getText().toString().trim();
                String user_Confirmpassword =confirmPassword.getText().toString().trim();

                if (user_name.isEmpty()||user_address.isEmpty()||user_email.isEmpty()||user_password.isEmpty()||user_Confirmpassword.isEmpty()){
                    Toast.makeText(Register.this, "Fill all the given fields to proceed!", Toast.LENGTH_SHORT).show();
                } else if (!user_password.equals(user_Confirmpassword)){
                    Toast.makeText(Register.this, "Password don't match!", Toast.LENGTH_SHORT).show();

                } else if (!(checkbox.isChecked())) {
                    checkbox.setTextColor(Color.RED);
                    Toast.makeText(Register.this, "Agree to our privacy terms and conditions!", Toast.LENGTH_SHORT).show();

                } else {
                    Intent mainCtivityintent =new Intent(Register.this,MainActivity.class);
                    startActivity(mainCtivityintent); /// BELOW IS FIREBASE REFISTRAION CODE

                    /////BELOW IS THE FIREBASE REGISTRATION CODE///////

//                    mAuth = FirebaseAuth.getInstance();
//                    //create user using email and password
//                    mAuth.createUserWithEmailAndPassword(user_email, user_password)
//                            .addOnCompleteListener(com.example.fruitsapp.Activities.Register.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    progressBar.setVisibility(View.GONE);
//                                    if (task.isSuccessful()) {
//                                            //registering the user
//                                            FirebaseUser user = mAuth.getCurrentUser();
//                                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//                                            User newUser = new User();
//                                            newUser.setName(user_name);
//                                            newUser.setAddress(user_address);
//                                            assert user != null;
//                                            mDatabase.child("users").child(user.getUid()).push().setValue(newUser);
//                                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
//
//                                            //if successfully move to login panel
//                                        Intent loginIntent =new Intent(com.example.fruitsapp.Activities.Register.this,Login.class);
//                                        startActivity(loginIntent);
//                                        finish();
//
//
//                                    } else {
//
//                                        Toast.makeText(Register.this, "Registration Failed!.",
//                                                Toast.LENGTH_SHORT).show();
//
//                                    }
//                                }
//                            });
//
                }

            }
        });

        //set onclick listener to the textGotologin
        textGotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

}