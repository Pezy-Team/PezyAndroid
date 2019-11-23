package com.example.pezyandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pezyandroid.databinding.ActivityMainNavBinding;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainNavActivity extends AppCompatActivity {

    private EditText edt;
    private ActivityMainNavBinding fBind;
    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fContext = this;
        getSupportActionBar().hide();

        fBind = DataBindingUtil.setContentView(MainNavActivity.this, R.layout.activity_main_nav);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_main, R.id.navigation_chat, R.id.navigation_store, R.id.navigation_cart, R.id.navigation_category)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        onProfileClick();
    }

    private void onProfileClick() {
        fBind.incToolBar.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fContext, "Goto profile", Toast.LENGTH_LONG).show();
                String token = SharePreferencesUtil.init("USER", MODE_PRIVATE, fContext).getString("token", "nothing");
                Intent intent = new Intent(MainNavActivity.this, LoginActivity.class);
                if("nothing".equals(token)){
                    intent = new Intent(MainNavActivity.this, LoginActivity.class);
                }
                startActivity(intent);
            }
        });
    }

}
