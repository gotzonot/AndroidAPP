package com.example.esnbilbao2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPass;
    private Button mbntReg;
    private Button mbntSendLogin;

    //Variables

    private String name ="";
    private String email ="";
    private String password ="";

    //Firebse Auth & Database
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_activity);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        mEditTextName = findViewById(R.id.editTextName);
        mEditTextEmail = findViewById(R.id.editTextEmail);
        mEditTextPass =  findViewById(R.id.editTextPass);
        mbntReg = findViewById(R.id.bntReg);
        mbntSendLogin = findViewById(R.id.bntSendLogin);

        mbntReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mEditTextName.getText().toString();
                email = mEditTextEmail.getText().toString();
                password = "123456";
                password = password.toString();


                if (!name.isEmpty()&&!email.isEmpty()){

                    if (password.length() >=6){

                    }
                    registeruser();

                } else {
                    Toast.makeText(TestActivity.this, "Debe tener una contraseña con almenos 6 caracteres", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mbntSendLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(TestActivity.this, ProfileActivity.class));
            finish();
        }
    }



    private void registeruser(){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Map<String, Object> datos = new HashMap<>();
                    datos.put("name", name);
                    datos.put("email", email);
                    datos.put("password", password);
                    String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    mDatabase.child("Usuarios").child(id).setValue(datos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(TestActivity.this, ProfileActivity.class));
                                finish();
                            }
                            else {
                                Toast.makeText(TestActivity.this, "Error Nº5", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(TestActivity.this, "No se ha podido registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}