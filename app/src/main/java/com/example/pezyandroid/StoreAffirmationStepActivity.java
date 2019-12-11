package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pezyandroid.databinding.ActivityStoreAffirmationStepBinding;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

public class StoreAffirmationStepActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreAffirmationStepBinding fBind;

    private boolean isPersonalSuccess = false, isInviteCodeSuccess = false, isCitizenIdSuccess = false;

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

    @Override
    protected void onResume() {
        super.onResume();

        SharePreferencesUtil pref = SharePreferencesUtil.init("STORE_STEP_SUCCESS_STATUS", MODE_PRIVATE, fContext);

        isPersonalSuccess = pref.getBoolean("step_personal_success", false);
        isCitizenIdSuccess = pref.getBoolean("step_citizen_id_success", false);
        isInviteCodeSuccess = pref.getBoolean("step_invite_success", false);

        if(isPersonalSuccess){
            makeStepComplete(fBind.btnPersonalInfo);
        }

        if(isCitizenIdSuccess){
            makeStepComplete(fBind.btnCitizenID);
        }

        if(isInviteCodeSuccess){
            makeStepComplete(fBind.btnInviteCode);
        }
    }

    private void makeStepComplete(Button button){
        button.setTextColor(fContext.getResources().getColor(R.color.text_white));
        button.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.step_check), null);
        button.setBackgroundColor(fContext.getResources().getColor(R.color.pezy_orange));
    }

    private void onInviteCodeClick() {
        fBind.btnInviteCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeStepComplete(fBind.btnInviteCode);
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
                if(!isPersonalSuccess){
                    intentTo(StoreStepPersonalInfoActivity.class);
                }
            }
        });
    }

    private void intentTo(Class<?> destination) {
        Intent intent = new Intent(StoreAffirmationStepActivity.this, destination);
        startActivity(intent);
    }

    private void onNextStepClick() {
        fBind.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
