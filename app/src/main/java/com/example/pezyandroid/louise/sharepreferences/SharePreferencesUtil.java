package com.example.pezyandroid.louise.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by anubi on 07/09/2560.
 */

public class SharePreferencesUtil {

    /**
     * PROPERTIES AREA.
     */
    private static String PREF_NAMES;
    private static int PREF_MODE;
    private static SharePreferencesUtil instance;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static Context appContext;

    /**
     * CONSTRUCTOR
     */
    private SharePreferencesUtil(){
        /**
         * Make sharepreferences.
         */

        sp = appContext.getSharedPreferences(PREF_NAMES, PREF_MODE);
    }

    /**
     * Get instance objects.
     * @return
     */
    public static SharePreferencesUtil init(String pref_name, int mode, Context context){
        PREF_MODE = mode;
        if(pref_name != null){
            PREF_NAMES = pref_name;
        }
        if(context != null){
            appContext = context;
        }
        if(instance == null){
            instance = new SharePreferencesUtil();
        }

        return instance;
    }


    /**
     * Sharepreferences commands.
     */
    public boolean commit(){
        if(editor != null){
            return editor.commit();
        }
        return false;
    }

    public void apply(){
        if(editor != null){
            editor.apply();
        }
    }

    /**
     * Set editor.
     */
    protected void setEditor(){
        if(sp != null && editor == null){
            editor = sp.edit();
        }
    }

    /**
     * Remove & Clear
     */
    public SharePreferencesUtil clearAll(){
        setEditor();
        if(editor != null){
            editor.clear().commit();
        }
        return instance;
    }

    public SharePreferencesUtil remove(String key){
        setEditor();
        if(editor != null){
            editor.remove(key);
        }
        return instance;
    }

    /**
     * Store preference
     */
    public SharePreferencesUtil putBoolean(String key, boolean value){
        setEditor();
        editor.putBoolean(key, value);
        return instance;
    }

    public SharePreferencesUtil putInt(String key, int value){
        setEditor();
        editor.putInt(key, value);
        return instance;
    }

    public SharePreferencesUtil putFloat(String key, float value){
        setEditor();
        editor.putFloat(key, value);
        return instance;
    }

    public SharePreferencesUtil putLong(String key, long value){
        setEditor();
        editor.putLong(key, value);
        return instance;
    }

    public SharePreferencesUtil putString(String key, String value){
        setEditor();
        editor.putString(key, value);
        return instance;
    }

    public SharePreferencesUtil putStringSet(String key, Set<String> value){
        setEditor();
        editor.putStringSet(key, value);
        return instance;
    }

    /**
     * Retrieve preference.
     */
    public boolean getBoolean(String key, boolean expectedIfDoesNotExist){
        return sp.getBoolean(key, expectedIfDoesNotExist);
    }

    public int getInt(String key, int expectedIfDoesNotExist){
        return sp.getInt(key, expectedIfDoesNotExist);
    }

    public float getFloat(String key, float expectedIfDoesNotExist){
        return sp.getFloat(key, expectedIfDoesNotExist);
    }

    public long getLong(String key, long expectedIfDoesNotExist){
        return sp.getLong(key, expectedIfDoesNotExist);
    }

    public String getString(String key, String expectedIfDoesNotExist){
        return sp.getString(key, expectedIfDoesNotExist);
    }

    public Set<String> getStringSet(String key, Set<String>expectedIfDoesNotExist){
        return sp.getStringSet(key, expectedIfDoesNotExist);
    }
}
