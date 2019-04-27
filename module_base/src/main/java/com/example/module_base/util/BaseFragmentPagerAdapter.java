package com.example.module_base.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

public class BaseFragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);

            this.fragmentList = fragmentList;
        }


        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }