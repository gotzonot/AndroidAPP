package com.example.esnbilbao2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textViewForgottenPassword;
    //private TextView TextoPrueba;


    //BD
   //DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
    //DatabaseReference mRootChild = mDatabaseReference.child("texto");

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI(); //Llamamos a hideSystemUI para que se ejecute.
        }
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    } //Ocultamos la barra de navegación y de notificaciones superior


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIdForVariables();
        textViewForgottenPassword.setOnClickListener(this);
    }

    private void getIdForVariables() {


        textViewForgottenPassword = findViewById(R.id.text_view_forgotten_password_login_main_activity);
        // Asignamos la abrebiatura textViewForgottenPassword al

    }

    public void onClick(View v) {
        if (v == textViewForgottenPassword) {
            Intent intent = new Intent(MainActivity.this, events_activity.class);
            //Al estar en el MainActivity y al hacer click en el texto de textViewForgottenPassword
         //  cambiamos a la actividad events_activity.
            intent.putExtra("EXTRA", "0");
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}
