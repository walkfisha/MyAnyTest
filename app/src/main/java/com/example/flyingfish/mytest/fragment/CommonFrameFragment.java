package com.example.flyingfish.mytest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.adapter.CommonFrameFragmentAdapter;
import com.example.flyingfish.mytest.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 常用框架fragment
 */
public class CommonFrameFragment extends BaseFragment {

    private String[] datas;
    private CommonFrameFragmentAdapter adapter;
    private ListView mListView;

    private static final String TAG = CommonFrameFragment.class.getSimpleName();

    @Override
    protected View initView() {
        Log.e(TAG, "常用框架fragment页面被初始化了。。。。");
        View view = View.inflate(mContext, R.layout.fragment_common_frame, null);
        mListView = view.findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = datas[i];
                Toast.makeText(mContext,"data == "+data,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //准备数据
        datas = new String[]{"OKHttp", "xUtils3", "Retrofit2", "Fresco", "Glide", "GreenDao", "RxJava"};
        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, datas);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
