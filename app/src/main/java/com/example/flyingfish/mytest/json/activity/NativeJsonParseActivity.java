package com.example.flyingfish.mytest.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.json.bean.TicketInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Flyingfish on 2017/7/26.
 * 手动json解析界面
 * 1.将json格式的字符串{}转换为Java对象
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
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_native_original)
    TextView tvNativeOriginal;
    @BindView(R.id.tv_native_last)
    TextView tvNativeLast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nativejson_parse);
        ButterKnife.bind(this);
        //修改标题
        title.setText("手动Json解析");
    }

    @OnClick({R.id.btn_native2javaobj, R.id.btn_native2javalist, R.id.btn_native_complex, R.id.btn_native_special})
    public void onClick(View view) {
        switch (view.getId()) {
            //将json格式的字符串{}转换为Java对象
            case R.id.btn_native2javaobj:
                jsonToObjectByNative();
                break;
            //将json格式的字符串[]转换为Java对象的List
            case R.id.btn_native2javalist:
                jsonToJavaListByNative();
                break;
            //复杂json数据解析
            case R.id.btn_native_complex:
                break;
            //特殊json数据解析
            case R.id.btn_native_special:
                break;
        }
    }
    //将json格式的字符串[]转换为Java对象的List
    private void jsonToJavaListByNative() {
        //获取或创建json数据
        String json = "[\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"imagePath\": \"http://img5.mtime.cn/mg/2017/03/30/110536.36501366.jpg\",\n" +
                "        \"name\": \"星际特工\",\n" +
                "        \"price\": 26.3\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 3,\n" +
                "        \"imagePath\": \"http://img5.mtime.cn/mg/2017/03/29/170431.30153600.jpg\",\n" +
                "        \"name\": \"蜘蛛侠：英雄归来\",\n" +
                "        \"price\": 28.3\n" +
                "    }\n" +
                "]";
        List<TicketInfo> ticketInfos = new ArrayList<>();
        //解析json数据
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0;i < jsonArray.length();i++ ){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.optInt("id");
                String name = jsonObject.optString("name");
                double price = jsonObject.optDouble("price");
                String imagePath = jsonObject.optString("imagePath");

                //封装Java对象
                TicketInfo ticketInfo = new TicketInfo(id, name, price, imagePath);
                ticketInfos.add(ticketInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //显示json数据
        tvNativeOriginal.setText(json);
        tvNativeLast.setText(ticketInfos.toString());
    }

    //将json格式的字符串{}转换为Java对象
    private void jsonToObjectByNative() {
        //获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2,\"name\":\"星际特工\",\n" +
                "\t\"price\":26.3,\n" +
                "\t\"imagePath\":\"http://img5.mtime.cn/mg/2017/03/30/110536.36501366.jpg\"\n" +
                "}\n";

        TicketInfo ticketInfo = null;
        //解析json
        try {
            JSONObject jsonObject = new JSONObject(json);
//            int id = jsonObject.getInt("id");  //用这个方法可能会抛空指针异常
            int id = jsonObject.optInt("id");
            String name = jsonObject.optString("name");
            double price = jsonObject.optDouble("price");
            String imagePath = jsonObject.optString("imagePath");

            ticketInfo = new TicketInfo(id, name, price, imagePath);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示json数据
        tvNativeOriginal.setText(json);
        tvNativeLast.setText(ticketInfo.toString());
    }
}
