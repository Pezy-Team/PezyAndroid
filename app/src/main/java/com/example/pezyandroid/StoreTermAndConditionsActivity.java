package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;

import com.example.pezyandroid.databinding.ActivityStoreTermAndConditionsBinding;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.example.pezyandroid.louise.dialog.DialogEventListener;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

public class StoreTermAndConditionsActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreTermAndConditionsBinding fBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_term_and_conditions);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreTermAndConditionsActivity.this, R.layout.activity_store_term_and_conditions);

        onBackClick();

        onSubmit();
    }

    private void onSubmit() {
        fBind.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AlertDialogHelper.init(fContext).blockUI("กำลังส่งข้อมูล");
                    Thread.sleep(5000);
                    AlertDialogHelper.init(fContext).dialogOK("ระบบ", "ส่งข้อมูลการซื้อแพ็คเกจร้านค้าเรียบร้อย ทางทีมงานกำลังตรวจสอบข้อมูลของคุณ", false, new DialogEventListener() {
                        @Override
                        public void onPositiveClick(DialogInterface dialog, int which) {

                            SharePreferencesUtil.init("STORE_STEP_SUCCESS_STATUS", MODE_PRIVATE, fContext)
                                    .putBoolean("buy_package_success", true)
                                    .apply();
                            finish();
                        }

                        @Override
                        public void onNeutralClick(DialogInterface dialog, int which) {

                        }

                        @Override
                        public void onNegativeClick(DialogInterface dialog, int which) {

                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void onBackClick() {
        fBind.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
