package com.bwie.aizhonghui.mymvp.fragmentxq;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Shopxq_shop;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.AddcarPresenter;
import com.bwie.aizhonghui.mymvp.presenter.ShopxqPresenter;
import com.bwie.aizhonghui.mymvp.view.ShopxqView;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

@SuppressLint("ValidFragment")
public class fragmentshop extends Fragment implements ShopxqView,XBanner.XBannerAdapter{
    private View view;
    private ShopxqPresenter shopxqPresenter;
    private String pid;
    private Shopxq_shop.DataBean data;
    private TextView title;
    private TextView sub;
    private TextView price;
    private XBanner banner;
    private List<String> stringList;

    public fragmentshop(String pid) {
        this.pid = pid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_shop_xq_shop,null);
        initView();
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    private void initView() {
        banner = view.findViewById(R.id.banner);
        title = view.findViewById(R.id.tv_shop_xq_shop_title);
        sub = view.findViewById(R.id.tv_shop_xq_shop_sub);
        price = view.findViewById(R.id.tv_shop_xq_shop_price);
    }

    private void initData() {
        stringList=new ArrayList<>();
        shopxqPresenter=new ShopxqPresenter(getActivity(),this);
        shopxqPresenter.ShopxqOk(pid);
    }

    @Override
    public void ShopxqSuccess(String code, String msg) {
        System.out.println("====ppppp"+msg);
        Gson gson=new Gson();
        Shopxq_shop shopxq_shop = gson.fromJson(msg, Shopxq_shop.class);
        data = shopxq_shop.getData();
        for (int i = 0; i <4; i++) {
            String images = data.getImages();
            String[] split = images.split("\\|");
            stringList.add(split[0]);
            System.out.println("sdfsdfsdf"+split[0]);
        }
        intsetData();
    }

    private void intsetData() {
        banner.setData(stringList,null);
        banner.setPoinstPosition(XBanner.CENTER);
        banner.setmAdapter(this);
        title.setText(data.getTitle());
        sub.setText(data.getSubhead());
        price.setText(data.getPrice()+"");
    }


    @Override
    public void ShopxqFail(String code, String msg) {

    }

    @Override
    public void ShopxqError(Call call, IOException e) {

    }

    @Override
    public void loadBanner(XBanner banner, View view, int position) {
        Glide.with(this).load(stringList.get(position)).into((ImageView) view);
    }
}
