package com.example.openmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity {
    private EditText editTextFullName,editTextEmail,editTextItemDescription
            ,editTextPrice,editTextPassword,editTextAddress;
    private Button registerUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        registerUser = findViewById(R.id.buttonRegisterUser);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextItemDescription = findViewById(R.id.editTextItem);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextAddress = findViewById(R.id.editTextAddress);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser(){
        String email = editTextEmail.getText().toString();
        String name = editTextFullName.getText().toString();
        String item = editTextItemDescription.getText().toString();
        String price = editTextPrice.getText().toString();
        String password = editTextPassword.getText().toString();
        String address = editTextAddress.getText().toString();

        if(name.isEmpty()){
            editTextFullName.setError("Full name is required");
            editTextFullName.requestFocus();
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
        }

        if(item.isEmpty()){
            editTextItemDescription.setError("Item and description are required");
            editTextItemDescription.requestFocus();
        }

        if(price.isEmpty()){
            editTextPrice.setError("Price is required");
            editTextPrice.requestFocus();
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
        }

        if(address.isEmpty()){
            editTextAddress.setError("Address is required");
            editTextAddress.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    User user = new User(email,name,item,price,password,address);

                    FirebaseDatabase.getInstance().getReference("OpenMarket")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this,
                                                "User has been registered",
                                                Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(RegisterUser.this,
                                                "Failed to register try again",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });



    }
}