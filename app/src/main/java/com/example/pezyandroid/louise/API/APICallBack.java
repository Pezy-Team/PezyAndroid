package com.example.pezyandroid.louise.API;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by anubi on 14/12/2560.
 */

public interface APICallBack {
    public void onSuccess(JSONObject response);

    public void onSuccess(JSONArray response);

    public void onError(VolleyError error);

    public void onError(VolleyError error, String jsonString, int statusCode);
}
