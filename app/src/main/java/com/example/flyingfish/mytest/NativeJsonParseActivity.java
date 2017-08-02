package com.example.flyingfish.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Flyingfish on 2017/7/26.
 * 手动json解析界面
 * 1.将json格式的字符串转换为Java对象
 * 2.将json格式的字符串[]转换为Java对象的List
 * 3.复杂json数据解析
 * 4.特殊json数据解析
 */

public class NativeJsonParseActivity extends Activity {
    @BindView(R.id.btn_native2javaobj)
    Button btnNative2javaobj;
    @BindView(R.id.btn_native2javalist)
    Button btnNative2javalist;
    @BindView(R.id.btn_native_complex)
    Button btnNativeComplex;
    @BindView(R.id.btn_native_special)
    Button btnNativeSpecial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativejson_parse);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_native2javaobj,R.id.btn_native2javalist,R.id.btn_native_complex,R.id.btn_native_special})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_native2javaobj:
                break;
            case R.id.btn_native2javalist:
                break;
            case R.id.btn_native_complex:
                break;
            case R.id.btn_native_special:
                break;
        }
    }
}
