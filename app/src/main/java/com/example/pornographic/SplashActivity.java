package com.example.pornographic;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.module_base.base.BaseActivity;
import com.example.module_base.util.GlideUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Xuyijie
 */
public class SplashActivity extends BaseActivity {


    ImageView ivSplash;

    @Override
    public int initActivityLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        ivSplash = findViewById(R.id.iv_splash);
        ButterKnife.bind(this);
        Glide.with(SplashActivity.this).asBitmap().load(R.drawable.splash_bg).into(ivSplash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    public void initData() {

    }

}
