package com.bwie.aizhonghui.mymvp.model;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.common.Api;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class SearchModel {
    private ISearch iSearch;
    private Activity context;

    public SearchModel(Activity context) {
        this.context = context;
    }

    public void search(Map<String,String> map){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
        }
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.SEARCH_API).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iSearch.SearchError(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iSearch.SearchSuccess("s",result);
                            }
                        });

                }
            }
        });
    }
    public  void setiSearch(ISearch iSearch){
        this.iSearch=iSearch;
    }

    public interface  ISearch{
        void SearchSuccess(String code, String msg);
        void SearchFail(String code, String msg);
        void SearchError(Call call, IOException e);
    }
}
