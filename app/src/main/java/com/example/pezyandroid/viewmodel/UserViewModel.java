package com.example.pezyandroid.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.VolleyError;
import com.example.pezyandroid.entity.User;
import com.example.pezyandroid.helper.APIHelper;
import com.example.pezyandroid.louise.API.APICallBack;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
    private Context fContext;

    public UserViewModel(Context context){
        fContext = context;
    }

    private void authByUsername(JSONObject jsonReq) {

        APIHelper.init(fContext).authByUsername(jsonReq, new APICallBack() {
            @Override
            public void onSuccess(JSONObject response) {

            }

            @Override
            public void onSuccess(JSONArray response) {

            }

            @Override
            public void onError(VolleyError error) {

            }

            @Override
            public void onError(VolleyError error, String jsonString, int statusCode) {

            }
        });

    }
}
