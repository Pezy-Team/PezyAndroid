package com.example.pezyandroid.louise.clipboard;

import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

public class ClipboardHelper {
    private static final ClipboardHelper ourInstance = new ClipboardHelper();
    private static Context fContext;
    private static ClipboardManager clipboard;

    public static ClipboardHelper init(Context context) {
        fContext = context;
        clipboard = (ClipboardManager) fContext.getSystemService(Context.CLIPBOARD_SERVICE);
        return ourInstance;
    }

    private ClipboardHelper() {
    }

    @SuppressWarnings("deprecation")
    public String getClipboardData(){
        if(clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
            return clipboard.getText().toString();
        }else{
            return "Nothing";
        }
    }

    @SuppressWarnings("deprecation")
    public ClipboardHelper setClipboardData(String data){
        clipboard.setText(data);
        return ourInstance;
    }
}
