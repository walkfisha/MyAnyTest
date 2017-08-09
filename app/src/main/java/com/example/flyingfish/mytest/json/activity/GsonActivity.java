package com.example.flyingfish.mytest.json.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.json.bean.TicketInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GsonActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btn_json2javaobj)
    Button btnJson2javaobj;
    @BindView(R.id.btn_json2javalist)
    Button btnJson2javalist;
    @BindView(R.id.btn_gson_java2jsonobject)
    Button btnGsonJava2jsonobject;
    @BindView(R.id.btn_gson_javatojsonarray)
    Button btnGsonJavatojsonarray;
    @BindView(R.id.tv_gson_original)
    TextView tvGsonOriginal;
    @BindView(R.id.tv_gson_last)
    TextView tvGsonLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);
        ButterKnife.bind(this);
        title.setText("Gson解析");
    }

    @OnClick({R.id.btn_json2javaobj,R.id.btn_json2javalist,R.id.btn_gson_java2jsonobject,R.id.btn_gson_javatojsonarray})
    public void onClick(View view){
        switch (view.getId()){
            //将json格式的字符串{}转化为Java对象
            case R.id.btn_json2javaobj:
                jsonToJavaObjectByGson();
                break;
            //将json格式的字符串[]转化为Java对象的list
            case R.id.btn_json2javalist:
                jsonToJavaListByGson();
                break;
            //将Java对象转化为json字符串{}
            case R.id.btn_gson_java2jsonobject:
                javaToJsonObjectByGson();
                break;
            //将Java对象的list转化为json字符串[]
            case R.id.btn_gson_javatojsonarray:
                JavaListToJsonArrayByGson();
                break;
        }
    }

    //将Java对象的list转化为json字符串[]
    private void JavaListToJsonArrayByGson() {
        //创建或获取Java对象
        List<TicketInfo> tickets = new ArrayList<>();
        TicketInfo zhanlang = new TicketInfo(1, "《战狼2》3D", 28.6, "hsiudfh8439r");
        TicketInfo zhanlangs = new TicketInfo(2, "《战狼2》4D", 48.5, "jdhfi37658dmfk");
        tickets.add(zhanlang);
        tickets.add(zhanlangs);
        //生成Json数据
        Gson gson = new Gson();
        String json = gson.toJson(tickets);
        //展示数据
        tvGsonOriginal.setText(tickets.toString());
        tvGsonLast.setText(json);
    }

    //将Java对象转化为json字符串{}
    private void javaToJsonObjectByGson() {
        //创建或获取Java对象
        TicketInfo ticketInfo = new TicketInfo(1, "战狼2", 28.6, "imagepath:723827");
        //生成Json数据
        Gson gson = new Gson();
        String json = gson.toJson(ticketInfo);
        //展示数据
        tvGsonOriginal.setText(ticketInfo.toString());
        tvGsonLast.setText(json);
    }

    //将json格式的字符串[]转化为Java对象的list
    private void jsonToJavaListByGson() {
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
        //解析json数据
        Gson gson = new Gson();
        List<TicketInfo> tickets = gson.fromJson(json, new TypeToken<List<TicketInfo>>() {
        }.getType());
        //展示数据
        tvGsonOriginal.setText(json);
        tvGsonLast.setText(tickets.toString());
    }

    //将json格式的字符串{}转化为Java对象
    private void jsonToJavaObjectByGson() {
        //获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2,\"name\":\"星际特工\",\n" +
                "\t\"price\":26.3,\n" +
                "\t\"imagePath\":\"http://img5.mtime.cn/mg/2017/03/30/110536.36501366.jpg\"\n" +
                "}\n";
        //解析json数据
        Gson gson = new Gson();
        TicketInfo ticketInfo = gson.fromJson(json, TicketInfo.class);
        //展示数据
        tvGsonOriginal.setText(json);
        tvGsonLast.setText(ticketInfo.toString());
    }
}
