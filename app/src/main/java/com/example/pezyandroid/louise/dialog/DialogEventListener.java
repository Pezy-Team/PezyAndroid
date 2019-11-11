package com.example.pezyandroid.louise.dialog;

import android.content.DialogInterface;

/**
 * Created by anubi on 7/28/2018.
 */

public interface DialogEventListener {
    public void onPositiveClick(DialogInterface dialog, int which);
    public void onNeutralClick(DialogInterface dialog, int which);
    public void onNegativeClick(DialogInterface dialog, int which);
}
