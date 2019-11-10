package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    private EditText txtUsername, txtPassword;
    private Button btnLogin, btnFBLogin, btnRegister;
    private TextView txtForgotPwd;
    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fContext = this;

        bindObj();

        onRegisterClick();

        onForgotPwdClick();
    }

    private void onForgotPwdClick() {
        txtForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, ForgotPasswordStep1Activity.class);
                startActivity(intent);
            }
        });
    }

    private void onRegisterClick() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindObj() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnFBLogin = findViewById(R.id.btnFBLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtForgotPwd = findViewById(R.id.txtForgotPwd);
    }
}
