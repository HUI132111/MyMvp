package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bawei.pay.PayDemoActivity;
import com.bwie.aizhonghui.mymvp.Adapter.MyAnyDdAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Any_DD_bean;
import com.bwie.aizhonghui.mymvp.common.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyDdActivity extends AppCompatActivity {

    private ListView lvdd;
    private SharedPreferences sp;
    private String loginuid;
    private List<Any_DD_bean.DataBean> data;
    private MyAnyDdAdapter maa;
    private ImageView ivdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dd);
        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initsp();
    }

    private void initsp() {
        loginuid = sp.getString("LOGIN", null);
        if(loginuid!=null){
            initOkhttp(loginuid);
        }


    }

    public void initOkhttp(String uid){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("uid",uid);
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url("http://120.27.23.105/product/getOrders").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("===hhhh==="+result);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Gson gson=new Gson();
                            Any_DD_bean any_dd_bean = gson.fromJson(result, Any_DD_bean.class);
                            data = any_dd_bean.getData();
                            initSetData();
                        }
                    });

                }
            }
        });
    }

    private void initSetData() {
        maa=new MyAnyDdAdapter(MyDdActivity.this,data);
        lvdd.setAdapter(maa);
        lvdd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MyDdActivity.this, PayDemoActivity.class);
                intent.putExtra("uid",loginuid);
                intent.putExtra("orderld",data.get(i).getOrderid()+"");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        lvdd = (ListView) findViewById(R.id.lv_My_dd);
        ivdd = (ImageView) findViewById(R.id.iv_mydd_jian);
        ivdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        
    }
}
