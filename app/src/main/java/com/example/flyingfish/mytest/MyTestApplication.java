package com.example.flyingfish.mytest;

import android.app.Application;

import org.xutils.x;


/**
 * Created by Flyingfish on 2017/8/10.
 */

public class MyTestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //xutils3初始化
        x.Ext.init(this);
        // 是否输出debug日志, 开启debug会影响性能.
        x.Ext.setDebug(true);
    }
}
