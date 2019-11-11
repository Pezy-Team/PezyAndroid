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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pezyandroid.R;
import com.example.pezyandroid.louise.API.APICallBack;
import com.example.pezyandroid.louise.API.APICallPathModel;
import com.example.pezyandroid.louise.API.APIContextPathModel;
import com.example.pezyandroid.louise.volley.InitVolley;

import org.json.JSONArray;
import org.json.JSONException;
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
    public void auth(JSONObject jsonRequest, final APICallBack callBack){
        Log.wtf("MYAPP", "auth()");
        String url = APIContextPathModel.getDomainName() + APICallPathModel.AUTH;
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP", "Request : " + jsonRequest.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf("MYAPP getDeliverOrder", "success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    /**
     * Find case
     * @param jsonRequest
     * @param callBack
     */
    public void caseFindJoin(JSONObject jsonRequest, final APICallBack callBack){
        Log.wtf("MYAPP", "caseFindJoin()");
        String url = APIContextPathModel.getDomainName() + APICallPathModel.CASE_FIND_JOIN;
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP", "Request : " + jsonRequest.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf("MYAPP getDeliverOrder", "success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void saveCaseNote(String note, String caseId, final APICallBack callBack){
        try {
            Log.wtf("MYAPP", "auth()");
            String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.API_CASE, Integer.valueOf(caseId));
            Log.wtf("MYAPP url", url);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("comment", note);

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.PATCH,
                    url,
                    jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                            callBack.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                            callBack.onError(error);
                        }
                    }
            ){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    //return super.getHeaders();
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("page-code", "none");
                    params.put("Authorization", User.init(fContext).getToken("none"));
                    return params;
                }

            };
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getCaseByIdWithPrepSeqPlace(int caseId, final APICallBack callBack){
        Log.wtf(fContext.getString(R.string.log_debug), "getCaseByIdWithPrepSeqPlace()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.FIND_CASE_WITH_PREP_SEQ_PLACE, caseId);
        Log.wtf(fContext.getString(R.string.log_info), url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if(error.networkResponse != null){
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error);
                            }else{
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
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void getCaseById(String caseId, final APICallBack callBack){
        Log.wtf("MYAPP", "auth()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.API_CASE, Integer.valueOf(caseId));
        Log.wtf("MYAPP url", url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void getLatLngFromTrackingDevice(String trackingId, final APICallBack callBack){
        Log.wtf("MYAPP", "auth()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.TRACKING_DEVICE, Integer.valueOf(trackingId));
        Log.wtf("MYAPP url", url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void patchEstimatePrice(long assetID, String price, final APICallBack callBack){
        Log.wtf("MYAPP", "patchEstimatePrice()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.PATCH_ESTIMATE_PRICE, assetID,price);
        Log.wtf("MYAPP url", url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.PATCH,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void checkTrackStatus(JSONObject jsonReq, final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.POST_CHECK_TRACKING_DEVICE);
        Log.wtf("MYAPP url", url);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonReq,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void checkTrackStatusMultipleItems(JSONArray jsonReq, final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.POST_CHECK_TRACKING_DEVICE_MULTIPLE);
        Log.wtf("MYAPP url", url);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.POST,
                url,
                jsonReq,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                        callBack.onError(error);
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //return super.getHeaders();
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }

        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void bindingTrackingDevice(JSONArray jsonReq, final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.BINDING_TRACKING_DEVICE);
        Log.wtf("MYAPP url", url);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Content-Type", "application/json");
        params.put("page-code", "none");
        params.put("Authorization", User.init(fContext).getToken("none"));

        CustomJsonArrayRequest req = new CustomJsonArrayRequest(
                params,
                Request.Method.POST,
                url,
                jsonReq,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callBack.onError(error);
                    }
                });
        req.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(req,fContext);
    }

    public void removeTrackingDevice(String trackId, final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.REMOVE_TRACKING_DEVICE, trackId);
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP json request ", trackId);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.DELETE,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if(error.networkResponse != null){
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error);
                            }else{
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", "network response was null", error.getMessage()));
                                callBack.onError(null);
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void makeSequestration(String caseId, JSONObject req, final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.MAKE_SEQUESTRATION, caseId);
        Log.wtf("MYAPP url", url);
        Log.wtf("MYAPP json request ", caseId);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                req,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if(error.networkResponse != null){
                                byte[] data = error.networkResponse.data;
                                String jsonString = new String(data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s", jsonString));
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", error.networkResponse.statusCode, error.getMessage()));
                                callBack.onError(error);
                            }else{
                                Log.wtf(fContext.getString(R.string.log_info), String.format("Response Error : %s, Message : %s ", "network response was null", error.getMessage()));
                                callBack.onError(null);
                            }

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }

    public void getTrackingDeviceAvailable(final APICallBack callBack){
        Log.wtf("MYAPP", "checkTrackStatus()");
        String url = String.format(APIContextPathModel.getDomainNameHibernate() + APICallPathModel.TRACKING_DEVICE_AVAILABLE);
        Log.wtf("MYAPP url", url);

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.wtf(fContext.getString(R.string.log_info), "Response Success : " + response.toString());
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            if (error.networkResponse != null) {
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
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                params.put("page-code", "none");
                params.put("Authorization", User.init(fContext).getToken("none"));
                return params;
            }
        };

        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        InitVolley.getsInstance().addToQueue(jsonObjReq,fContext);
    }


}
