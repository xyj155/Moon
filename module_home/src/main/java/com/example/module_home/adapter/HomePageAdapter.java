package com.example.module_home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_base.bean.PurseVideo;
import com.example.module_base.util.GlideUtil;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.module_home.utils.GlideImageLoader;
import com.example.module_home.view.WebViewActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PurseVideo> videoList;
    private List<String> imagesList=new ArrayList<>();
    private List<String> webUrlList =new ArrayList<>();
    private Context context;
    private int headCount = 1;//头部个数
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NOE = 1;
    public static final int TYPE_TWO = 2;

    public HomePageAdapter(Context context, List<PurseVideo> videoList, List<String> imagesList, List<String> webUrlList) {
        this.videoList = videoList;
        this.context = context;
        this.imagesList = imagesList;
        this.webUrlList = webUrlList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_NOE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == TYPE_HEADER) {
            View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_head_layout, viewGroup, false);
            return new MyHeaderHolder(layout);
        }
        if (i == TYPE_NOE) {
            View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_list_layout, viewGroup, false);
            return new MyTypeOneViewHolder(layout);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        if (holder instanceof MyTypeOneViewHolder) {
            int pos = i - 1;
            //注意除去头布局
            holder.itemView.setTag(pos);

            ((MyTypeOneViewHolder) holder).tv_video_name.setText(videoList.get(pos).getVideoName());
            ((MyTypeOneViewHolder) holder).tv_video_time.setText(videoList.get(pos).getVideoDuration());
            GlideUtil.loadRoundCornerAvatarImage(videoList.get(pos).getVideoPictureUrl(), ((MyTypeOneViewHolder) holder).iv_video_pic,10);
        }
        if (holder instanceof MyHeaderHolder) {
            //设置图片加载器
            ((MyHeaderHolder) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            ((MyHeaderHolder) holder).banner.setImages(imagesList);
            //设置banner动画效果
            ((MyHeaderHolder) holder).banner.setBannerAnimation(Transformer.Default);
            //设置自动轮播，默认为true
            ((MyHeaderHolder) holder).banner.isAutoPlay(true);
            //设置轮播时间
            ((MyHeaderHolder) holder).banner.setDelayTime(5000);
            //设置指示器位置（当banner模式中有指示器时）
            ((MyHeaderHolder) holder).banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            ((MyHeaderHolder) holder).banner.start();
            ((MyHeaderHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("url", webUrlList.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (videoList == null) {
            return headCount;
        }
        return videoList.size() + headCount;
    }

    class MyHeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.mBanner)
        Banner banner;

        public MyHeaderHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyTypeOneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_video_pic)
        ImageView iv_video_pic;
        @BindView(R2.id.tv_video_name)
        TextView tv_video_name;
        @BindView(R2.id.tv_video_time)
        TextView tv_video_time;

        public MyTypeOneViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
