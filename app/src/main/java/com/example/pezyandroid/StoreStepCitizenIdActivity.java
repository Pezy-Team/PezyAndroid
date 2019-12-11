package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pezyandroid.databinding.ActivityStoreStepCitizenIdBinding;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.example.pezyandroid.louise.dialog.DialogEventListener;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class StoreStepCitizenIdActivity extends AppCompatActivity {

    private Context fContext;

    private ActivityStoreStepCitizenIdBinding fBind;

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_step_citizen_id);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreStepCitizenIdActivity.this, R.layout.activity_store_step_citizen_id);

        onTakePhoto();

        onPressBack();

        onSubmit();

    }

    private void onSubmit() {
        fBind.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    SharePreferencesUtil.init("STORE_STEP_SUCCESS_STATUS", MODE_PRIVATE, fContext)
                            .putBoolean("step_citizen_id_success", true)
                            .apply();
                    finish();
                }
            }
        });
    }

    private boolean validate() {
        String idCard = fBind.txtIDCard.getText().toString();
        if(StringUtils.isBlank(idCard)){
            AlertDialogHelper.init(fContext).dialogOK("ตรวจสอบ", "โปรดกรอกเลขบัตรประชาชนให้สมบูรณ์", false, new DialogEventListener() {
                @Override
                public void onPositiveClick(DialogInterface dialog, int which) {
                    fBind.txtIDCard.requestFocus();
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

        if(idCard.length() != 13 || !StringUtils.isNumeric(idCard)){
            AlertDialogHelper.init(fContext).dialogOK("ตรวจสอบ", "โปรดกรอกเลขบัตรประชาชนให้ครบ 13 หลัก และเป็นตัวเลขเท่านั้น", false, new DialogEventListener() {
                @Override
                public void onPositiveClick(DialogInterface dialog, int which) {
                    fBind.txtIDCard.requestFocus();
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

    private void onPressBack() {
        fBind.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onTakePhoto() {
        fBind.wrapTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto(v);
            }
        });
    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        imageUri = FileProvider.getUriForFile(StoreStepCitizenIdActivity.this, BuildConfig.APPLICATION_ID + ".provider", photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri selectedImage = imageUri;
                        getContentResolver().notifyChange(selectedImage, null);
                        ContentResolver cr = getContentResolver();

                        File file = new File(imageUri.getPath());
                        InputStream in = new FileInputStream(file);
                        Bitmap bitmap;
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);


                        fBind.imgUpload.setVisibility(View.VISIBLE);
                        fBind.imgUpload.setImageBitmap(BitmapFactory.decodeStream(in));
                        fBind.wrapTakePhoto.setVisibility(View.GONE);
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }
                }
        }
    }

}
