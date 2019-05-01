package com.example.module_home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.module_base.base.BaseFragment;
import com.example.module_base.bean.Comic;
import com.example.module_base.bean.Europe;
import com.example.module_base.view.toast.ToastUtils;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.module_home.adapter.ComicVideoListAdapter;
import com.example.module_home.adapter.EuropeVideoListAdapter;
import com.example.module_home.view.VideoPlayerActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HomeComicFragment extends BaseFragment {
    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.mSmartRefreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private List<Comic> datalist = new ArrayList<>();
    ComicVideoListAdapter adapter;
    Unbinder unbinder;
    @Override
    public int initLayout() {
        return R.layout.fragment_home_comic;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this,view);
        mSmartRefreshLayout.autoRefresh();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new ComicVideoListAdapter(datalist,getContext());
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                intent.putExtra("url", datalist.get(position).getVideoUrl());
                intent.putExtra("title", datalist.get(position).getVideoName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                BmobQuery<Comic> bmobQuery = new BmobQuery<>();
                bmobQuery.setLimit(10);
                bmobQuery.setSkip(0);
                bmobQuery.order("-createdAt");
                bmobQuery.findObjects(new FindListener<Comic>() {
                    @Override
                    public void done(List<Comic> list, BmobException e) {
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
                BmobQuery<Comic> bmobQuery = new BmobQuery<>();
                bmobQuery.setLimit(10);
                bmobQuery.setSkip(datalist.size());
                bmobQuery.order("-createdAt");
                bmobQuery.findObjects(new FindListener<Comic>() {
                    @Override
                    public void done(List<Comic> list, BmobException e) {
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
