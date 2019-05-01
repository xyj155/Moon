package com.example.module_home.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_base.base.BaseFragment;
import com.example.module_base.bean.Advertisement;
import com.example.module_base.bean.PurseVideo;
import com.example.module_base.view.toast.ToastUtils;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.module_home.adapter.RecommendAdapter;
import com.example.module_home.utils.BannerViewHolder;
import com.example.module_home.view.VideoPlayerActivity;
import com.example.module_home.view.WebViewActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;


import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeRecommendFragment extends BaseFragment {

    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    Unbinder unbinder;
    private List<PurseVideo> datalist = new ArrayList<>();
    RecommendAdapter adapter;
    MZBannerView mBanner;

    @Override
    public int initLayout() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    public void initView(View view) {

        unbinder = ButterKnife.bind(this,view);

        mSmartRefreshLayout.autoRefresh();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        adapter = new RecommendAdapter(datalist,getContext());
        mRecyclerView.setAdapter(adapter);

        //添加头部布局
        View headview = LayoutInflater.from(getContext()).inflate(R.layout.home_head_layout,null);
        mBanner = (MZBannerView) headview.findViewById(R.id.mBanner);
        initBanner();
        adapter.addHeaderView(headview);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                intent.putExtra("url", datalist.get(position).getVideoPlayUrl());
                intent.putExtra("title", datalist.get(position).getVideoName());
                startActivity(intent);
            }
        });
    }

    private void initBanner() {
        BmobQuery<Advertisement> bmobQuery = new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<Advertisement>() {
            @Override
            public void done(final List<Advertisement> list, BmobException e) {
                if (e==null){
                    // 设置数据
                    mBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
                        @Override
                        public BannerViewHolder createViewHolder() {
                            return new BannerViewHolder();
                        }
                    });
                    //点击事件
                    mBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
                        @Override
                        public void onPageClick(View view, int i) {
                            Intent intent = new Intent(getContext(), WebViewActivity.class);
                            intent.putExtra("url",list.get(i).getBannerLindUrl());
                            startActivity(intent);
                        }
                    });

                    mBanner.start();

                }else {
                    Log.i("tag1","获取轮播图失败"+e.getMessage());
                }
            }
        });
    }
    @Override
    public void initData() {
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                //刷新轮播图
                initBanner();
                //获取视频列表
                BmobQuery<PurseVideo> bmobQuery = new BmobQuery<>();
                bmobQuery.setLimit(10);
                bmobQuery.setSkip(0);
                bmobQuery.order("-createdAt");
                bmobQuery.findObjects(new FindListener<PurseVideo>() {
                    @Override
                    public void done(List<PurseVideo> list, BmobException e) {
                        if (e==null){
                            datalist.clear();
                            datalist.addAll(list);
                            adapter.replaceData(datalist);
                        }else {
                            ToastUtils.show("获取数据失败，请重试~");
                        }
                        mSmartRefreshLayout.finishRefresh();
                    }
                });
            }
        });
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                BmobQuery<PurseVideo> bmobQuery = new BmobQuery<>();
                bmobQuery.setLimit(10);
                bmobQuery.setSkip(datalist.size());
                bmobQuery.order("-createdAt");
                bmobQuery.findObjects(new FindListener<PurseVideo>() {
                    @Override
                    public void done(List<PurseVideo> list, BmobException e) {
                        if (e==null){
                            datalist.addAll(list);
                            adapter.replaceData(datalist);
                        }else {
                            ToastUtils.show("获取数据失败，请重试~");
                        }
                        mSmartRefreshLayout.finishLoadMore();
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mBanner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.start();//开始轮播
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
