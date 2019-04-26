package com.example.module_base.util;


import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_base.App;
import com.example.module_base.view.toast.ToastUtils;
import com.payelves.sdk.EPay;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.UpgradeInfo;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;

/**
 * @author Xuyijie
 */
public class ApplicationInitial {
    public ApplicationInitial initBmob() {
        Bmob.initialize(App.getInstance(), "2a33abea32080fbb53b6103a504347ec");
        return this;
    }

    public ApplicationInitial initToast() {
        ToastUtils.init(App.getInstance());
        return this;
    }

//    public ApplicationInitial initMob() {
//        MobSDK.init(App.getInstance(), "2ad59d9ca652d", "d508e251cdd62bdc91e162f6a66880c2");
//        return this;
//    }

    public ApplicationInitial initPayment() {
        EPay.getInstance(App.getInstance()).init("wAwS4BHkB", "1b0ccf51458c4053ae2931772fbbfb97",
                "7778897507581955", "baidu");
        return this;
    }

    public ApplicationInitial initJpush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(App.getInstance());
        return this;
    }

    public ApplicationInitial initArouter() {
        ARouter.openLog();     // Print log
        ARouter.openDebug();
        ARouter.init(App.getInstance());
        return this;
    }

    public ApplicationInitial initBugly() {
//        Beta.autoInit = true;
//        Beta.autoCheckUpgrade = true;
//        Bugly.init(App.getInstance(), "c02bea3990", false);
//        UpgradeInfo upgradeInfo = Beta.getUpgradeInfo();
//        Log.i(TAG, "initBugly: " + upgradeInfo);
        return this;
    }

    private static final String TAG = "ApplicationInitial";
}
