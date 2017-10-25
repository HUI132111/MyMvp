package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.SearchView;

import com.bwie.aizhonghui.mymvp.model.LoginModel;
import com.bwie.aizhonghui.mymvp.model.SearchModel;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class SearchPresenter implements SearchModel.ISearch{
    private SearchModel searchModel;
    private SearchViewh searchViewh;
    private Activity context;
    private Map<String,String> map;

    public SearchPresenter(Activity context, SearchViewh searchViewh) {
        this.context=context;
        this.searchViewh=searchViewh;
        searchModel=new SearchModel(context);
        searchModel.setiSearch(this);

    }

    public void ISearch(String keywords,String page,String sort){
        map=new HashMap<>();
        map.put("keywords",keywords);
        map.put("page",page);
        map.put("sort",sort);
        searchModel.search(map);
    }

    @Override
    public void SearchSuccess(String code, String msg) {
      searchViewh.SearchSuccess(code,msg);
    }

    @Override
    public void SearchFail(String code, String msg) {

    }

    @Override
    public void SearchError(Call call, IOException e) {

    }
}
