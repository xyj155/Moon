package com.example.module_member.vpfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.module_base.base.BaseFragment;
import com.example.module_base.bean.Advertisement;
import com.example.module_base.util.GlideImageLoader;
import com.example.module_base.view.toast.ToastUtils;
import com.example.module_member.R;
import com.example.module_member.R2;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class PurseFragment extends BaseFragment {

    @BindView(R2.id.sml_purse)
    SmartRefreshLayout smlPurse;
    Unbinder unbinder;

    @Override
    public int initLayout() {
        return R.layout.fragment_purse;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initBanner();
    }

    private void initBanner() {

    }

    private static final String TAG = "PurseFragment";

    @Override
    public void initData() {

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
