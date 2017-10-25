package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface ZiSearchViewh {
    void ziSearchSuccess(String code, String msg);
    void ziSearchFail(String code, String msg);
    void ziSearchError(Call call, IOException e);
}
