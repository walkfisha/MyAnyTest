package com.example.flyingfish.mytest.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.json.activity.FastJsonActivity;
import com.example.flyingfish.mytest.json.activity.GsonActivity;
import com.example.flyingfish.mytest.json.activity.NativeJsonParseActivity;
import com.example.flyingfish.mytest.okhttp.activity.OKHttpActivity;
import com.example.flyingfish.mytest.CommonFrameFragmentAdapter;
import com.example.flyingfish.mytest.base.BaseFragment;
import com.example.flyingfish.mytest.xutils3.activity.XUtils3Activity;

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
                if(data.toLowerCase().equals("okhttp")){  //toLowerCase()转化为小写
                    //点击条目跳转到okhttp界面
                    Intent intent = new Intent(mContext,OKHttpActivity.class);
                    startActivity(intent);
                }else if (data.toLowerCase().equals("nativejsonparse")){
                    //点击条目跳转到json解析页面
                    Intent intent = new Intent(mContext,NativeJsonParseActivity.class);
                    startActivity(intent);
                }else if (data.toLowerCase().equals("gson")){
                    //点击条目跳转到Gson解析界面
                    Intent intent = new Intent(mContext, GsonActivity.class);
                    startActivity(intent);
                }else if (data.toLowerCase().equals("fastjson")){
                    //点击条目跳转到FastJson解析页面
                    Intent intent = new Intent(mContext, FastJsonActivity.class);
                    startActivity(intent);
                }else if (data.toLowerCase().equals("xutils3")){
                    //点击条目跳转到XUtils3界面
                    Intent intent = new Intent(mContext, XUtils3Activity.class);
                    startActivity(intent);
                }
                Toast.makeText(mContext,"data == "+data,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        //准备数据
        datas = new String[]{"OKHttp", "NativeJsonParse", "Gson", "FastJson", "xUtils3", "Retrofit2", "Fresco", "Glide", "GreenDao", "RxJava"};
        //设置适配器
        adapter = new CommonFrameFragmentAdapter(mContext, datas);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
