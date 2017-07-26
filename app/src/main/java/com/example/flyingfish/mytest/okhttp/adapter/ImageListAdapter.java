package com.example.flyingfish.mytest.okhttp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.okhttp.domian.DataBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Flyingfish on 2017/7/26.
 */

public class ImageListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DataBean.ItemData> datas;

    public ImageListAdapter(Context mContext, List<DataBean.ItemData> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_okhttp_imagelist, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        //根据位置得到数据
        DataBean.ItemData itemData = datas.get(i);
        holder.tvName.setText(itemData.getMovieName());
        holder.tvDesc.setText(itemData.getVideoTitle());
        Log.e("photourl",itemData.getCoverImg());

        //在列表中使用okhttp_utils请求图片
        OkHttpUtils
                .get()//
                .url(itemData.getCoverImg())//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id) {
                        Log.e("TAG", "onResponse：complete");
                        holder.ivIcon.setImageBitmap(bitmap);
                    }
                });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.rl_image)
        RelativeLayout rlImage;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
