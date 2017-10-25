package com.bwie.aizhonghui.mymvp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

public class SzActivity extends AppCompatActivity implements View.OnClickListener,LoginView {

    private ImageView sztou;
    private ImageView szjian;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;
    private TextView tvuser;
    private TextView tvuserx;
    private RelativeLayout reszxq;
    private Button sztui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sz);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        String login = sp.getString("LOGIN", null);
        System.out.println("=================== "+login);
        if(login!=null){
            loginPresenter.get_user(login+"");
        }else{
            System.out.println("===================还没登录过");
        }
    }

    private void initData() {
        loginPresenter=new LoginPresenter(SzActivity.this,this);
    }

    private void initView() {
        sztou = (ImageView) findViewById(R.id.iv_sz_tou);
        szjian = (ImageView) findViewById(R.id.iv_sz_jian);
        tvuser = (TextView) findViewById(R.id.tv_sz_user);
        tvuserx = (TextView) findViewById(R.id.tv_sz_userx);
        reszxq = (RelativeLayout) findViewById(R.id.re_sz_xq);
        sztui = (Button) findViewById(R.id.btn_sz_tui);
        szjian.setOnClickListener(this);
        reszxq.setOnClickListener(this);
        sztui.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_sz_xq:
                startActivity(new Intent(SzActivity.this,XqActivity.class));
                break;
            case R.id.btn_sz_tui:
                AlertDialog.Builder ab=new AlertDialog.Builder(this);
                ab.setMessage("确定退出登录");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sp.edit().clear().commit();
                    }
                });
                ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ab.create().show();
                break;
            case R.id.iv_sz_jian:
                finish();
                break;
        }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void loginSuccess(String code, String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONObject dataj = obj.optJSONObject("data");
            String icon = dataj.optString("icon");
            String username = dataj.optString("nickname");
            System.out.println("===="+icon);
            System.out.println("====="+username);
            ImageLoader.getInstance().displayImage(icon,sztou);
            tvuser.setText(username);
            tvuserx.setText(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
}
