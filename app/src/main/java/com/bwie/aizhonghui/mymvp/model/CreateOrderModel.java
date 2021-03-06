package com.bwie.aizhonghui.mymvp.model;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.common.Api;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class CreateOrderModel {
    private ICreateOrder iCreateOrder;
    private Activity context;

    public CreateOrderModel(Activity context) {
        this.context = context;
    }

    public void createorderr(Map<String,String> map){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.MONEY_CREATEORDER_API).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iCreateOrder.CreateOrderError(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("===创建成功==="+result);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iCreateOrder.CreateOrderSuccess("s",result);
                            }
                        });

                }
            }
        });
    }
    public  void setiCreateOrder(ICreateOrder iCreateOrder){
        this.iCreateOrder=iCreateOrder;
    }

    public interface  ICreateOrder{
        void CreateOrderSuccess(String code, String msg);
        void CreateOrderFail(String code, String msg);
        void CreateOrderError(Call call, IOException e);
    }
}
