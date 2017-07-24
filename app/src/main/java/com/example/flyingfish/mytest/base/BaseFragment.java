package com.example.flyingfish.mytest.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Flyingfish on 2017/7/21.
 * 作用：基类，公共类
 * CommonFrameFragment，ThirdPartyFragment，CustomFragment，OtherFragment等都要继承该类
 */

public abstract class BaseFragment extends Fragment{
    //上下文
    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 强制子类重写，实现子类特有的UI
     * @return
     */
    protected abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要初始化数据，或者联网请求绑定数据，展示数据  等等可以重写该方法
     */
    protected void initData() {
    }
}
