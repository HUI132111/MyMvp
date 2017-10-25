package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface ShopCarUpdateView {
    void ShopCarUpdateSuccess(String code, String msg);
    void ShopCarUpdateFail(String code, String msg);
    void ShopCarUpdateError(Call call, IOException e);
}
