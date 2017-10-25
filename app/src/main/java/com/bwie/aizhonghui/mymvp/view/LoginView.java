package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface LoginView {
    void showProgressbar();
    void hideProgressbar();
    void nameError(String msg);
    void passError(String msg);
    void loginSuccess(String code,String msg);
    void updateSuccess(String code,String msg);
    void loginFail(String code,String msg);
    void failure(Call call, IOException e);
}
