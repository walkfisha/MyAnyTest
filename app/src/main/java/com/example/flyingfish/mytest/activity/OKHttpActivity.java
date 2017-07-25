package com.example.flyingfish.mytest.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flyingfish.mytest.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Flyingfish on 2017/7/24.
 * okhttp
 */

public class OKHttpActivity extends Activity {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    //get请求
    private static final int GET = 1;
    //post请求
    private static final int POST = 2;
    private static final String TAG = OKHttpActivity.class.getSimpleName();
    @BindView(R.id.bt_get_post)
    Button btGetPost;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bt_get_httputils)
    Button btGetHttputils;
    @BindView(R.id.bt_httputils_downloadfile)
    Button btHttputilsDownloadfile;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private OkHttpClient client = new OkHttpClient();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET:
                    tvResult.setText((String) msg.obj);
                    break;
                case POST:
                    tvResult.setText((String) msg.obj);
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
    @OnClick({R.id.bt_get_post, R.id.bt_get_httputils,R.id.bt_httputils_downloadfile})
    public void setBtGetPost(View view) {
        switch (view.getId()) {
            case R.id.bt_get_post:
                //get请求点击事件
//                getDataFromGet();
                //post请求点击事件
                getDataFromPost();
                break;
            case R.id.bt_get_httputils:
                //get请求点击事件
//                getDataFromOkhttpUtilsGet();
                //post请求点击事件
                getDataFromOKHttpUtilsPost();
                break;
            case R.id.bt_httputils_downloadfile:
                downloadFile();
                break;
        }

    }

    /**
     * 使用get请求网络数据
     */
    private void getDataFromGet() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("https://translate.google.cn/");
                    Log.e("TAG", result);
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
     * 使用post请求网络数据
     */
    private void getDataFromPost() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String result = get("https://translate.google.cn/");
                    Log.e("TAG", result);
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
     *
     * @param url 网络链接
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

    /**
     * post请求
     *
     * @param url
     * @param json
     * @return
     * @throws IOException
     */
    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 使用OKHttp-Utils的get方法请求网络文本数据
     */
    public void getDataFromOkhttpUtilsGet() {
        String url = "http://www.csdn.net/";
        OkHttpUtils
                .get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvResult.setText("OnError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvResult.setText("OnResponse:" + response);
                        switch (id) {
                            case 100:
                                Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                                break;
                            case 101:
                                Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 使用OKHttp-Utils的post方法请求网络文本数据
     */
    public void getDataFromOKHttpUtilsPost() {
        String url = "http://news.mtime.com/2017/07/25/1571748.html";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvResult.setText("OnError" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvResult.setText("OnResponse" + response);
                        switch (id) {
                            case 100:
                                Toast.makeText(OKHttpActivity.this, "http", Toast.LENGTH_SHORT).show();
                                break;
                            case 101:
                                Toast.makeText(OKHttpActivity.this, "https", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
    }

    /**
     * 使用OKhttp-Utils下载大文件
     */
    public void downloadFile() {
        String url = "http://pan.baidu.com/play/video#video/mp4";
        OkHttpUtils//
                .get()//
                .url(url)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "okhttp-utils-test.mp4")//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        mProgressBar.setProgress((int) (100 * progress));
                        Log.e(TAG, "inProgress :" + (int) (100 * progress));
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError :" + e.getMessage());
                    }

                    @Override
                    public void onResponse(File file, int id) {
                        Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                    }
                });
    }
}
