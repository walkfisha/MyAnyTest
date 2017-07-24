package com.example.flyingfish.mytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Flyingfish on 2017/7/24.
 * okhttp
 */

public class OKHttpActivity extends Activity {
    //get请求
    private static final int GET = 1;
    //post请求
    private static final int POST = 2;
    @BindView(R.id.bt_get_post)
    Button btGetPost;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.title)
    TextView title;

    private OkHttpClient client = new OkHttpClient();
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case GET:
                    tvResult.setText((String) msg.obj);
                    break;
                case POST:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("OKHttp");
    }

    /**
     * 使用原生的OKhttp请求网络数据：get和post
     */
    @OnClick(R.id.bt_get_post)
    public void setBtGetPost() {
        getDataFromGet();
    }

    private void getDataFromGet() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("https://translate.google.cn/");
                    Log.e("TAG",result);
                    Message msg = Message.obtain();
                    msg.what = GET;
                    msg.obj = result;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * get请求
     * @param url  网络链接
     * @return
     * @throws IOException
     */
    private String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
