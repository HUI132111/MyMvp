package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Zf_SuccessActivity extends AppCompatActivity {

    private String uid;
    private String orderld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zf__success);
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        orderld = intent.getStringExtra("orderld");
        initupdatadd(uid,orderld);
    }
    private void initupdatadd(String uid, String orderld) {
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("uid",uid);
        builder.add("status",1+"");
        builder.add("orderId",orderld);
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url("http://120.27.23.105/product/updateOrder").build();
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

                        }
                    });

                }
            }
        });
    }
}
