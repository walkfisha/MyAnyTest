package com.example.flyingfish.mytest.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.flyingfish.mytest.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 其他fragment
 */
public class OtherFragment extends BaseFragment {


    private static final String TAG = OtherFragment.class.getSimpleName();
    private TextView textView;
//    public CommonFrameFragment() {
//        // Required empty public constructor
//    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_common_frame, container, false);
//    }

    @Override
    protected View initView() {
        Log.e(TAG,"其他fragment页面被初始化了。。。。");
        textView = new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG,"其他fragment数据被初始化了。。。。。");
        textView.setText("其他页面");
    }
}
