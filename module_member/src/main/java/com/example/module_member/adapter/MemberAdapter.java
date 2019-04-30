package com.example.module_member.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.module_base.bean.MemberVideo;
import com.example.module_base.util.GlideUtil;
import com.example.module_member.R;

import java.util.List;

public class MemberAdapter extends BaseQuickAdapter<MemberVideo, BaseViewHolder> {
    public MemberAdapter(@Nullable List<MemberVideo> data) {
        super(R.layout.ry_member_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MemberVideo item) {
        GlideUtil.loadRoundCornerAvatarImage(item.getVideoPictureUrl(), (ImageView) helper.getView(R.id.iv_cover), 8);
        helper.setText(R.id.tv_duration, item.getVideoDuration())
                .setText(R.id.tv_title, item.getVideoName());
    }
}
