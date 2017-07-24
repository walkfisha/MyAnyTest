package com.example.flyingfish.mytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.flyingfish.mytest.base.BaseFragment;
import com.example.flyingfish.mytest.fragment.CommonFrameFragment;
import com.example.flyingfish.mytest.fragment.CustomFragment;
import com.example.flyingfish.mytest.fragment.OtherFragment;
import com.example.flyingfish.mytest.fragment.ThirdPartyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 */
public class MainActivity extends FragmentActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_common_frame)
    RadioButton rbCommonFrame;
    @BindView(R.id.rb_thirdparty)
    RadioButton rbThirdparty;
    @BindView(R.id.rb_custom)
    RadioButton rbCustom;
    @BindView(R.id.rb_other)
    RadioButton rbOther;
    @BindView(R.id.rg_mian)
    RadioGroup rgMian;

    private List<BaseFragment> mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();

    }

    private void initFragment() {
        mBaseFragment  = new ArrayList<>();
        mBaseFragment.add(new CommonFrameFragment());
        mBaseFragment.add(new ThirdPartyFragment());
        mBaseFragment.add(new CustomFragment());
        mBaseFragment.add(new OtherFragment());
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //默认选中常用框架按钮
        rgMian.check(R.id.rb_common_frame);
    }


}
