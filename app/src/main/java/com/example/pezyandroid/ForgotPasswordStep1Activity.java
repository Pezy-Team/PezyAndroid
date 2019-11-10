package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordStep1Activity extends AppCompatActivity {

    private Context fContext;

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_step1);

        fContext = this;

        bindObj();

        onNextClick();
    }

    private void onNextClick() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, ForgotPasswordStep2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void bindObj() {
        btnNext = findViewById(R.id.btnNext);
    }
}
