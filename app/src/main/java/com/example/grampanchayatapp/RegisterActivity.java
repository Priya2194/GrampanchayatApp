package com.example.grampanchayatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grampanchayatapp.Model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private EditText name,email,address,phone,password;
    private Button register;


    private ProgressBar rProgressbar;
    private FirebaseAuth auth;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.name);
        email=findViewById(R.id.emailR);
        address=findViewById(R.id.addressR);
        phone=findViewById(R.id.phoneR);
        password=findViewById(R.id.passwordR);
        //logintext=findViewById(R.id.loginText);

        register=findViewById(R.id.register);
        rProgressbar=findViewById(R.id.registeProgressBar);


        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance().getReference("User");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final String NAME=name.getText().toString().trim();
                final String EMAIL=email.getText().toString().trim();
                final String ADDRESS=address.getText().toString().trim();
                final String PHONE=phone.getText().toString().trim();
                final String PASSWORD=password.getText().toString().trim();

                if (NAME.isEmpty())
                {
                    name.setError("Please Enter Name");
                    name.requestFocus();
                    return;
                }

                if (EMAIL.isEmpty()) {
                    email.setError("Please enter email");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(EMAIL).matches()) {
                    email.setError("Email is not valid");
                    email.requestFocus();
                    return;
                }

                if (ADDRESS.isEmpty()) {
                    address.setError("Please enter Address");
                    address.requestFocus();
                    return;
                }

                if (PHONE.isEmpty())
                {
                    phone.setError("Please Enter Phone");
                    phone.requestFocus();
                    return;
                }
                if (PASSWORD.isEmpty()) {
                    password.setError("Please enter password");
                    password.requestFocus();
                    return;
                }

                if (PASSWORD.length() >8) {
                    password.setError("Maximum length of password is 8");
                    password.requestFocus();
                    return;
                }
                register.setVisibility(View.INVISIBLE);
                rProgressbar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(EMAIL,PASSWORD).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String id=database.push().getKey();
                        UserModel userModel=new UserModel(id,NAME,EMAIL,ADDRESS,PHONE,PASSWORD);
                        database.child(id).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                if (task.isSuccessful())
                                {
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                                    register.setVisibility(View.VISIBLE);
                                    rProgressbar.setVisibility(View.GONE);
                                }

                            }
                        })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        register.setVisibility(View.VISIBLE);
                                        rProgressbar.setVisibility(View.GONE);
                                    }
                                });

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                register.setVisibility(View.VISIBLE);
                                rProgressbar.setVisibility(View.GONE);
                            }
                        });

            }
        });


    }


    public void login(View view)
    {
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
   /* @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getUid() != null) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }*/
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}