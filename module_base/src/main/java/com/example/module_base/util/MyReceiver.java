package com.example.module_base.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private List<String> list = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理

            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);
//此处，我们取出的是通知的内容。还可以取出别的信息，这个大家可自行处理

//           在这里可以自己写代码去定义用户点击后的行为
//            Intent i = new Intent(context, MainActivity.class);
//            i.putExtra("content", content);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(i);
        } else {
        }
    }
}
