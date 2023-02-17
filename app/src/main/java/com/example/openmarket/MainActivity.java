package com.example.openmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The App allows the user to shop for locally grown foods ranging from vegetables,
 * eggs,cheese,and meat.  The users of the app have their own database with their products in
 * firebase that are displayed to other app users(This would be displayed in the RecyclerView).
 * The menus of the app allow the user to search for different types of foods and order
 * the results by price(Menus). Once the user has selected the item the app displays the location
 * of the other user selling the product using google maps(Feature to learn by myself).
 * User settings to change the font of the app and the backgrounds on the app(User Settings).
 *
 * Colors
 * Purple: 9933cc
 * Yellow: ffcc00
 * Green: 72c156
 **/
public class MainActivity extends AppCompatActivity{

    //Declaring Variables
    private EditText userEmail;
    private EditText passwordEditText;
    private Button loginButton;
    private  Button registerUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonRegisterUser);
        registerUser = findViewById(R.id.buttonRegister);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view->{
            loginUser();
        });

        registerUser.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,RegisterUser.class));
        });

    }

    //Method that takes text from the editText and logs the user into firebase
    private void loginUser(){
        String user = userEmail.getText().toString();
        String password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(user)){
            userEmail.setError("Email cannot be empty");
            userEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            passwordEditText.setError("Password cannot be empty");
            passwordEditText.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, Market.class));
                    }else{
                        Toast.makeText(MainActivity.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

