package com.example.esnbilbao2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private EditText mEditTextEmail;
    private EditText mEditTextPass;
    private Button mbntLogin;

    private String email = "";
    private String password ="";

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        mAuth = FirebaseAuth.getInstance();

        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPass =  (EditText) findViewById(R.id.editTextPass);
        mbntLogin = (Button) findViewById(R.id.bntLogin);


        mbntLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            email = mEditTextEmail.getText().toString()+"gmail.com";
            password = mEditTextPass.getText().toString();


            if (!email.isEmpty()&& !password.isEmpty()){
                loginUser();
            } else {
                Toast.makeText(LoginActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loginUser(){
        
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Error al tratar de iniciar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}