package com.example.fruitsapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fruitsapp.NetworkCheck.Networkcheck;
import com.example.fruitsapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Button loginButton;

    private TextView textGotoregister;

    private EditText email,password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //networkcheck
        Networkcheck networkcheck= Networkcheck.getInstance(this);


        //find by their id
        loginButton =findViewById(R.id.loginButton);
        email =findViewById(R.id.email);
        password =findViewById(R.id.password);
        textGotoregister=findViewById(R.id.textGotoregister);
        progressBar=findViewById(R.id.progressBar);


        //set on click listener to the button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //Convert inputs to string
                String user_email =email.getText().toString();
                String user_password =password.getText().toString();

                //check if empty
                if(user_email.isEmpty()||user_password.isEmpty()){
                    Toast.makeText(Login.this, "Username and password required!", Toast.LENGTH_SHORT).show();

                }else {
//                    Intent mainActivityIntent = new Intent(Login.this,MainActivity.class);
//                    startActivity(mainActivityIntent); //BELOW IS TE FIREBASE CODE FOR LOGIN USER
                    mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(user_email, user_password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(Login.this, "Successfully logged in!", Toast.LENGTH_SHORT).show();
                                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        email.setTextColor(Color.RED);
                                        password.setTextColor(Color.RED);
                                        Toast.makeText(Login.this, "Invalid username or password!..", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

        //set onclick listener to the textGotoRegister
        textGotoregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });
    }
}