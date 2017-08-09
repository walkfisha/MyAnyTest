package com.example.flyingfish.mytest.json.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.flyingfish.mytest.R;
import com.example.flyingfish.mytest.json.bean.TicketInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FastJsonActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.btn_json2javaobj)
    Button btnJson2javaobj;
    @BindView(R.id.btn_json2javalist)
    Button btnJson2javalist;
    @BindView(R.id.btn_fastjson_java2jsonobject)
    Button btnFastjsonJava2jsonobject;
    @BindView(R.id.btn_fastjson_javatojsonarray)
    Button btnFastjsonJavatojsonarray;
    @BindView(R.id.tv_fastjson_original)
    TextView tvFastjsonOriginal;
    @BindView(R.id.tv_fastjson_last)
    TextView tvFastjsonLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json);
        ButterKnife.bind(this);
        title.setText("FastJson解析");
    }

    @OnClick({R.id.btn_json2javaobj,R.id.btn_json2javalist,R.id.btn_fastjson_java2jsonobject,R.id.btn_fastjson_javatojsonarray})
    public void onClick(View view){
        switch (view.getId()){
            //将json格式的字符串{}转化为Java对象
            case R.id.btn_json2javaobj:
                jsonToJavaObjectByFastJson();
                break;
            //将json格式的字符串[]转化为Java对象的list
            case R.id.btn_json2javalist:
                jsonToJavaListByFastJson();
                break;
            //将Java对象转化为json字符串{}
            case R.id.btn_fastjson_java2jsonobject:
                javaToJsonObjectByFastJson();
                break;
            //将Java对象的list转化为json字符串[]
            case R.id.btn_fastjson_javatojsonarray:
                javaToJsonArrayByFastJson();
                break;
        }
    }

    //将Java对象的list转化为json字符串[]
    private void javaToJsonArrayByFastJson() {
        //创建或获取Java对象
        List<TicketInfo> tickets = new ArrayList<>();
        TicketInfo zhanlang = new TicketInfo(1, "《战狼2》3D", 28.6, "hsiudfh8439r");
        TicketInfo zhanlangs = new TicketInfo(2, "《战狼2》4D", 48.5, "jdhfi37658dmfk");
        tickets.add(zhanlang);
        tickets.add(zhanlangs);
        //生成Json数据
        String jsonString = JSON.toJSONString(tickets);
        //展示数据
        tvFastjsonOriginal.setText(tickets.toString());
        tvFastjsonLast.setText(jsonString);
    }

    //将Java对象转化为json字符串{}
    private void javaToJsonObjectByFastJson() {
        //创建或获取Java对象
        TicketInfo ticketInfo = new TicketInfo(1, "战狼2", 28.6, "imagepath:723827");
        //生成Json数据
        String jsonString = JSON.toJSONString(ticketInfo);
        //展示数据
        tvFastjsonOriginal.setText(ticketInfo.toString());
        tvFastjsonLast.setText(jsonString);
    }

    //将json格式的字符串[]转化为Java对象的list
    private void jsonToJavaListByFastJson() {
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
        List<TicketInfo> ticketInfos = JSON.parseArray(json, TicketInfo.class);
        //展示数据
        tvFastjsonOriginal.setText(json);
        tvFastjsonLast.setText(ticketInfos.toString());
    }

    //将json格式的字符串{}转化为Java对象
    private void jsonToJavaObjectByFastJson() {
        //获取或创建json数据
        String json = "{\n" +
                "\t\"id\":2,\"name\":\"星际特工\",\n" +
                "\t\"price\":26.3,\n" +
                "\t\"imagePath\":\"http://img5.mtime.cn/mg/2017/03/30/110536.36501366.jpg\"\n" +
                "}\n";
        //解析json数据
        TicketInfo ticketInfo = JSON.parseObject(json, TicketInfo.class);
        //展示数据
        tvFastjsonOriginal.setText(json);
        tvFastjsonLast.setText(ticketInfo.toString());
    }
}
