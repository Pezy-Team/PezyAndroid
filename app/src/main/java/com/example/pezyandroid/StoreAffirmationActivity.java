package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pezyandroid.databinding.ActivityStoreAffirmationBinding;

public class StoreAffirmationActivity extends AppCompatActivity {

    private ActivityStoreAffirmationBinding fBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_affirmation);

        fBind = DataBindingUtil.setContentView(StoreAffirmationActivity.this, R.layout.activity_store_affirmation);

        onConfirmClick();

    }

    private void onConfirmClick() {
        fBind.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoreAffirmationActivity.this, StoreAffirmationStepActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
