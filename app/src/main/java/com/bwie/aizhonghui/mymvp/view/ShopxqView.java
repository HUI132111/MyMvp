package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface ShopxqView {
    void ShopxqSuccess(String code, String msg);
    void ShopxqFail(String code, String msg);
    void ShopxqError(Call call, IOException e);
}
