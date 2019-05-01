package com.example.module_search;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.module_base.base.BaseFragment;
import com.example.module_search.adpter.SearAdapter;
import com.example.module_search.customview.NonScrollGridView;
import com.example.module_search.view.act.ActSearch;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SearchFragment extends BaseFragment {


    @BindView(R2.id.gridSearch)
    NonScrollGridView gridSearch;

    private SearAdapter searAdapter;
    private ArrayList<String> datas = new ArrayList<>();





    Unbinder unbinder;

    @Override
    public int initLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void initData() {

        datas.clear();


        for (int i = 0; i < 21; i++) {
            datas.add("测试" + i);
        }


        searAdapter = new SearAdapter(datas, getActivity());
        gridSearch.setAdapter(searAdapter);


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


//    @OnClick(R2.id.lin_sear)
//    public void onClick(View view) {
////        switch (view.getId()){
////            case R2.id.lin_sear:
////                if (TextUtils.isEmpty(edit_sear.getText().toString().trim())){
////
////                }else {
////                    Intent intent = new Intent(getActivity(),ActSearch.class);
////                    intent.putExtra("edit_sear",edit_sear.getText().toString().trim());
////                    startActivity(intent);
////                }
////                break;
////        }
//        int i = view.getId();
////        if (i == R.id.lin_sear) {
////            if (TextUtils.isEmpty(edit_sear.getText().toString().trim())) {
////            } else {
////                Intent intent = new Intent(getActivity(), ActSearch.class);
////                intent.putExtra("edit_sear", edit_sear.getText().toString().trim());
////                startActivity(intent);
////            }
////        }
//    }
}
