package com.example.pezyandroid.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.example.pezyandroid.louise.sharepreferences.SharePreferencesUtil;

/**
 * Created by anubi on 11/12/2560.
 */

public class User {

    private final String PREF_NAME = "UID";
    private final String PREF_TOKEN_NAME = "token";
    private Integer USER_TIME_OUT = 180000;

    private static final User ourInstance = new User();
    public static Context fContext;
    private Handler fHandler = null;
    private Runnable fRun = null;

    public static User init(Context context) {
        fContext = context;
        return ourInstance;
    }

    /**
     * Is user logged in?
     */
    public boolean isLogin(){
        String token = getToken("nothing");
        if("nothing".equals(token)){
            return false;
        }else{
            return true;
        }
    }

    public void ifNotLoginThenAuth(){
        if(!isLogin()){
            logout();
        }
    }

    public String getToken(String returnMeIfTheValIsNotAvailable){
        return SharePreferencesUtil.init(PREF_NAME, Context.MODE_PRIVATE, fContext)
                .getString(PREF_TOKEN_NAME, returnMeIfTheValIsNotAvailable);
    }


    /**
     * Store user's token.
     */
    public boolean store(String token){
        return SharePreferencesUtil.init(PREF_NAME, Context.MODE_PRIVATE, fContext)
                .putString(PREF_TOKEN_NAME, token)
                .commit();
    }


    public void logout(){
        Log.wtf("MYAPP", "Logout in " + USER_TIME_OUT);
        SharePreferencesUtil.init(PREF_NAME, Context.MODE_PRIVATE, fContext).remove(PREF_TOKEN_NAME).commit();
        /*UserDatabaseHelper user = new UserDatabaseHelper(fContext);
        Intent intent;
        if(user.isHasFirstLoginFalse()){
            intent = new Intent(fContext, PinActivity.class);
        }else{
            intent = new Intent(fContext, AuthActivity.class);
        }
        fContext.startActivity(intent);
        ((Activity) fContext).finish();*/
    }

    public void setUserTimeOut(){ setUserTimeOut(USER_TIME_OUT); }
    public void setUserTimeOut(int timeOut){
        /**
         * Set user timeout
         */
        if(isLogin()){
            if(fHandler != null){
                fHandler.removeCallbacks(fRun);
            }
            USER_TIME_OUT = timeOut;
            fHandler = new Handler();
            fRun = new LogOut();
            fHandler.postDelayed(fRun, USER_TIME_OUT);
        }
    }

    class LogOut implements Runnable{
        @Override
        public void run() {
            logout();
        }
    }
}