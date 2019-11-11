package com.example.pezyandroid.louise.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by anubi on 04/09/2560.
 */

public class InitVolley {

    private RequestQueue queue;
    private static InitVolley sInstance = new InitVolley();
    private static final String TAG = InitVolley.class.getSimpleName();
    private static Context context;

    public InitVolley(){
        Log.d("VOLLEY", "Constructor");
        sInstance = this;
    }

    public <T> void addToQueue(Request<T> req, Context contxt){
        Log.d("VOLLEY", "Before!");
        if(req == null){
            Log.e("VOLLEY", "req was null!");
        }else{
            Log.d("VOLLEY", "req wasn't null!");
            req.setTag(TAG);
            getQueue(contxt).add(req);
        }
    }

    public void cancelPendingRequest(Object tag){
        if(queue != null){
            queue.cancelAll(tag);
        }
    }

    public RequestQueue getQueue(Context contxt){
        context = contxt;
        if(queue == null){
            queue = Volley.newRequestQueue(contxt);
        }
        return queue;
    }

    /**
     * GETTER & SETTER
     */
    public static synchronized InitVolley getsInstance() {
        return sInstance;
    }






}
