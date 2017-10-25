package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface AddcarView {
    void AddcarSuccess(String code, String msg);
    void AddcarFail(String code, String msg);
    void AddcarError(Call call, IOException e);
}
