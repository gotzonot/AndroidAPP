package com.example.esnbilbao2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    private TextView  textViewForgottenPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        getIdForVariables();
        textViewForgottenPassword.setOnClickListener(this);
    }
private void getIdForVariables() {


    textViewForgottenPassword = findViewById(R.id.text_view_forgotten_password_login_main_activity);


}
    public void onClick(View v) {
        if (v == textViewForgottenPassword) {
            Intent intent = new Intent(LoginActivity.this, ContactForm.class);
            intent.putExtra("EXTRA", "0");
            startActivity(intent);
        }
    }


}