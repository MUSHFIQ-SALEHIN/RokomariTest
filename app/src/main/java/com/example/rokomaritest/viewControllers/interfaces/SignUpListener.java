package com.example.rokomaritest.viewControllers.interfaces;


import com.example.rokomaritest.model.responseModel.SignUpResponse;

public interface SignUpListener extends BaseApiCallListener {

    void onSuccess(SignUpResponse signUpResponse);
    void onFailed(String message, int responseCode);
}
