package com.example.module_user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_base.adapter.GlideImageLoader;
import com.example.module_base.base.BaseFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {
    @BindView(R2.id.banner_user)
    Banner bannerUser;
    Unbinder unbinder;

    @Override
    public int initLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.test);
        bannerUser.setImageLoader(new GlideImageLoader());
        bannerUser.setImages(list);
        bannerUser.start();

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
