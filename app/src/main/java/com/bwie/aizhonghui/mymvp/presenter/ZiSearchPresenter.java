package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.SearchModel;
import com.bwie.aizhonghui.mymvp.model.ZiSearchModel;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;
import com.bwie.aizhonghui.mymvp.view.ZiSearchViewh;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class ZiSearchPresenter implements ZiSearchModel.IZiSearch{
    private ZiSearchModel ziSearchModel;
    private ZiSearchViewh ziSearchViewh;
    private Activity context;
    private Map<String,String> map;

    public ZiSearchPresenter(Activity context, ZiSearchViewh ziSearchViewh) {
        this.context=context;
        this.ziSearchViewh=ziSearchViewh;
        ziSearchModel=new ZiSearchModel(context);
        ziSearchModel.setziSearch(this);

    }

    public void IziSearch(String pscid,String page,String sort){
        map=new HashMap<>();
        map.put("pscid",pscid);
        map.put("page",page);
        map.put("sort",sort);
        ziSearchModel.zisearch(map);
    }

    @Override
    public void ziSearchSuccess(String code, String msg) {
     ziSearchViewh.ziSearchSuccess(code,msg);
    }

    @Override
    public void ziSearchFail(String code, String msg) {

    }

    @Override
    public void ziSearchError(Call call, IOException e) {

    }
}
