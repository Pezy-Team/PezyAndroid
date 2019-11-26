package com.example.pezyandroid.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pezyandroid.R;
import com.example.pezyandroid.louise.API.APICallBack;
import com.example.pezyandroid.louise.API.APICallPathModel;
import com.example.pezyandroid.louise.API.APIContextPathModel;
import com.example.pezyandroid.louise.volley.InitVolley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by anubi on 02/12/2560.
 */
public class APIHelper extends AppCompatActivity {
    private static final APIHelper ourInstance = new APIHelper();

    private static Context fContext;
    private AlertDialog fDialog;

    public static APIHelper init(Context context) {
        fContext = context;
        return ourInstance;
    }

    public APIHelper() {

    }

    /**
     * Auth
     */
    public void authByUsername(JSONObject jsonRequest, final APICallBack callBack){
        Log.wtf("MYAPP", "authByUsername()");
        String url = APIContextPathModel.getDomainname() + APICallPathModel.USER_AUTH_BY_USERNAME;
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP", "Request : " + jsonRequest.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (null != error.networkResponse) {
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error, jsonString, error.networkResponse.statusCode);
                            } else {
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", "network response was null", error.getMessage()));
                                callBack.onError(null);
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void userCreate(JSONObject jsonRequest, final APICallBack callBack){
        Log.wtf("MYAPP", "userCreate()");
        String url = APIContextPathModel.getDomainname() + APICallPathModel.USER;
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP", "Request : " + jsonRequest.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (null != error.networkResponse) {
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error);
                            } else {
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", "network response was null", error.getMessage()));
                                callBack.onError(null);
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void getUserProfile(String token, final APICallBack callBack){
        Log.wtf("MYAPP", "userCreate()");
        String url = APIContextPathModel.getDomainname() + APICallPathModel.USER_GETPROFILE + "?token=" + token;
        Log.wtf("MYAPP url", url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (null != error.networkResponse) {
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error);
                            } else {
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", "network response was null", error.getMessage()));
                                callBack.onError(null);
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

}
