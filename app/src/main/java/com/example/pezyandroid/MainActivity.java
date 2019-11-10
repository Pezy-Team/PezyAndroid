package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private EditText txtUsername, txtPassword;
    private Button btnLogin, btnFBLogin, btnRegister;
    private TextView txtForgotPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bindObj();
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
