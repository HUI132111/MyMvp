package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.AddCarModel;
import com.bwie.aizhonghui.mymvp.model.GetCarModel;
import com.bwie.aizhonghui.mymvp.view.AddcarView;
import com.bwie.aizhonghui.mymvp.view.GetcarView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class GetcarPresenter implements GetCarModel.IGetCar{
    private GetCarModel getCarModel;
    private GetcarView getcarView;
    private Activity context;
    private Map<String,String> map;

    public GetcarPresenter(Activity context, GetcarView getcarView) {
        this.context=context;
        this.getcarView=getcarView;
        getCarModel=new GetCarModel(context);
        getCarModel.setiGetCar(this);

    }

    public void IGetcarp(String uid){
        map=new HashMap<>();
        map.put("uid",uid);
        getCarModel.getCar(map);
    }


    @Override
    public void GetcarSuccess(String code, String msg) {
        getcarView.GetcarSuccess(code,msg);
    }

    @Override
    public void GetcarFail(String code, String msg) {

    }

    @Override
    public void GetcarError(Call call, IOException e) {

    }
}
