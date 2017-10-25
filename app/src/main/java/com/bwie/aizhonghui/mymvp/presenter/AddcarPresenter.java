package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.AddCarModel;
import com.bwie.aizhonghui.mymvp.model.SearchModel;
import com.bwie.aizhonghui.mymvp.view.AddcarView;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class AddcarPresenter implements AddCarModel.IAddCar{
    private AddCarModel addCarModel;
    private AddcarView addcarView;
    private Activity context;
    private Map<String,String> map;

    public AddcarPresenter(Activity context, AddcarView addcarView) {
        this.context=context;
        this.addcarView=addcarView;
        addCarModel=new AddCarModel(context);
        addCarModel.setiAddCar(this);

    }

    public void IAddcarp(String uid,String pid){
        map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        addCarModel.addCar(map);
    }


    @Override
    public void AddcarSuccess(String code, String msg) {
          addcarView.AddcarSuccess(code,msg);
    }

    @Override
    public void AddcarFail(String code, String msg) {

    }

    @Override
    public void AddcarError(Call call, IOException e) {

    }
}
