package com.example.pezyandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.pezyandroid.entity.User;
import com.example.pezyandroid.helper.APIHelper;
import com.example.pezyandroid.louise.API.APICallBack;
import com.example.pezyandroid.louise.dialog.AlertDialogHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtNameSurname, txtTelephone, txtUsername, txtPassword,
        txtEmail, txtConfPwd;

    private CheckBox chkAccept;

    private Button btnFBLogin, btnRegister;

    private Context fContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fContext = this;

        bindObj();

        onRegClick();

    }

    private void onRegClick() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!validatePwd()){
                        Toast.makeText(fContext, getString(R.string.pls_conf_pwd), Toast.LENGTH_SHORT).show();
                    }else{
                        if(chkAccept.isChecked()){
                            JSONObject userJson = buildUserJson();
                            AlertDialogHelper.init(fContext).blockUI("กำลังดำเนินการ");
                            apiCreate(userJson, new APICallBack() {
                                @Override
                                public void onSuccess(JSONObject response) {
                                    AlertDialogHelper.init(fContext).dialogDismiss();
                                    Toast.makeText(fContext, "ยินดีต้อนรับ", Toast.LENGTH_LONG).show();
                                    finish();
                                }

                                @Override
                                public void onSuccess(JSONArray response) {

                                }

                                @Override
                                public void onError(VolleyError error) {
                                    if(null == error){
                                        Log.d(getString(R.string.log_debug), "Error was null");
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(fContext, getString(R.string.pls_accept), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void apiCreate(JSONObject userJson, APICallBack callback) {

        APIHelper.init(fContext).userCreate(userJson,callback);

    }

    private JSONObject buildUserJson() throws JsonProcessingException, JSONException {
        User user = buildUserObj();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(user);
        Log.d(getString(R.string.log_info), str);
        return new JSONObject(str);
    }

    private boolean validatePwd() {
        String pwd = txtPassword.getText().toString();
        String pwd2 = txtConfPwd.getText().toString();
        if (StringUtils.isNotBlank(pwd) &&
                StringUtils.isNotBlank(pwd2)) {
            if(pwd.equals(pwd2)){
                return true;
            }
        }
        return false;
    }

    private User buildUserObj() {
        User user = new User();
        user.setName(txtNameSurname.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        String tel = StringUtils.remove(txtTelephone.getText().toString(), "-");
        user.setTel(tel);
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtConfPwd.getText().toString());
        return user;
    }

    private void bindObj() {
        chkAccept = findViewById(R.id.chkAccept);
        txtNameSurname = findViewById(R.id.txtNameSurname);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtEmail = findViewById(R.id.txtEmail);
        txtConfPwd = findViewById(R.id.txtConfPwd);
        btnFBLogin = findViewById(R.id.btnFBLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }
}
