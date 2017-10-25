package com.bwie.aizhonghui.mymvp.model;

import android.app.Activity;
import android.content.Context;

import com.bwie.aizhonghui.mymvp.common.Api;
import com.bwie.aizhonghui.mymvp.view.LoginView;

import org.json.JSONException;
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

public class LoginModel {
    private ILogin iLogin;
    private Activity context;

    public LoginModel(Activity context) {
        this.context = context;
    }

    public void reg(String mobile, String pwd){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.REG_API).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    String result = response.body().string();
                    System.out.println("======"+result);
//                    try {
//                        JSONObject obj = new JSONObject(result);
//                        final String msg = obj.optString("msg");
//                        final String code = obj.optString("code");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.loginSuccess("666","666");
                            }
                        });
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
        });
    }
    public void login(String mobile, String pwd){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pwd);
        FormBody body=builder.build();

        final Request request=new Request.Builder().post(body).url(Api.LOGIN_API).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             if(response!=null&&response.isSuccessful()){
                 final String result = response.body().string();
                 System.out.println("======"+result);
                 try {
                     JSONObject obj = new JSONObject(result);
                     final String msg = obj.optString("msg");
                     final String code = obj.optString("code");
                     context.runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             iLogin.loginSuccess(code,result);
                         }
                     });

                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
            }
        });
    }
    public void touimg(String mobile, File file){
        Map<String,Object> params=new HashMap<>();
        params.put("uid",mobile);
        OkHttpClient okHttpClient=new OkHttpClient();
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/*"),file);
        builder.addFormDataPart("file",file.getName(),requestBody);
        if(params!=null){
            for (Map.Entry entry : params.entrySet()) {
                builder.addFormDataPart(valueOf(entry.getKey()), valueOf(entry.getValue()));
            }
            Request request=new Request.Builder().url(Api.IMG_API).post(builder.build()).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("=======失败了===");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    System.out.println("=======成功了===");
                }
            });
        }
    }
    public void updatename(String uid,String nickname){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("uid",uid);
        builder.add("nickname",nickname);
        FormBody body=builder.build();
        final Request request=new Request.Builder().post(body).url(Api.UPDATE_NAME).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        final String code = obj.optString("code");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.updateSuccess(code,result);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void userget(String uid){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("uid",uid);
        FormBody body=builder.build();
        final Request request=new Request.Builder().post(body).url(Api.GET_USER).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        final String code = obj.optString("code");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.loginSuccess(code,result);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void syvpTou(){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().get().url(Api.IMG_VPTOU).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        final String msg = obj.optString("msg");
                        final String code = obj.optString("code");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.loginSuccess(code,result);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void Flfl(){
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().get().url(Api.IMG_FL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        final String msg = obj.optString("msg");
                        final String code = obj.optString("code");
                        System.out.println("================"+result);
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.loginSuccess(code,result);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public void FlFra(String cid){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("cid",cid);
        FormBody body=builder.build();
        final Request request=new Request.Builder().post(body).url(Api.FL_FRAApi).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLogin.failure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        final String code = obj.optString("code");
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                iLogin.loginSuccess(code,result);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    public  void setLigin(ILogin iLogin){
        this.iLogin=iLogin;
    }

    public interface  ILogin{
        void loginSuccess(String code,String msg);
        void updateSuccess(String code,String msg);
        void loginFail(String code,String msg);
        void failure(Call call,IOException e);
    }
}
