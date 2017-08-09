package com.example.flyingfish.mytest.json.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.json.bean.DataInfo;
import com.example.flyingfish.mytest.json.bean.FilmInfo;
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
                jsonToJavaOfComplex();
                break;
            //特殊json数据解析
            case R.id.btn_native_special:
                jsonToJavaOfSpecial();
                break;
        }
    }

    //特殊json数据解析
    private void jsonToJavaOfSpecial() {
        //获取或创建json数据
        String json = "{\n" +
                "    \"code\": 0,\n" +
                "    \"list\": {\n" +
                "        \"0\": {\n" +
                "            \"aid\": \"6008965\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 170,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:34\"\n" +
                "        },\n" +
                "        \"1\": {\n" +
                "            \"aid\": \"6008938\",\n" +
                "            \"author\": \"哔哩哔哩番剧\",\n" +
                "            \"coins\": 345,\n" +
                "            \"copyright\": \"Copy\",\n" +
                "            \"create\": \"2016-08-25 21:28\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //创建封装的Java对象
        FilmInfo filmInfo = new FilmInfo();
        //解析json数据
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            int code = jsonObject.optInt("code");
            JSONObject list = jsonObject.optJSONObject("list");
            //第一层封装
            filmInfo.setCode(code);
            List<FilmInfo.FilmBean> lists = new ArrayList<>();
            filmInfo.setList(lists);
            //第二层解析
            for (int i = 0;i < list.length(); i++){
                JSONObject object = list.optJSONObject(i + "");
                if (object != null){
                    String aid = object.optString("aid");
                    String author = object.optString("author");
                    int coins = object.optInt("coins");
                    String copyright = object.optString("copyright");
                    String create = object.optString("create");

                    //第二层封装
                    FilmInfo.FilmBean filmBean = new FilmInfo.FilmBean();
                    filmBean.setAid(aid);
                    filmBean.setAuthor(author);
                    filmBean.setCoins(coins);
                    filmBean.setCopyright(copyright);
                    filmBean.setCreate(create);
                    lists.add(filmBean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //显示json数据
        tvNativeOriginal.setText(json);
        tvNativeLast.setText(filmInfo.toString());
    }

    //复杂json数据解析
    private void jsonToJavaOfComplex() {
        //获取或创建json数据
        String json = "{\n" +
                "    \"data\": {\n" +
                "        \"count\": 5,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"id\": 45,\n" +
                "                \"title\": \"坚果\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 132,\n" +
                "                \"title\": \"炒货\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 166,\n" +
                "                \"title\": \"蜜饯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 185,\n" +
                "                \"title\": \"果脯\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 196,\n" +
                "                \"title\": \"礼盒\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"rs_code\": 1000,\n" +
                "    \"rs_msg\": \"success\"\n" +
                "}";
        DataInfo dataInfo = new DataInfo();
        //解析json数据
        try {
            JSONObject jsonObject = new JSONObject(json);
            //第一层解析
            JSONObject data = jsonObject.optJSONObject("data");
            int rs_code = jsonObject.optInt("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");
            //第一层封装
            DataInfo.DataBean dataBean = new DataInfo.DataBean();
            dataInfo.setData(dataBean);
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);

            //第二层解析
            int id = data.optInt("count");
            JSONArray items = data.optJSONArray("items");
            //第二层数据的封装
            dataBean.setCount(id);
            List<DataInfo.DataBean.ItemsBean> itemsBean = new ArrayList<>();
            dataBean.setItems(itemsBean);
            //第三层解析
            for (int i = 0;i < items.length();i++){
                JSONObject object = items.optJSONObject(i);
                if (object != null){
                    int id1 = object.optInt("id");
                    String title = object.optString("title");
                    //第三层数据的封装
                    DataInfo.DataBean.ItemsBean bean = new DataInfo.DataBean.ItemsBean();
                    bean.setId(id1);
                    bean.setTitle(title);
                    itemsBean.add(bean);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //显示json数据
        tvNativeOriginal.setText(json);
        tvNativeLast.setText(dataInfo.toString());
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
