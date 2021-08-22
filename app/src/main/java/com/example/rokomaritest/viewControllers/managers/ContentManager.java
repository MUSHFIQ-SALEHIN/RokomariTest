package com.example.rokomaritest.viewControllers.managers;

import android.content.Context;
import android.util.Log;

import com.example.rokomaritest.model.configuration.ApiHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;


public class ContentManager {
    private Context context;
    private ApiHandler apiHandler;



    public ContentManager(Context context) {
        this.context = context;
        apiHandler = new ApiHandler(context) {
            @Override
            public void startApiCall(String requestId) {

            }

            @Override
            public void endApiCall(String requestId) {

            }


            @Override
            public void successResponse(String requestId, ResponseBody responseBody, String baseUrl, String path, String requestType) {

            }

            @Override
            public void failResponse(String requestId, int responseCode, String message) {

            }


        };
    }
}
