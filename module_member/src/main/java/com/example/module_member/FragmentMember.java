package com.example.module_member;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.module_base.base.BaseFragment;
import com.example.module_base.util.BaseFragmentPagerAdapter;
import com.example.module_base.view.title.ScaleTransitionPagerTitleView;
import com.example.module_member.vpfragment.MemberBaseVideoFragment;
import com.example.module_member.vpfragment.PurseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentMember extends BaseFragment {


    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.mg_member)
    MagicIndicator mgMember;
    @BindView(R2.id.vp_member)
    ViewPager vpMember;
    Unbinder unbinder;
    private String[] titles = {"精选", "自拍", "欧美", "日韩", "SM", "动漫", "高清", "三级", "无码", "4K"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private BaseFragmentPagerAdapter baseFragmentPagerAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_member;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        initViewPager();

    }

    private void initViewPager() {
        fragmentList.add(new PurseFragment());
        for (int i = 1; i < titles.length; i++) {
            MemberBaseVideoFragment memberBaseVideoFragment = new MemberBaseVideoFragment();
            memberBaseVideoFragment.setTitle(titles[i]);
            fragmentList.add(memberBaseVideoFragment);
        }
        baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(titles[index]);
                simplePagerTitleView.setTextSize(20);//设置导航的文字大小
                TextPaint paint = simplePagerTitleView.getPaint();
                paint.setFakeBoldText(true);
                simplePagerTitleView.setNormalColor(Color.GRAY);//正常状态下的标题颜色
                simplePagerTitleView.setSelectedColor(0xff000000);//选中的标题字体颜色
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vpMember.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        vpMember.setCurrentItem(0);
        mgMember.setNavigator(commonNavigator);
        vpMember.setOffscreenPageLimit(titles.length);
        vpMember.setAdapter(baseFragmentPagerAdapter);
        ViewPagerHelper.bind(mgMember, vpMember);
    }

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
