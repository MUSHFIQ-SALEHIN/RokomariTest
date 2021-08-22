package com.example.rokomaritest.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.rokomaritest.R;
import com.example.rokomaritest.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity {

    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void viewRelatedTask() {

    }


}