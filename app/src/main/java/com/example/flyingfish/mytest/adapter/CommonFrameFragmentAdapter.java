package com.example.flyingfish.mytest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Flyingfish on 2017/7/24.
 * 常用框架的fragment内要显示的数据
 */

public class CommonFrameFragmentAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mDatas;

    public CommonFrameFragmentAdapter(Context mContext, String[] mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView  = new TextView(mContext);
        textView.setTextSize(20);
        textView.setPadding(15,15,0,15);
        textView.setTextColor(Color.BLACK);
        textView.setText(mDatas[i]);
        return textView;
    }
}
