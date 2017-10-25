package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.bwie.aizhonghui.mymvp.model.LoginModel;
import com.bwie.aizhonghui.mymvp.view.LoginView;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class LoginPresenter implements LoginModel.ILogin{
    private LoginModel loginModel;
    private LoginView loginView;
    private Activity context;

    public LoginPresenter(Activity context,LoginView loginView) {
        this.loginView = loginView;
        this.context=context;
        loginModel=new LoginModel(context);
        loginModel.setLigin(this);

    }

    /**
     *
     * @param mobile
     * @param pass
     */
    public void login(String mobile,String pass){
        loginView.showProgressbar();

        if(TextUtils.isEmpty(mobile)){
            loginView.nameError("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            loginView.passError("密码不能为空");
            return;
        }
        loginModel.login(mobile,pass);
    }

    public void reg(String mobile,String pass){
        loginView.showProgressbar();

        if(TextUtils.isEmpty(mobile)){
            loginView.nameError("用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            loginView.passError("密码不能为空");
            return;
        }
        loginModel.reg(mobile,pass);
    }
    public void vptousy(){
       loginModel.syvpTou();
    }
    public void fenlie(){
       loginModel.Flfl();
    }
    public void fenfra(String cid){
        loginModel.FlFra(cid);
    }
    public void get_user(String uid){
        loginModel.userget(uid);
    }
    public void update_name(String uid,String nickname){
        loginModel.updatename(uid,nickname);
    }
    @Override
    public void loginSuccess(String code, String msg) {

          loginView.loginSuccess(code,msg);
          loginView.hideProgressbar();

    }

    @Override
    public void updateSuccess(String code, String msg) {
        loginView.updateSuccess(code,msg);
    }

    @Override
    public void loginFail(String code, String msg) {

        loginView.loginFail(code,msg);
        loginView.hideProgressbar();
    }

    @Override
    public void failure(Call call, IOException e) {
        loginView.failure(call,e);
        loginView.hideProgressbar();
    }
}
