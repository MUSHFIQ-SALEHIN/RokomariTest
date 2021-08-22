package com.example.rokomaritest.model.utils;

import android.app.Activity;
import android.content.Context;

import com.example.rokomaritest.R;

import java.util.Random;


public class ShareInfo {

    private static final ShareInfo ourInstance = new ShareInfo();

    public static ShareInfo getInstance() {
        return ourInstance;
    }

    private ShareInfo() {
    }

    static {
        System.loadLibrary("native-lib");
    }

    public native String getBaseUrl();

    public static void goNextPage(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    public static void goPreviousPage(Context context) {
        ((Activity) context).overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back);
    }



    public String getAuthenticationToken(Context context){
        String auth_token = LocalStorage.getInstance().getStringData(context, "auth_token");
        auth_token = auth_token==null ? "" : auth_token;
        return auth_token;
    }

    public void setAuthenticationToken(Context context, String authToken){
        LocalStorage.getInstance().setData(context,"auth_token", authToken);
    }

    public String getRequestId() {
        Random rand = new Random();
        return String.valueOf(System.currentTimeMillis() + "" + rand.nextInt(100000));
    }

    /*public void logout(Context context) {
        LocalStorage.getInstance().clearData(context, "auth_token");
        // todo: GO TO LOGIN SCREEN.
        ((BaseActivity) context).goToLoginScreen();
    }*/


}
