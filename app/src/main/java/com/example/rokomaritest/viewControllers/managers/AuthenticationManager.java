package com.example.rokomaritest.viewControllers.managers;

import static com.example.rokomaritest.model.configuration.ResponseCode.INVALID_JSON_RESPONSE;

import android.content.Context;
import android.util.Log;

import com.example.rokomaritest.model.configuration.ApiHandler;
import com.example.rokomaritest.model.responseModel.SignUpResponse;
import com.example.rokomaritest.model.utils.ShareInfo;
import com.example.rokomaritest.viewControllers.interfaces.SignUpListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;



public class AuthenticationManager {

    private Context context;
    ApiHandler apiHandler;
    private String reqIdSignUp;
    SignUpListener signUpListener;


    public AuthenticationManager(Context context) {
        this.context=context;

        apiHandler= new ApiHandler(context) {
            @Override
            public void startApiCall(String requestId) {
                if(requestId.equals(reqIdSignUp)){
                    signUpListener.startLoading(requestId);
                }

            }
            @Override
            public void endApiCall(String requestId) {
                if(requestId.equals(reqIdSignUp)){
                    signUpListener.endLoading(requestId);
                }
            }


            @Override
            public void successResponse(String requestId, ResponseBody responseBody, String baseUrl, String path, String requestType) {
                if ( requestId.equals(reqIdSignUp)){
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        signUpListener.onSuccess(new Gson().fromJson(jsonObject.toString(), SignUpResponse.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        signUpListener.onFailed("Invalid  Response", INVALID_JSON_RESPONSE);
                    }
                }
            }

            @Override
            public void failResponse(String requestId, int responseCode, String message) {
                if(requestId.equals(reqIdSignUp)){
                    signUpListener.onFailed(message, responseCode);
                }
            }
        };
    }

    public String signUp(String name, String email, String pass,SignUpListener signUpListener){
        this.signUpListener = signUpListener;
        this.reqIdSignUp = ShareInfo.getInstance().getRequestId();
        HashMap hashMap= new HashMap();
        hashMap.put("username",name);
        hashMap.put("email",email);
        hashMap.put("password",pass);

        apiHandler.httpRequest(ShareInfo.getInstance().getBaseUrl(), "auth/register/", "post", reqIdSignUp, hashMap);
        return reqIdSignUp;
    }

}
