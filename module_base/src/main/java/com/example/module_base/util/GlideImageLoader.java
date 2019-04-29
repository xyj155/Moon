package com.example.module_base.util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.module_base.bean.Advertisement;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {
    private static final String TAG = "GlideImageLoader";
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path).into(imageView);
        Log.i(TAG, "displayImage: "+path);
    }

    @Override
    public ImageView createImageView(Context context) {
        //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
        ImageView simpleDraweeView=new ImageView(context);
        return simpleDraweeView;
    }
}