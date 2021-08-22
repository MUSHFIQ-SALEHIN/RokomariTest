package com.example.rokomaritest.view.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.rokomaritest.R;
import com.example.rokomaritest.databinding.ToolbarBinding;
import com.example.rokomaritest.model.utils.ShareInfo;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;


public abstract class BaseActivity extends AppCompatActivity {

    private static final int reqCode = 580;
    public DisplayMetrics displayMetrics = new DisplayMetrics();
    public ProgressDialog progressDialog;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String language;
    MediaPlayer appBackSound;
    public static final Pattern PHONE
            = Pattern.compile(
            "(\\+[0-9]+[\\- ]*)?"
                    + "(\\([0-9]+\\)[\\- ]*)?"
                    + "([0-9][0-9\\- ]+[0-9])");

    public Typeface defaultTypeface;
    Intent backgroundSoundServiceIntent;

    protected BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        progressDialog = new ProgressDialog(BaseActivity.this, R.style.CustomProgress);
        defaultTypeface = Typeface.createFromAsset(getAssets(),"fonts/font-normal.ttf");
    }
    public boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        ShareInfo.goNextPage(this);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        ShareInfo.goNextPage(this);
    }

    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
            ShareInfo.goPreviousPage(this);
        } catch (Exception e) {
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewRelatedTask();
    }

    public void showToast(String message) {
        showToast(message, false);
    }

    public void showToast(String message, boolean isLong) {
        Toast.makeText(getApplicationContext(), message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    protected abstract void viewRelatedTask();

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void hideKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                    .HIDE_NOT_ALWAYS);
        }
    }


    public void showProgressDialog(String message, boolean isCancelable) {
        if(progressDialog==null){
            progressDialog = new ProgressDialog(BaseActivity.this, R.style.CustomProgress);
        }
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(R.style.CustomProgress);
        progressDialog.setCancelable(isCancelable);
        progressDialog.show();

        if (progressDialog.isShowing())
            Log.e("progress_", "is showing");
        else
            Log.e("progress_", "is not showing");
    }

    public void dismissProgressDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

   /* public Toolbar setToolbar(String title, boolean backButton, boolean b1, boolean b, boolean hasBackButton, ToolbarBinding toolbarBinding) {
        setSupportActionBar(toolbarBinding.toolbar);
        toolbarBinding.toolbarTitle.setText(title);
        if (hasBackButton) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
        } else {
            toolbarBinding.toolbar.setTitle("    "); // quickfix; need to do it more conveniently
        }
        return toolbarBinding.toolbar;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToHome() {
        Intent intent = new Intent(BaseActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void goToLoginScreen() {

       /* Intent intent = new Intent(BaseActivity.this, WelcomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

   /* public void logout(){
        LocalStorage.getInstance().clearData(this, "LOGGED_IN_USER");
    }*/

    public void refreshAnimation(final View view){
        YoYo.with(Techniques.RotateIn)
                .duration(750)
                .repeat(1)
                .playOn(view);
    }

    public void showView(final View view, Techniques techniques) {
        YoYo.with(techniques)
                .delay(100)
                .duration(700)
                .repeat(0)
                .playOn(view);

        new Thread(() -> {
            try {
                Thread.sleep(105);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> view.setVisibility(View.VISIBLE));
        }).start();
    }

    public void hideView(final View view, Techniques techniques) {
        YoYo.with(techniques)
                .duration(700)
                .repeat(0)
                .playOn(view);

        new Thread(() -> {
            try {
                Thread.sleep(690);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> view.setVisibility(View.INVISIBLE));
        }).start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard();
    }


    public void hideViewIfKeyboardShowing(View contentView, View viewToHide){
        contentView.postDelayed(() -> runOnUiThread(() -> contentView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {

            Rect r = new Rect();
            contentView.getWindowVisibleDisplayFrame(r);
            int screenHeight = contentView.getRootView().getHeight();

            // r.bottom is the position above soft keypad or device button.
            // if keypad is shown, the r.bottom is smaller than that before.
            int keypadHeight = screenHeight - r.bottom;

            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
                // keyboard is opened
                viewToHide.setVisibility(View.GONE);
            }
            else {
                // keyboard is closed
                viewToHide.setVisibility(View.VISIBLE);
            }
        })), 250);
    }

    public Drawable getDrawableImage(String imagePath) throws IOException {
        return Drawable.createFromStream(getAssets().open(imagePath),null);
    }


    public boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    public static boolean isValidPhone(CharSequence target) {
        return (!TextUtils.isEmpty(target) && PHONE.matcher(target).matches());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
