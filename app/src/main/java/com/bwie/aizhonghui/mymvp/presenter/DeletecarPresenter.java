package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.AddCarModel;
import com.bwie.aizhonghui.mymvp.model.DeleteCarModel;
import com.bwie.aizhonghui.mymvp.view.AddcarView;
import com.bwie.aizhonghui.mymvp.view.DeletecarView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class DeletecarPresenter implements DeleteCarModel.IDeleteCar{
    private DeleteCarModel deleteCarModel;
    private DeletecarView deletecarView;
    private Activity context;
    private Map<String,String> map;

    public DeletecarPresenter(Activity context, DeletecarView deletecarView) {
        this.context=context;
        this.deletecarView=deletecarView;
        deleteCarModel=new DeleteCarModel(context);
        deleteCarModel.setiDeleteCar(this);

    }

    public void IDeletecarp(String uid,String pid){
        map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        deleteCarModel.deleteCar(map);
    }


    @Override
    public void DeletecarSuccess(String code, String msg) {
        deletecarView.DeletecarSuccess(code,msg);
    }

    @Override
    public void DeletecarFail(String code, String msg) {

    }

    @Override
    public void DeletecarError(Call call, IOException e) {

    }
}
