package com.example.flyingfish.mytest.xutils3.net;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flyingfish.mytest.R;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_xutils3_net)
public class Xutils3NetActivity extends AppCompatActivity {

    @ViewInject(R.id.tv_result)
    private TextView textView;

    @ViewInject(R.id.title)
    private TextView title;

    @ViewInject(R.id.progressBar)
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils3_net);
        x.view().inject(this);
        title.setText("Xutils3的网络模块");
    }

    @Event(value = {R.id.btn_request,R.id.btn_bigfile_upload,R.id.btn_file_download})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_request:
                getAndPostNet();
                break;
            case R.id.btn_bigfile_upload:
//                uploadFile();
                Toast.makeText(this,"大文件上传",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.btn_file_download:
                downloadFile();
                break;
        }
    }

    /**
     * 文件上传
     */
    private void uploadFile() {
        RequestParams params = new RequestParams("上传地址");
        //以表单方式上传
        params.setMultipart(true);
        //设置上传文件的路径
        params.addBodyParameter("File",new File(Environment.getExternalStorageDirectory()+"/downloadmovies/blove1.mp4"),null,"upload.mp4");
        x.http().post(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法,并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG","onSuccess=="+file.toString());
                Toast.makeText(Xutils3NetActivity.this,"onSuccess=="+file.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","onError=="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","onCancelled=="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG","onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG","onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG","onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
                Log.e("TAG","onLoading=="+current+"/"+total+"isDownloading=="+isDownloading);
            }
        });
    }

    private void downloadFile() {
        RequestParams params = new RequestParams("http://vfx.mtime.cn/Video/2017/08/02/mp4/170802074323236656.mp4");
        //设置保存数据
        params.setSaveFilePath(Environment.getExternalStorageDirectory()+"/downloadmovies/blove1.mp4");
        //设置是否可以立即取消下载
        params.setCancelFast(true);
        //设置是否自动根据头信息命名
        params.setAutoRename(false);
        //设置断点续传
        params.setAutoResume(true);
        //自定义线程池，有效的值范围[1,3],设置为 3 时，可能阻塞图片加载
        params.setExecutor(new PriorityExecutor(3,true));
        x.http().get(params, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法,并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG","onSuccess=="+file.toString());
                Toast.makeText(Xutils3NetActivity.this,"onSuccess=="+file.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","onError=="+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","onCancelled=="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG","onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG","onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG","onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
                Log.e("TAG","onLoading=="+current+"/"+total+"isDownloading=="+isDownloading);
            }
        });
    }

    private void getAndPostNet(){
        //1.get请求
        //2.post请求   只需把x.http().get() 改为x.http().post()即可
        RequestParams params = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("TAG","Xutils3联网请求成功=="+result);
                textView.setText("get请求的结果----------"+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG","Xutils3联网请求失败=="+ex.getMessage());
                textView.setText("Xutils3联网请求失败----------"+ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG","onCancelled=="+cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG","onCancelled==");
            }
        });
    }
}
