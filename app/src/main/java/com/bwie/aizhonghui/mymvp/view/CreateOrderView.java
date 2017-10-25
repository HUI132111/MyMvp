package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface CreateOrderView {
    void CreateOrderSuccess(String code, String msg);
    void CreateOrderFail(String code, String msg);
    void CreateOrderError(Call call, IOException e);
}
