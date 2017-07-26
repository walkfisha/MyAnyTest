package com.example.flyingfish.mytest.okhttp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.okhttp.adapter.ImageListAdapter;
import com.example.flyingfish.mytest.okhttp.domian.DataBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Flyingfish on 2017/7/26.
 */

public class OKHttpListActivity extends Activity {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_noData)
    TextView tvNoData;

    private ImageListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_list);
        ButterKnife.bind(this);
        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        OkHttpUtils
                .post()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        tvNoData.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tvNoData.setVisibility(View.GONE);
                        switch (id) {
                            case 100:
                                Toast.makeText(OKHttpListActivity.this, "http", Toast.LENGTH_SHORT).show();
                                break;
                            case 101:
                                Toast.makeText(OKHttpListActivity.this, "https", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        if (response != null){
                            //解析数据并展示数据
                            processData(response);
                        }
                    }
                });
    }

    /**
     * 解析和显示数据
     * @param json
     */
    private void processData(String json) {
        //解析数据
        DataBean dataBean = parsedJson(json);
        List<DataBean.ItemData> datas = dataBean.getTrailers();
        if (datas != null && datas.size()>0){
            //有数据,则设置适配器
            tvNoData.setVisibility(View.GONE);
            adapter = new ImageListAdapter(OKHttpListActivity.this,datas);
            listView.setAdapter(adapter);
        }else {
            //没有数据
            tvNoData.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);
    }

    /**
     * 解析json数据
     * @param response
     * @return
     */
    private DataBean parsedJson(String response){
        DataBean dataBean = new DataBean();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArry = jsonObject.optJSONArray("trailers");
            if (jsonArry != null && jsonArry.length()>0){
                List<DataBean.ItemData> trailers = new ArrayList<>();
                dataBean.setTrailers(trailers);
                for (int i = 0; i < jsonArry.length(); i++){
                    JSONObject jsonObjectItem = (JSONObject) jsonArry.get(i);
                    if (jsonObjectItem != null){
                        DataBean.ItemData mediaItem = new DataBean.ItemData();
                        String movieName = jsonObjectItem.optString("movieName");//name
                        mediaItem.setMovieName(movieName);
                        String videoTitle = jsonObjectItem.optString("videoTitle");//desc
                        mediaItem.setVideoTitle(videoTitle);
                        String imageUrl = jsonObjectItem.optString("coverImg");//imageUrl
                        mediaItem.setCoverImg(imageUrl);
                        String hightUrl = jsonObjectItem.optString("hightUrl");//data
                        mediaItem.setHightUrl(hightUrl);

                        //把数据添加到集合
                        trailers.add(mediaItem);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataBean;
    }
}
