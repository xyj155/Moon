package com.example.module_home.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_base.bean.PurseVideo;
import com.example.module_base.util.GlideUtil;
import com.example.module_home.R;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<PurseVideo, BaseViewHolder> {
    public RecommendAdapter(@Nullable List<PurseVideo> data, Context context) {
        super(R.layout.item_video_list_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PurseVideo item) {
        helper.setText(R.id.tv_video_name,item.getVideoName());
        helper.setText(R.id.tv_video_time,item.getVideoDuration());
        GlideUtil.loadRoundCornerAvatarImage(item.getVideoPictureUrl(), (ImageView) helper.getView(R.id.iv_video_pic),10);
    }
}
