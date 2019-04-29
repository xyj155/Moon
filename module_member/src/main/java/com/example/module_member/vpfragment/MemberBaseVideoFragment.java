package com.example.module_member.vpfragment;

import android.os.Bundle;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_base.base.BaseFragment;
import com.example.module_base.bean.MemberVideo;
import com.example.module_base.view.toast.ToastUtils;
import com.example.module_member.R;
import com.example.module_member.R2;
import com.example.module_member.adapter.MemberAdapter;
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

public class MemberBaseVideoFragment extends BaseFragment {

    @BindView(R2.id.ry_member)
    RecyclerView ryMember;
    @BindView(R2.id.sml_member)
    SmartRefreshLayout smlMember;
    Unbinder unbinder;
    private String title;
    private MemberAdapter memberAdapter;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_member_base_video;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        memberAdapter = new MemberAdapter(memberVideoList,getContext());
    }

    private List<MemberVideo> memberVideoList = new ArrayList<>();

    private void initVideoData(String title) {
        BmobQuery<MemberVideo> bmobQuery = new BmobQuery<>();
        bmobQuery.addWhereEqualTo("kind", title);
        bmobQuery.setLimit(14);
        bmobQuery.findObjects(new FindListener<MemberVideo>() {
            @Override
            public void done(List<MemberVideo> list, BmobException e) {
                if (e == null) {
                    if (list.size() > 0) {
                        memberVideoList.addAll(list);
                        memberAdapter.addData(memberVideoList);
                    }
                } else {
                    ToastUtils.show("请求出错了哦！请重试" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void initData() {
        final String title = getTitle();
        initVideoData(title.isEmpty() ? "" : getTitle());
        ryMember.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ryMember.setAdapter(memberAdapter);
        smlMember.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                BmobQuery<MemberVideo> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("kind", title);
                bmobQuery.setSkip(1);
                bmobQuery.findObjects(new FindListener<MemberVideo>() {
                    @Override
                    public void done(List<MemberVideo> list, BmobException e) {
                        if (e == null) {
                            if (list.size() > 0) {
                                memberVideoList.clear();
                                memberVideoList.addAll(list);
                                memberAdapter.addData(memberVideoList);
                            }
                        } else {
                            ToastUtils.show("请求出错了哦！请重试" + e.getMessage());
                        }
                        smlMember.finishRefresh();
                    }
                });
            }
        });
        smlMember.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                BmobQuery<MemberVideo> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("kind", title);
                bmobQuery.setSkip(memberVideoList.size());
                bmobQuery.findObjects(new FindListener<MemberVideo>() {
                    @Override
                    public void done(List<MemberVideo> list, BmobException e) {
                        if (e == null) {
                            if (list.size() > 0) {
                                memberVideoList.addAll(list);
                                memberAdapter.addData(memberVideoList);
                            }
                        } else {
                            ToastUtils.show("请求出错了哦！请重试" + e.getMessage());
                        }
                        smlMember.finishLoadMore();
                    }
                });
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
