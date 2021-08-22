package com.example.rokomaritest.view.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.rokomaritest.R;
import com.example.rokomaritest.databinding.ActivitySignUpBinding;
import com.example.rokomaritest.model.responseModel.SignUpResponse;
import com.example.rokomaritest.viewControllers.interfaces.SignUpListener;
import com.example.rokomaritest.viewControllers.managers.AuthenticationManager;

import org.w3c.dom.Text;

public class SignUpActivity extends BaseActivity {
    ActivitySignUpBinding signUpBinding;
    AuthenticationManager authenticationManager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        //setToolbar("",false ,true,true,false,signUpBinding.toolbarBinding);
    }

    @Override
    protected void viewRelatedTask() {
        signUpBinding.signUp.setOnClickListener(v -> {
            String name = signUpBinding.name.getText().toString();
            String email = signUpBinding.email.getText().toString();
            String pass = signUpBinding.pass.getText().toString();
            if(TextUtils.isEmpty(name)){
                showToast(getString(R.string.please_insert_name));
            } else if(TextUtils.isEmpty((email))){
                showToast(getString(R.string.please_insert_email));
            } else if(TextUtils.isEmpty((pass))){
                showToast(getString(R.string.please_insert_password));
            }
            else {
                callSignUpApi(name,email,pass);
            }
        });
    }

    private void callSignUpApi(String name, String email, String pass) {

        if (isValidEmail(email)){
            authenticationManager = new AuthenticationManager(this);
            authenticationManager.signUp(name,email,pass, new SignUpListener() {


                @Override
                public void onSuccess(SignUpResponse signUpResponse) {
                    showToast(signUpResponse.getSuccess());
                }

                @Override
                public void onFailed(String message, int responseCode) {
                    showToast(message);
                }

                @Override
                public void startLoading(String requestId) {

                }

                @Override
                public void endLoading(String requestId) {

                }
            });
        } else
        {
            showToast(getString(R.string.please_insert_validEmail));
        }


    }
}