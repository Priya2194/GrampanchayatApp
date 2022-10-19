package com.example.grampanchayatapp;

import static android.provider.Telephony.Carriers.PASSWORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{
    private EditText email,password;
    private Button loginL;
    private ProgressBar lProgressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.emailLl);
        password=findViewById(R.id.passwordL);
        loginL=findViewById(R.id.loginL);

        lProgressBar=findViewById(R.id.loginProgresss);
        auth=FirebaseAuth.getInstance();

        loginL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String Email=email.getText().toString().trim();
            String Password=password.getText().toString().trim();

            if (Email.isEmpty())
            {
                email.setError("Please Enter the Email");
                email.requestFocus();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
            {
                email.setError("Email is not valid");
                email.requestFocus();
                return;
            }
            if (Password.isEmpty()) {
                password.setError("Please enter password");
                password.requestFocus();
                return;
            }

            if (Password.length() >8) {
                password.setError(" Maximum length of password is 8 ");
                password.requestFocus();
                return;
            }
            loginL.setVisibility(View.INVISIBLE);
            lProgressBar.setVisibility(View.VISIBLE);

            auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        startActivity(new Intent(LoginActivity.this,NavigationDrawer.class));
                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                        loginL.setVisibility(View.VISIBLE);
                        lProgressBar.setVisibility(View.GONE);

                    }

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            loginL.setVisibility(View.VISIBLE);
                            lProgressBar.setVisibility(View.GONE);                       }
                    });
        }
    });
    }


    public void registerL(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getUid() != null) {
            Intent intent = new Intent(LoginActivity.this,NavigationDrawer.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}