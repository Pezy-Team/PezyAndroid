package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.pezyandroid.databinding.ActivityStoreSelectPackageBinding;

public class StoreSelectPackageActivity extends AppCompatActivity {

    private ActivityStoreSelectPackageBinding fBind;
    private Context fContext;

    private String packageName = "Small";
    private Integer packageDuration = 3;

    private Drawable btnMonthDefault, btnMonthSelected;
    private int colorOrange, colorWhite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_select_package);

        fContext = this;

        fBind = DataBindingUtil.setContentView(StoreSelectPackageActivity.this, R.layout.activity_store_select_package);

        setColor();
        setDrawableButtonDuration();

        onSelectPackage();
        onSelectPackSmallDuration();
        onSelectPackMediumDuration();
        onSelectPackRegularDuration();

        onBuyPackage();

    }

    private void setColor() {
        colorOrange = getResources().getColor(R.color.pezy_orange);
        colorWhite = getResources().getColor(R.color.text_white);
    }

    private void onBuyPackage() {
        fBind.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void onSelectPackMediumDuration() {
        fBind.btnMedium3M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 3;
                fBind.btnMedium3M.setTextColor(colorWhite);
                fBind.btnMedium3M.setBackground(btnMonthSelected);

                fBind.btnMedium6M.setTextColor(colorOrange);
                fBind.btnMedium6M.setBackground(btnMonthDefault);

                fBind.btnMedium12M.setTextColor(colorOrange);
                fBind.btnMedium12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnMedium6M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 6;
                fBind.btnMedium3M.setTextColor(colorOrange);
                fBind.btnMedium3M.setBackground(btnMonthDefault);

                fBind.btnMedium6M.setTextColor(colorWhite);
                fBind.btnMedium6M.setBackground(btnMonthSelected);

                fBind.btnMedium12M.setTextColor(colorOrange);
                fBind.btnMedium12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnMedium12M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 12;
                fBind.btnMedium3M.setTextColor(colorOrange);
                fBind.btnMedium3M.setBackground(btnMonthDefault);

                fBind.btnMedium6M.setTextColor(colorOrange);
                fBind.btnMedium6M.setBackground(btnMonthDefault);

                fBind.btnMedium12M.setTextColor(colorWhite);
                fBind.btnMedium12M.setBackground(btnMonthSelected);
            }
        });
    }

    private void onSelectPackSmallDuration() {
        fBind.btnSmall3M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 3;
                fBind.btnSmall3M.setTextColor(colorWhite);
                fBind.btnSmall3M.setBackground(btnMonthSelected);

                fBind.btnSmall6M.setTextColor(colorOrange);
                fBind.btnSmall6M.setBackground(btnMonthDefault);

                fBind.btnSmall12M.setTextColor(colorOrange);
                fBind.btnSmall12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnSmall6M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 6;
                fBind.btnSmall3M.setTextColor(colorOrange);
                fBind.btnSmall3M.setBackground(btnMonthDefault);

                fBind.btnSmall6M.setTextColor(colorWhite);
                fBind.btnSmall6M.setBackground(btnMonthSelected);

                fBind.btnSmall12M.setTextColor(colorOrange);
                fBind.btnSmall12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnSmall12M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 12;
                fBind.btnSmall3M.setTextColor(colorOrange);
                fBind.btnSmall3M.setBackground(btnMonthDefault);

                fBind.btnSmall6M.setTextColor(colorOrange);
                fBind.btnSmall6M.setBackground(btnMonthDefault);

                fBind.btnSmall12M.setTextColor(colorWhite);
                fBind.btnSmall12M.setBackground(btnMonthSelected);
            }
        });
    }

    private void onSelectPackRegularDuration() {
        fBind.btnRegular3M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 3;
                fBind.btnRegular3M.setTextColor(colorWhite);
                fBind.btnRegular3M.setBackground(btnMonthSelected);

                fBind.btnRegular6M.setTextColor(colorOrange);
                fBind.btnRegular6M.setBackground(btnMonthDefault);

                fBind.btnRegular12M.setTextColor(colorOrange);
                fBind.btnRegular12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnRegular6M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 6;
                fBind.btnRegular3M.setTextColor(colorOrange);
                fBind.btnRegular3M.setBackground(btnMonthDefault);

                fBind.btnRegular6M.setTextColor(colorWhite);
                fBind.btnRegular6M.setBackground(btnMonthSelected);

                fBind.btnRegular12M.setTextColor(colorOrange);
                fBind.btnRegular12M.setBackground(btnMonthDefault);
            }
        });

        fBind.btnRegular12M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                packageDuration = 12;
                fBind.btnRegular3M.setTextColor(colorOrange);
                fBind.btnRegular3M.setBackground(btnMonthDefault);

                fBind.btnRegular6M.setTextColor(colorOrange);
                fBind.btnRegular6M.setBackground(btnMonthDefault);

                fBind.btnRegular12M.setTextColor(colorWhite);
                fBind.btnRegular12M.setBackground(btnMonthSelected);
            }
        });
    }

    private void setDrawableButtonDuration() {
        btnMonthDefault = fContext.getDrawable(R.drawable.bg_package_month_default);
        btnMonthSelected = fContext.getDrawable(R.drawable.bg_package_month_selected);
    }


    private void onSelectPackage() {
        fBind.radioRegular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRadio("Regular");
            }
        });

        fBind.radioMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRadio("Medium");
            }
        });

        fBind.radioSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetRadio("Small");
            }
        });
    }

    private void resetRadio(String pack) {
        fBind.radioMedium.setImageResource(R.drawable.radio_check_default);
        fBind.radioRegular.setImageResource(R.drawable.radio_check_default);
        fBind.radioRegular.setImageResource(R.drawable.radio_check_default);

        if("Small".equals(pack)){
            fBind.radioSmall.setImageResource(R.drawable.radio_check_selected);
            packageName = "Small";
        } else if("Medium".equals(pack)){
            fBind.radioMedium.setImageResource(R.drawable.radio_check_selected);
            packageName = "Medium";
        } else if("Regular".equals(pack)){
            fBind.radioRegular.setImageResource(R.drawable.radio_check_selected);
            packageName = "Regular";
        }
    }
}
