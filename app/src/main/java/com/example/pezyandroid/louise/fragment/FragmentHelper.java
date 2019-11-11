package com.example.pezyandroid.louise.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHelper {

    private static final FragmentHelper ourInstance = new FragmentHelper();

    private static Context context;

    public static FragmentHelper init(Context ct) {
        context = ct;
        return ourInstance;
    }

    public int setFragment(int frameLayoutId, Fragment fragment){
        FragmentTransaction fmTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fmTransaction.replace(frameLayoutId, fragment);
        return fmTransaction.commit();
    }
}
