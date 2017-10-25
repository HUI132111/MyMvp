package com.bwie.aizhonghui.mymvp.model;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.common.Api;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class ShopXqModel {
    private IShopXq iShopXq;
    private Activity context;

    public ShopXqModel(Activity context) {
        this.context = context;
    }

    public void shopxq(String pid){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("pid",pid);
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.SHOP_XQ_API).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iShopXq.ShopxqError(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iShopXq.ShopxqSuccess("6",result);
                            }
                        });

                }
            }
        });
    }
    public  void setiShopxq(IShopXq iShopxq){
        this.iShopXq=iShopxq;
    }

    public interface  IShopXq{
        void ShopxqSuccess(String code, String msg);
        void ShopxqFail(String code, String msg);
        void ShopxqError(Call call, IOException e);
    }
}
