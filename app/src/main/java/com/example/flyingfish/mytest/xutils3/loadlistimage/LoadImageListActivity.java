package com.example.flyingfish.mytest.xutils3.loadlistimage;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.flyingfish.mytest.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@ContentView(R.layout.activity_load_image_list)
public class LoadImageListActivity extends AppCompatActivity {

    @ViewInject(R.id.listView)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_load_image_list);
        x.view().inject(this);

        ImageAdapter adapter = new ImageAdapter(this);
        listView.setAdapter(adapter);

    }

    public class ImageAdapter extends BaseAdapter {

        private Context context;
        private List<String> items = new ArrayList<>();

        public ImageAdapter(Context context) {
            this.context = context;
            initData();
        }

        private void initData() {
            items.add("http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg");
            items.add("http://img5.mtime.cn/mg/2017/03/30/110536.36501366.jpg");
            items.add("http://img5.mtime.cn/mg/2017/03/29/170431.30153600.jpg");
            items.add("http://img5.mtime.cn/mg/2017/06/15/175025.74431884.jpg");
            items.add("http://img5.mtime.cn/mg/2017/07/23/173127.61663169.jpg");
            items.add("http://img5.mtime.cn/mg/2017/07/23/173925.91198625.jpg");
            items.add("http://img5.mtime.cn/mg/2017/07/21/112020.41941501.jpg");
            items.add("http://img5.mtime.cn/mg/2016/12/20/152243.84093718.jpg");
            items.add("http://img5.mtime.cn/mg/2016/12/15/174641.37616965.jpg");
            items.add("http://img5.mtime.cn/mg/2017/07/21/112020.41941501.jpg");
            items.add("http://img5.mtime.cn/mg/2016/12/20/152243.84093718.jpg");
            items.add("http://img5.mtime.cn/mg/2016/12/15/174641.37616965.jpg");
        }


        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.image_adapter, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            /**
             * 加载单张图片
             */
            ImageOptions imageOptions = new ImageOptions.Builder()
                    .setRadius(DensityUtil.dip2px(5))
                    .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                    .setLoadingDrawableId(R.drawable.haha)
                    .setFailureDrawableId(R.drawable.haha)
                    .build();
            x.image().bind(holder.imageView,items.get(i),imageOptions);
            return view;
        }

        class ViewHolder {
            @BindView(R.id.imageView)
            ImageView imageView;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
