package com.example.flyingfish.mytest.xutils3.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.xutils3.annotation.FragmentXUtils3Activity;
import com.example.flyingfish.mytest.xutils3.loadlistimage.LoadImageListActivity;
import com.example.flyingfish.mytest.xutils3.net.Xutils3NetActivity;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
@ContentView(R.layout.activity_xutils3)
public class XUtils3Activity extends AppCompatActivity {

    @ViewInject(R.id.title)
    private TextView title;

    @ViewInject(R.id.iv_icon)
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xutils3);
        x.view().inject(this);

        title.setText("xUtils3的使用");

    }

    @Event(value = {R.id.btn_xutils_annotation,R.id.btn_xutils_net,R.id.btn_xutils_load_image,R.id.btn_xutils_load_imagelist})
    private void getEvent(View view){
        switch (view.getId()){
            case R.id.btn_xutils_annotation:
                Intent intent = new Intent(this, FragmentXUtils3Activity.class);
                Log.e("被点击","成功");
                startActivity(intent);
                break;
            case R.id.btn_xutils_net:
                intent = new Intent(this, Xutils3NetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_xutils_load_image:
                getImage();
                break;
            case R.id.btn_xutils_load_imagelist:
                intent = new Intent(this, LoadImageListActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 加载单张图片
     */
    private void getImage(){
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(5))
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.haha)
                .setFailureDrawableId(R.drawable.haha)
                .build();
        x.image().bind(icon,"http://img5.mtime.cn/mg/2017/08/04/165113.24946335.jpg",imageOptions);
    }

}
