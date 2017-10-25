package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.SearchModel;
import com.bwie.aizhonghui.mymvp.model.ShopXqModel;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;
import com.bwie.aizhonghui.mymvp.view.ShopxqView;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class ShopxqPresenter implements ShopXqModel.IShopXq{
    private ShopXqModel shopXqModel;
    private ShopxqView shopxqView;
    private Activity context;

    public ShopxqPresenter(Activity context, ShopxqView shopxqView) {
        this.context=context;
        this.shopxqView=shopxqView;
        shopXqModel=new ShopXqModel(context);
        shopXqModel.setiShopxq(this);

    }
     public  void ShopxqOk(String pid){
         shopXqModel.shopxq(pid);
     }

    @Override
    public void ShopxqSuccess(String code, String msg) {
        shopxqView.ShopxqSuccess(code,msg);
    }

    @Override
    public void ShopxqFail(String code, String msg) {

    }

    @Override
    public void ShopxqError(Call call, IOException e) {

    }
}
