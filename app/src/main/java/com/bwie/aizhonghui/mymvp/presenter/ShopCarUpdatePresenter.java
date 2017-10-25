package com.bwie.aizhonghui.mymvp.presenter;

import android.app.Activity;

import com.bwie.aizhonghui.mymvp.model.SearchModel;
import com.bwie.aizhonghui.mymvp.model.ShopCarUpdateModel;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;
import com.bwie.aizhonghui.mymvp.view.ShopCarUpdateView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/9/27.
 */

public class ShopCarUpdatePresenter implements ShopCarUpdateModel.IShopCarUpdate{
    private ShopCarUpdateModel shopCarUpdateModel;
    private ShopCarUpdateView shopCarUpdateView;
    private Activity context;
    private Map<String,String> map;

    public ShopCarUpdatePresenter(Activity context,ShopCarUpdateView shopCarUpdateView) {
        this.context=context;
        this.shopCarUpdateView=shopCarUpdateView;
        shopCarUpdateModel=new ShopCarUpdateModel(context);
        shopCarUpdateModel.setiShopCarUpdate(this);

    }

    public void IShopCarUpdate(String uid,String sellerid,String pid,String num,String selected){
        map=new HashMap<>();
        map.put("uid",uid);
        map.put("sellerid",sellerid);
        map.put("pid",pid);
        map.put("num",num);
        map.put("selected",selected);
        shopCarUpdateModel.shopcarUpdate(map);
    }


    @Override
    public void ShopCarUpdateSuccess(String code, String msg) {
          shopCarUpdateView.ShopCarUpdateSuccess(code,msg);
    }

    @Override
    public void ShopCarUpdateFail(String code, String msg) {

    }

    @Override
    public void ShopCarUpdateError(Call call, IOException e) {

    }
}
