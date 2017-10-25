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

public class ShopCarUpdateModel {
    private IShopCarUpdate iShopCarUpdate;
    private Activity context;

    public ShopCarUpdateModel(Activity context) {
        this.context = context;
    }

    public void shopcarUpdate(Map<String,String> map){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.SHOP_CAR_UPDATE).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iShopCarUpdate.ShopCarUpdateError(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iShopCarUpdate.ShopCarUpdateSuccess("s",result);
                            }
                        });

                }
            }
        });
    }
    public  void setiShopCarUpdate(IShopCarUpdate iShopCarUpdate){
        this.iShopCarUpdate=iShopCarUpdate;
    }

    public interface  IShopCarUpdate{
        void ShopCarUpdateSuccess(String code, String msg);
        void ShopCarUpdateFail(String code, String msg);
        void ShopCarUpdateError(Call call, IOException e);
    }
}
