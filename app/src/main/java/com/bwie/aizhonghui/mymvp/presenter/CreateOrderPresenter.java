package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.AddCarModel;
import com.bwie.aizhonghui.mymvp.model.CreateOrderModel;
import com.bwie.aizhonghui.mymvp.view.AddcarView;
import com.bwie.aizhonghui.mymvp.view.CreateOrderView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class CreateOrderPresenter implements CreateOrderModel.ICreateOrder{
    private CreateOrderModel createOrderModel;
    private CreateOrderView createOrderView;
    private Activity context;
    private Map<String,String> map;

    public CreateOrderPresenter(Activity context, CreateOrderView createOrderView) {
        this.context=context;
        this.createOrderView=createOrderView;
        createOrderModel=new CreateOrderModel(context);
        createOrderModel.setiCreateOrder(this);

    }

    public void ICreateOrder(String uid,String price){
        map=new HashMap<>();
        map.put("uid",uid);
        map.put("price",price);
        createOrderModel.createorderr(map);
    }


    @Override
    public void CreateOrderSuccess(String code, String msg) {
       createOrderView.CreateOrderSuccess(code,msg);
    }

    @Override
    public void CreateOrderFail(String code, String msg) {

    }

    @Override
    public void CreateOrderError(Call call, IOException e) {

    }
}
