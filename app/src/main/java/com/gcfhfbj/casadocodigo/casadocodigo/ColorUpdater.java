package com.gcfhfbj.casadocodigo.casadocodigo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/**
 * Created by android7281 on 08/09/17.
 */

public class ColorUpdater {

    private AppCompatActivity activity;
    FirebaseRemoteConfig remoteConfig;

    public ColorUpdater(FirebaseRemoteConfig remoteConfig, AppCompatActivity activity) {
        this.remoteConfig = remoteConfig;
        this.activity = activity;
        refreshConfig();
    }

    /*public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }*/

    public void refreshConfig() {
        remoteConfig.fetch(30).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    remoteConfig.activateFetched();
                    String toolbarColor = remoteConfig.getString("cor_toolbar");
                    String statusbarColor = remoteConfig.getString("cor_statusbar");
                    //setBarsColor(remoteConfig.getString("cor_toolbar"), remoteConfig.getString("cor_statusbar"));
                    setBarsColor(toolbarColor, statusbarColor);
                } else {
                    Toast.makeText(activity, "Task was not successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setBarsColor(String toolbarColorStr, String statusbarColorStr) {
        int toolbarColor = Color.parseColor(toolbarColorStr);
        int statusbarColor = Color.parseColor(statusbarColorStr);

        //configura cor toolbar
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(toolbarColor));

        //configura cor da status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusbarColor);
        }
    }
}
