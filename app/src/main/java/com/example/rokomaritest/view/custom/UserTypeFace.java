package com.example.rokomaritest.view.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Hashtable;

public class UserTypeFace {

    public static final String BOLD;
    public static final String LIGHT;
    public static final String NORMAL;
    public static final String ITALIC;
    public static final String MEDIUM;

    public static final String TITLE_BOLD;
    public static final String TITLE_LIGHT;
    public static final String TITLE_NORMAL;
    public static final String TITLE_ITALIC;
    public static final String TITLE_MEDIUM;

    static {
        NORMAL ="fonts/font-normal.ttf";
        LIGHT="fonts/font-light.ttf";
        BOLD="fonts/font-bold.ttf";
        MEDIUM ="fonts/font-medium.ttf";
        ITALIC ="fonts/font-italic.ttf";

        TITLE_NORMAL ="fonts/font-normal.ttf";
        TITLE_LIGHT="fonts/font-light.ttf";
        TITLE_BOLD="fonts/font-bold.ttf";
        TITLE_MEDIUM ="fonts/font-medium.ttf";
        TITLE_ITALIC ="fonts/font-italic.ttf";
    }

    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    private static Typeface getTypeFace(Context context, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface typeFace = Typeface.createFromAsset(
                            context.getAssets(), assetPath);
                    cache.put(assetPath, typeFace);
                } catch (Exception e) {
                    Log.e("TypeFaces", "Typeface not loaded.");
                    return null;
                }
            }
            return cache.get(assetPath);
        }
    }

    public static void SetLight(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),LIGHT), Typeface.NORMAL);
    }

    public static void SetBold(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),BOLD), Typeface.BOLD);
    }

    public static void SetItalic(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), ITALIC), Typeface.ITALIC);
    }
    public static void SetNormal(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), NORMAL), Typeface.NORMAL);
    }

    public static void SetMedium(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), MEDIUM), Typeface.BOLD);
    }

    public static void SetTitleLight(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),LIGHT), Typeface.NORMAL);
    }

    public static void SetTitleBold(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(),BOLD), Typeface.BOLD);
    }

    public static void SetTitleItalic(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), ITALIC), Typeface.ITALIC);
    }
    public static void SetTitleNormal(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), NORMAL), Typeface.NORMAL);
    }

    public static void SetTitleMedium(TextView obj){
        obj.setTypeface(getTypeFace(obj.getContext(), MEDIUM), Typeface.BOLD);
    }

    public static Typeface getNormal(View obj){
        return getTypeFace(obj.getContext(), NORMAL);
    }
}