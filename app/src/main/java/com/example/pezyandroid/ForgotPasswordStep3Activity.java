package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordStep3Activity extends AppCompatActivity {

    private Context fContext;

    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step3);


        fContext = this;

        bindObj();

        onConfirmClick();

    }

    private void onConfirmClick() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void bindObj() {
        btnConfirm = findViewById(R.id.btnConfirm);
    }
}
