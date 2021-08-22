package com.example.rokomaritest.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.rokomaritest.R;


public class CustomTextView extends AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomTextView(Context context) {
        super(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (!this.isInEditMode()) { // used for preview while designing.
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.custom);
            int type = a.getInteger(R.styleable.custom_textStyle, 0);
            if (type == 0) {
                UserTypeFace.SetNormal(this); //Set Default Font if font is not defined in xml
                return;
            }
            setStyle(type);
        } else {
            setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        }
    }


    private void setStyle(int type) {
        switch (type) {
            case 0:
                UserTypeFace.SetNormal(this);
                break;
            case 1:
                UserTypeFace.SetBold(this);
                break;
            case 2:
                UserTypeFace.SetItalic(this);
                break;
            case 3:
                UserTypeFace.SetLight(this);
                break;
            case 4:
                UserTypeFace.SetMedium(this);
                break;
            case 5:
                UserTypeFace.SetTitleNormal(this);
                break;
            case 6:
                UserTypeFace.SetTitleBold(this);
                break;
            case 7:
                UserTypeFace.SetTitleItalic(this);
                break;
            case 8:
                UserTypeFace.SetTitleLight(this);
                break;
            case 9:
                UserTypeFace.SetTitleMedium(this);
                break;
            default:
                UserTypeFace.SetNormal(this);
        }

    }

}
