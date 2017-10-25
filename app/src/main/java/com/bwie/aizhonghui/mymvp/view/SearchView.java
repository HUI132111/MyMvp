package com.bwie.aizhonghui.mymvp.view;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public interface SearchView {
    void SearchSuccess(String code, String msg);
    void SearchFail(String code, String msg);
    void SearchError(Call call, IOException e);
}
