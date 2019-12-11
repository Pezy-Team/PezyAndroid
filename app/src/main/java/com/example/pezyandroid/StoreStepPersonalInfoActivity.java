package com.example.pezyandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.pezyandroid.databinding.ActivityStoreStepPersonalInfoBinding;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.example.pezyandroid.louise.dialog.DialogEventListener;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

import org.apache.commons.lang3.StringUtils;

public class StoreStepPersonalInfoActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreStepPersonalInfoBinding fBind;

    /**
     * Image
     */
    private Bitmap currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_step_personal_info);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreStepPersonalInfoActivity.this, R.layout.activity_store_step_personal_info);

        loadSpinnerGender();

        onUploadImg();

        onSubmit();

        onPressBack();
    }

    private void onPressBack() {
        fBind.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onSubmit() {

        fBind.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    SharePreferencesUtil.init("STORE_STEP_SUCCESS_STATUS", MODE_PRIVATE, fContext)
                        .putBoolean("step_personal_success", true)
                        .apply();
                    finish();
                }
            }
        });
    }

    private boolean validateForm() {
        if(StringUtils.isBlank(fBind.txtName.getText().toString())){
            AlertDialogHelper.init(fContext).dialogOK("ตรวจสอบ", "โปรดกรอก ชื่อ - นามสกุล ให้สมบูรณ์", false, new DialogEventListener() {
                @Override
                public void onPositiveClick(DialogInterface dialog, int which) {
                    fBind.txtName.requestFocus();
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

        if(StringUtils.isBlank(fBind.txtBirthday.getText().toString())){
            AlertDialogHelper.init(fContext).dialogOK("ตรวจสอบ", "โปรดกรอกวันเกิดให้สมบูรณ์", false, new DialogEventListener() {
                @Override
                public void onPositiveClick(DialogInterface dialog, int which) {
                    fBind.txtBirthday.requestFocus();
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

    private void loadSpinnerGender() {
        String[] genders = fContext.getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(fContext,R.layout.support_simple_spinner_dropdown_item, genders);
        fBind.spnGender.setAdapter(adapter);
    }

    private void onUploadImg() {
        fBind.imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri photoUri = data.getData();
            if (photoUri != null) {
                try {
                    currentImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                    fBind.imgUpload.setImageBitmap(currentImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
