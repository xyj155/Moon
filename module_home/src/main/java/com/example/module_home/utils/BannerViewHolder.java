package com.example.module_home.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.module_base.bean.Advertisement;
import com.example.module_base.util.GlideUtil;
import com.example.module_home.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

//轮播图图片加载器
public class BannerViewHolder implements MZViewHolder<Advertisement> {
    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
        mImageView = (ImageView) view.findViewById(R.id.banner_image);
        return view;
    }

    @Override
    public void onBind(Context context, int i, Advertisement advertisement) {
        // 数据绑定
        GlideUtil.loadRoundCornerAvatarImage(advertisement.getBannerPictureUrl(), mImageView,10);
    }

}