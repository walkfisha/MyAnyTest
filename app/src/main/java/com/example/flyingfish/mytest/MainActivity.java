package com.example.flyingfish.mytest;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentTransaction;
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
    //选中的Fragment对应的位置
    private int position;
    //上次切换的Fragment
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    private void setListener() {
        rgMian.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //默认选中常用框架按钮
        rgMian.check(R.id.rb_common_frame);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i){
                case R.id.rb_common_frame://常用框架
                    position = 0;
                    break;
                case R.id.rb_custom://自定义
                    position = 1;
                    break;
                case R.id.rb_thirdparty://第三方
                    position = 2;
                    break;
                case R.id.rb_other://其他
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFragment(mContent,to);
        }
    }

    /**
     *
     * @param from  刚显示的Fragment,马上就要被隐藏了
     * @param to  马上要切换到的fragment,显示出来
     */
    private void switchFragment(Fragment from,Fragment to) {
        if (from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()){
                //to没有被添加
                //隐藏from
                if (from != null){
                    ft.hide(from);
                }
                //添加to
                if (to != null){
                    ft.add(R.id.fl_content,to).commit();
                }
            }else {
                //to已经被添加
                //隐藏from
                if (from != null){
                    ft.hide(from);
                }
                //显示to
                if (to != null){
                    ft.show(to).commit();
                }
            }
        }
    }

//    private void switchFragment(BaseFragment fragment) {
//        //1.得到FragmentManager
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content,fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    /**
     * 根据位置得到对应的Fragment
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
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
    }


}
