package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.pezyandroid.databinding.ActivityStoreStepInviteBinding;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.example.pezyandroid.louise.dialog.DialogEventListener;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

import org.apache.commons.lang3.StringUtils;

public class StoreStepInviteActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreStepInviteBinding fBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_step_invite);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreStepInviteActivity.this, R.layout.activity_store_step_invite);

        onSubmit();

        onBackClick();
    }

    private void onBackClick() {
        fBind.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onSubmit() {
        fBind.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){

                    SharePreferencesUtil.init("STORE_STEP_SUCCESS_STATUS", MODE_PRIVATE, fContext)
                            .putBoolean("step_invite_success", true)
                            .apply();

                    finish();
                }
            }
        });
    }

    private boolean validate() {
        if(StringUtils.isBlank(fBind.txtInviteCode.getText().toString())){
            AlertDialogHelper.init(fContext).dialogOK("ตรวจสอบ", "โปรดกรอกรหัสแนะนำให้ครบถ้วน", false, new DialogEventListener() {
                @Override
                public void onPositiveClick(DialogInterface dialog, int which) {
                    fBind.txtInviteCode.requestFocus();
                }

                @Override
                public void onNeutralClick(DialogInterface dialog, int which) {

                }

                @Override
                public void onNegativeClick(DialogInterface dialog, int which) {

                }
            });
            return false;
        }
        return true;
    }
}
