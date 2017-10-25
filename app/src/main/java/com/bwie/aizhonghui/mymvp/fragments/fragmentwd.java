package com.bwie.aizhonghui.mymvp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.aizhonghui.mymvp.MainActivity;
import com.bwie.aizhonghui.mymvp.MyDdActivity;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.SzActivity;
import com.bwie.aizhonghui.mymvp.XqActivity;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.CircleRoundImageView;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/3.
 */

public class fragmentwd extends Fragment implements View.OnClickListener,LoginView {
    private View view;
    private CircleRoundImageView ivuser;
    private TextView tvlogin;
    private SharedPreferences sp;
    private LoginPresenter loginPresenter;
    private ImageView ivsz;
    private TextView wddd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.item_wd,null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        loginPresenter=new LoginPresenter(getActivity(),this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sp=getActivity().getSharedPreferences("LOGIN",getActivity().MODE_PRIVATE);
        String login = sp.getString("LOGIN", null);
        System.out.println("=================== "+login);
         if(login!=null){
            loginPresenter.get_user(login+"");
         }else{
             System.out.println("===================还没登录过");
             ivuser.setImageResource(R.drawable.mytx);
             tvlogin.setText("登录/注册>");
         }


    }

    private void initView() {
         ivuser = view.findViewById(R.id.iv_user);
         tvlogin = view.findViewById(R.id.tv_login);
         ivsz = view.findViewById(R.id.iv_sz);
         wddd = view.findViewById(R.id.tv_wd_dd);
         ivuser.setOnClickListener(this);
         tvlogin.setOnClickListener(this);
         ivsz.setOnClickListener(this);
        wddd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_user:
                sp=getActivity().getSharedPreferences("LOGIN",getActivity().MODE_PRIVATE);
                String login = sp.getString("LOGIN", null);
                if(login!=null){
                    startActivity(new Intent(getActivity(), SzActivity.class));
                }
               else {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                break;
            case R.id.tv_login:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
            case R.id.tv_wd_dd:
                startActivity(new Intent(getActivity(), MyDdActivity.class));
                break;
            case R.id.iv_sz:
                startActivity(new Intent(getActivity(),SzActivity.class));
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
        System.out.println("===获得的个人信息==="+msg);
        Jsonjie(msg);
    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    private void Jsonjie(String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONObject dataj = obj.optJSONObject("data");
            String icon = dataj.optString("icon");
            String username = dataj.optString("nickname");
            System.out.println("===="+icon);
            System.out.println("====="+username);
           ImageLoader.getInstance().displayImage(icon,ivuser);
            tvlogin.setText(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
}
