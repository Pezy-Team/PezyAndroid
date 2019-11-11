package com.example.pezyandroid.louise.file;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anubi on 01/12/2560.
 */

public class File {
    private static final File ourInstance = new File();

    private static Context fContext;
    private static String fFile;
    private static String type;

    public static File asset(Context context, String file) {
        fContext = context;
        fFile = file;
        type = "asset";
        return ourInstance;
    }


    public static String read(){
        Log.wtf("MYAPP", "read()");
        String json = null;
        try {
            InputStream is = fContext.getAssets().open(fFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
