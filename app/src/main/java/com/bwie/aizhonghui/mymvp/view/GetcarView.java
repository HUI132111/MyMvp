package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface GetcarView {
    void GetcarSuccess(String code, String msg);
    void GetcarFail(String code, String msg);
    void GetcarError(Call call, IOException e);
}
