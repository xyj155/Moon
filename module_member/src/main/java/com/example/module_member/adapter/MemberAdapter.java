package com.example.module_member.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_base.base.VideoPlayerActivity;
import com.example.module_base.bean.MemberVideo;
import com.example.module_base.util.GlideUtil;
import com.example.module_member.R;

import java.util.List;

public class MemberAdapter extends BaseQuickAdapter<MemberVideo, BaseViewHolder> {
    private Context context;

    public MemberAdapter(@Nullable List<MemberVideo> data, Context context) {
        super(R.layout.ry_member_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberVideo item) {
        helper.setOnClickListener(R.id.rl_video, v -> {
            Intent intent = new Intent(context, VideoPlayerActivity.class);
            intent.putExtra("url", item.getVideoPlayUrl());
            intent.putExtra("picture", item.getVideoPictureUrl());
            intent.putExtra("title", item.getVideoName());
            intent.putExtra("duration", item.getVideoDuration());
            Log.i(TAG, "convert: " + item.getVideoPlayUrl());
            context.startActivity(intent);
        });
        GlideUtil.loadRoundCornerAvatarImage(item.getVideoPictureUrl(), (ImageView) helper.getView(R.id.iv_cover), 8);
        helper.setText(R.id.tv_duration, item.getVideoDuration())
                .setText(R.id.tv_title, item.getVideoName());
    }
}
