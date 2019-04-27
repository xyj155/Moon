package com.example.module_home;

import android.view.View;

import com.example.module_base.base.BaseFragment;


import net.lucode.hackware.magicindicator.MagicIndicator;

import org.w3c.dom.Text;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R2.id.tv_title)
    Text mg_title;
//    @BindView(R2.id.mg_title)
//    MagicIndicator mg_title;
    

    @Override
    public int initLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }
}
