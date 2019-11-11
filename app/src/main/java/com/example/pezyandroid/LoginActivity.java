package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.pezyandroid.entity.User;
import com.example.pezyandroid.helper.APIHelper;
import com.example.pezyandroid.louise.API.APICallBack;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {


    private EditText txtUsername, txtPassword;
    private Button btnLogin, btnFBLogin, btnRegister;
    private TextView txtForgotPwd;
    private Context fContext;

    private ScrollView scrlFrm;


    /**
     * TODO : Handles all of Exception.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fContext = this;

        bindObj();

        onRegisterClick();

        onLoginClick();

        onForgotPwdClick();
    }

    private void onLoginClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final User user = buildUserObj();
                    if(validateForm(user)){
                        AlertDialogHelper.init(fContext).blockUI("กำลังดำเนินการ");
                        JSONObject userJson = buildUserJson(user);
                        apiLogin(userJson, new APICallBack() {
                            @Override
                            public void onSuccess(JSONObject response) {
                                try {
                                    if(response.has("token")){
                                        SharePreferencesUtil.init("USER", MODE_PRIVATE, fContext).putString("token", response.getString("token"));
                                        Intent intent = new Intent(fContext, MainActivity.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Log.d(getString(R.string.log_debug), response.getString("message"));
                                        Toast.makeText(fContext, response.getString("message"), Toast.LENGTH_LONG).show();
                                    }
                                    AlertDialogHelper.init(fContext).dialogDismiss();
                                } catch (JSONException e) {

                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onSuccess(JSONArray response) {

                            }

                            @Override
                            public void onError(VolleyError error) {
                                Log.e(getString(R.string.log_error), error.toString());
                            }

                            @Override
                            public void onError(VolleyError error, String jsonString, int statusCode) {
                                Log.e(getString(R.string.log_error), String.format("Status code : %s, Response : %s ", statusCode, jsonString));
                                if(401 == statusCode){
                                    AlertDialogHelper.init(fContext).dialogDismiss();
                                    try {
                                        JSONObject json = new JSONObject(jsonString);
                                        Toast.makeText(fContext, json.getString("message"), Toast.LENGTH_LONG).show();
                                        resetForm();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        });
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void resetForm() {
        txtUsername.setText("");
        txtUsername.requestFocus();
        txtPassword.setText("");
    }

    private void apiLogin(JSONObject userJson, APICallBack callback) {
        APIHelper.init(fContext).authByUsername(userJson, callback);
    }

    private JSONObject buildUserJson(User user) throws JsonProcessingException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        return new JSONObject(str);
    }

    private User buildUserObj() {
        return new User(txtUsername.getText().toString(), txtPassword.getText().toString());
    }

    private boolean validateForm(User user) {
        if(StringUtils.isBlank(user.getUsername())){
            Toast.makeText(fContext, getString(R.string.require_username), Toast.LENGTH_LONG).show();
            return false;
        }

        if(StringUtils.isBlank(user.getPassword())){
            Toast.makeText(fContext, getString(R.string.require_password), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void onForgotPwdClick() {
        txtForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, ForgotPasswordStep1Activity.class);
                startActivity(intent);
            }
        });
    }

    private void onRegisterClick() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindObj() {
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnFBLogin = findViewById(R.id.btnFBLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtForgotPwd = findViewById(R.id.txtForgotPwd);
        scrlFrm = findViewById(R.id.scrlForm);
    }
}
