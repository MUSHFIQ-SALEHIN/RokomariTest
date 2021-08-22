package com.example.rokomaritest.model.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private static final LocalStorage ourInstance = new LocalStorage();
    public static LocalStorage getInstance() {
        return ourInstance;
    }
    private LocalStorage() { }


    private SharedPreferences getBaseConfig(Context context){
        return context.getSharedPreferences("rokomari_test", Context.MODE_PRIVATE);
    }

    public void setData(Context context, String key, String data){
        getBaseConfig(context).edit().putString(key,data).apply();
    }

    public void setData(Context context, String key, int data){
        getBaseConfig(context).edit().putInt(key,data).apply();
    }

    public void setData(Context context, String key, float data){
        getBaseConfig(context).edit().putFloat(key,data).apply();
    }

    public String getStringData(Context context, String key){
        return getBaseConfig(context).getString(key,"");
    }

    public int getIntData(Context context, String key){
        return getBaseConfig(context).getInt(key,-1);
    }

    public float getFloatData(Context context, String key){
        return getBaseConfig(context).getFloat(key,-1.0f);
    }

    public void clearData(Context context, String key){
        getBaseConfig(context).edit().remove(key).apply();
    }

    public void clearAllData(Context context, String key){
        getBaseConfig(context).edit().clear().apply();
    }

}
