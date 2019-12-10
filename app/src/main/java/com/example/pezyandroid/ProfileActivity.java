package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.example.pezyandroid.databinding.ActivityProfileBinding;
import com.example.pezyandroid.entity.User;
import com.example.pezyandroid.helper.APIHelper;
import com.example.pezyandroid.helper.UserUtils;
import com.example.pezyandroid.louise.API.APICallBack;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding fBind;
    private String token;
    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fContext = this;

        fBind = DataBindingUtil.setContentView(ProfileActivity.this, R.layout.activity_profile);

        token = UserUtils.init(fContext).getToken("nothing");
        if("nothing".equals(token)){
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            getUserProfile(token);
        }

        onPressBack();

        onActivateStore();

    }

    private void onActivateStore() {
        fBind.btnActivateStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, StoreSelectPackageActivity.class);
                startActivity(intent);
            }
        });
    }

    private void onPressBack() {
        fBind.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getUserProfile(String mToken) {
        APIHelper.init(fContext).getUserProfile(mToken, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) {
                fBind.loading.setVisibility(View.GONE);
                try {
                    Log.wtf(getString(R.string.log_info), response.toString());
                    ObjectMapper map = new ObjectMapper();
                    renderUser(response);
                } catch (JSONException e) {
                    Log.wtf(getString(R.string.log_error), e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(JSONArray response) {

            }

            @Override
            public void onError(VolleyError error) {
                Log.d(getString(R.string.log_debug), "This is error message : " + error.getMessage());
            }

            @Override
            public void onError(VolleyError error, String jsonString, int statusCode) {
                Log.d(getString(R.string.log_debug), String.format("Status code : %s, Message : %s", statusCode, error.getMessage()));
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void renderUser(JSONObject mUser) throws JSONException {
        fBind.txtNameTitle.setText(mUser.getString("name"));
        fBind.txtNameSurname.setText(mUser.getString("name"));
        fBind.txtTelephone.setText(mUser.getString("tel"));
        fBind.txtEmail.setText(mUser.getString("email"));
    }
}
