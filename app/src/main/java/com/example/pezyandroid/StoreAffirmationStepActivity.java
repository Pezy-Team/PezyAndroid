package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.pezyandroid.databinding.ActivityStoreAffirmationStepBinding;

public class StoreAffirmationStepActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreAffirmationStepBinding fBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_affirmation_step);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreAffirmationStepActivity.this, R.layout.activity_store_affirmation_step);

        onInviteCodeClick();

        onCitizenIdClick();

        onPersonalInfoClick();

        onNextStepClick();
    }

    private void onInviteCodeClick() {
        fBind.btnInviteCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onCitizenIdClick() {
        fBind.btnCitizenID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onPersonalInfoClick() {
        fBind.btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onNextStepClick() {
        fBind.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
