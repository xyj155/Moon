package com.example.module_base.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_base.R;
import com.example.module_base.util.StatusBarUtil;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity {


    ArrayList<Activity> activities = new ArrayList<Activity>();

    /**
     * 屏幕宽
     */
    public static int SCREEN_WIDTH;

    /**
     * 屏幕高
     */
    public static int SCREEN_HEIGHT;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initActivityLayout());
        StatusBarUtil.setStatusBarTextColor(this);
        StatusBarUtil.setStatusBarTranslucent(this);
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        addActivity();
        initView();
        initData();

    }

    public BaseActivity initToolbar(String title) {
        TextView viewById = findViewById(R.id.tv_title);
        viewById.setText(title);
        ImageView viewById1 = findViewById(R.id.iv_close);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return this;
    }

    public abstract int initActivityLayout();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeActivity();
    }

    public void addActivity() {
        activities.add(this);
    }

    public void removeActivity() {
        activities.remove(this);
    }

    /**
     * 结束所以活动
     */
    public void finishAllAcitivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 跳转
     */
    public void startAction(@SuppressWarnings("rawtypes") Class cls,
                            String name, String value) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(name, value);
        startActivity(intent);
    }
}

