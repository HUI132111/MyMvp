package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoginView{
    private EditText et_mobile;
    private EditText et_pass;
    private Button login;
    private ImageView ivx;
    private LoginPresenter loginPresenter;
    private TextView zc;
    private int REQUESTH=1003;
    public SharedPreferences sp;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        initView();
        initData();
    }

    private void initData() {
        loginPresenter=new LoginPresenter(MainActivity.this,this);
    }

    private void initView() {
        et_mobile= (EditText) findViewById(R.id.et_mobile);
        et_pass= (EditText) findViewById(R.id.et_pass);
        zc = (TextView) findViewById(R.id.tv_zc);
        login= (Button) findViewById(R.id.btn_login);
        ivx= (ImageView) findViewById(R.id.iv_x);
        login.setOnClickListener(this);
        zc.setOnClickListener(this);
        ivx.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btn_login:
               loginPresenter.login(et_mobile.getText().toString(),et_pass.getText().toString());
               break;
           case R.id.tv_zc:
               loginPresenter.reg(et_mobile.getText().toString(),et_pass.getText().toString());
               break;
           case R.id.iv_x:
               finish();
               break;
       }
    }

    @Override
    public void showProgressbar() {
         //progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {

        // progress.setVisibility(View.GONE);
    }

    @Override
    public void nameError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String code, String msg) {
        System.out.println("======"+msg);
        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        try {
            JSONObject obj = new JSONObject(msg);
            JSONObject dataj = obj.optJSONObject("data");
            String icon = dataj.optString("icon");
            String username = dataj.optString("nickname");
            uid = dataj.optInt("uid");
            System.out.println("===="+icon);
            System.out.println("====="+username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sp.edit().putString("LOGIN",uid+"").commit();
        finish();
    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    @Override
    public void loginFail(String code, String msg) {
        Toast.makeText(this,"登录失败"+msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(Call call, IOException e) {
        Toast.makeText(this,"对于请求会失败这事儿，就不劳拆穿了",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
