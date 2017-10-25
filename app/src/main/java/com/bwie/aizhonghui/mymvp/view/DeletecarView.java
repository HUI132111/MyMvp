package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface DeletecarView {
    void DeletecarSuccess(String code, String msg);
    void DeletecarFail(String code, String msg);
    void DeletecarError(Call call, IOException e);
}
