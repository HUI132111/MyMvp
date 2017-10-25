package com.bwie.aizhonghui.mymvp.fragmentxq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwie.aizhonghui.mymvp.Bean.Shopxq_shop;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.ShopxqPresenter;
import com.bwie.aizhonghui.mymvp.view.ShopxqView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

public class fragmentxq extends Fragment implements ShopxqView{
    private View view;
    private WebView wv;
    private String pid;
    private ShopxqPresenter shopxqPresenter;

    public fragmentxq(String pid) {
        this.pid = pid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_shop_xq_xq,null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        shopxqPresenter=new ShopxqPresenter(getActivity(),this);
        shopxqPresenter.ShopxqOk(pid);
    }

    private void initView() {
        wv = view.findViewById(R.id.wv_xq_xq);
    }

    @Override
    public void ShopxqSuccess(String code, String msg) {
        System.out.println("====dddddddfff"+msg);
        Gson gson=new Gson();
        Shopxq_shop shopxq_shop = gson.fromJson(msg, Shopxq_shop.class);
        Shopxq_shop.DataBean data = shopxq_shop.getData();
        String detailUrl = data.getDetailUrl();
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(detailUrl);
    }

    @Override
    public void ShopxqFail(String code, String msg) {

    }

    @Override
    public void ShopxqError(Call call, IOException e) {

    }
}
