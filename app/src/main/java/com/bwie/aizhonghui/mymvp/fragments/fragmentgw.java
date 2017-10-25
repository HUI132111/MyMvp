package com.bwie.aizhonghui.mymvp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Adapter.MygwAdapter;
import com.bwie.aizhonghui.mymvp.Adapter.MygwitemAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Gw_bean;
import com.bwie.aizhonghui.mymvp.DDActivity;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.DeletecarPresenter;
import com.bwie.aizhonghui.mymvp.presenter.GetcarPresenter;
import com.bwie.aizhonghui.mymvp.presenter.ShopCarUpdatePresenter;
import com.bwie.aizhonghui.mymvp.view.CreateOrderView;
import com.bwie.aizhonghui.mymvp.view.DeletecarView;
import com.bwie.aizhonghui.mymvp.view.GetcarView;
import com.bwie.aizhonghui.mymvp.view.ShopCarUpdateView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/3.
 */

public class fragmentgw extends Fragment implements GetcarView,ShopCarUpdateView,MygwAdapter.ZMoneysum,DeletecarView, View.OnClickListener {
    private View view;
    private GetcarPresenter getcarPresenter;
    private DeletecarPresenter deletecarPresenter;
    private SharedPreferences sp;
    private String loginuid;
    private RecyclerView regw;
    private List<Gw_bean.DataBean> data;
    private MygwAdapter mga;
    private TextView zj;
    private ShopCarUpdatePresenter shopCarUpdatePresenter;
    private CheckBox quan;
    private int shopnum=3000;
    private int kk=2000;
    private MygwitemAdapter mygwitemAdapter;
    private int shopmaxb;
    private int shopmaxboss=0;
    private String QUANBoss=null;
    private List<Integer> listja;
    private RelativeLayout jsgo;
    private String format;
    private ProgressBar gwprogressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.item_gw,null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        getcarPresenter=new GetcarPresenter(getActivity(),this);
        shopCarUpdatePresenter=new ShopCarUpdatePresenter(getActivity(),this);
        deletecarPresenter=new DeletecarPresenter(getActivity(),this);
        loginuid = sp.getString("LOGIN", null);
        if(loginuid!=null){
            getcarPresenter.IGetcarp(loginuid);
        }else {
            Toast.makeText(getActivity(),"还没登录，赶快登录吧",Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        sp=getActivity().getSharedPreferences("LOGIN",getActivity().MODE_PRIVATE);
        regw = view.findViewById(R.id.recycler_gw);
        gwprogressBar = view.findViewById(R.id.gw_progress);
        zj = view.findViewById(R.id.tv_gw_zj);
        jsgo = view.findViewById(R.id.re_go);
        quan = view.findViewById(R.id.ckb_quan);
        jsgo.setOnClickListener(this);
        quan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                kk=0;
                for (Gw_bean.DataBean dataBean : data) {
                    List<Gw_bean.DataBean.ListBean> list = dataBean.getList();
                    for (Gw_bean.DataBean.ListBean listBean : list) {
                        kk++;
                    }
                }
                System.out.println("===商品的总数量==="+kk);
                if(isChecked){
                    if(data!=null){
                        shopnum=0;
                        for (Gw_bean.DataBean dataBean : data) {
                            List<Gw_bean.DataBean.ListBean> list = dataBean.getList();
                            for (Gw_bean.DataBean.ListBean listBean : list) {
                              shopCarUpdatePresenter.IShopCarUpdate(loginuid,listBean.getSellerid()+"",listBean.getPid()+"",listBean.getNum()+"",1+"");
                            }
                        }
                    }
                }else {
                    if(data!=null){
                        shopnum=0;
                        for (Gw_bean.DataBean dataBean : data) {
                            List<Gw_bean.DataBean.ListBean> list = dataBean.getList();
                            for (Gw_bean.DataBean.ListBean listBean : list) {
                                shopCarUpdatePresenter.IShopCarUpdate(loginuid,listBean.getSellerid()+"",listBean.getPid()+"",listBean.getNum()+"",0+"");
                            }
                        }
                    }
                }

            }
        });
    }

    @Override
    public void GetcarSuccess(String code, String msg) {
        System.out.println("===查询的商品数量==="+msg);
        listja=new ArrayList<>();
        kk=0;
        QUANBoss=null;
        shopmaxboss=0;
        Gson gson=new Gson();
        Gw_bean gw_bean = gson.fromJson(msg, Gw_bean.class);
        data = gw_bean.getData();
        int bbb=0;
        int kkkkk=0;
        for (int i = 0; i <data.size(); i++) {
            int kb=0;
            List<Gw_bean.DataBean.ListBean> list = data.get(i).getList();
            kkkkk+=list.size();
            for (int j = 0; j <list.size(); j++) {
                if(list.get(j).getSelected()==1){
                    kb++;
                    bbb++;
                }
            }
            if(kb==list.size()){
                listja.add(9);
            }else{
                listja.add(6);
            }
        }
        if(bbb==kkkkk){
            quan.setChecked(true);
        }else {
            quan.setChecked(false);
        }
        gwprogressBar.setVisibility(View.GONE);
        initSetData();
        double summ=0;


        for (Gw_bean.DataBean dataBean : data) {
            List<Gw_bean.DataBean.ListBean> list = dataBean.getList();
            for (Gw_bean.DataBean.ListBean listBean : list) {
                if(listBean.getSelected()==1){
                    double v = listBean.getBargainPrice() * listBean.getNum();
                    summ+=v;
                }
            }
        }
        format = String.format("%.2f", summ);
        zj.setText(format);
    }

    private void initSetData() {
//        if(mga==null){
            mga=new MygwAdapter(getActivity(),data,listja);
            mga.IIMoneysum(this);
            LinearLayoutManager linealaymanger=new LinearLayoutManager(getActivity());
            regw.setLayoutManager(linealaymanger);
            regw.setAdapter(mga);
//        }else {
//            mga.notifyDataSetChanged();
//         }
    }

    @Override
    public void GetcarFail(String code, String msg) {

    }

    @Override
    public void GetcarError(Call call, IOException e) {

    }

    @Override
    public void ShopCarUpdateSuccess(String code, String msg) {
        System.out.println("==更改真的成功了=");
        shopnum++;
        System.out.println("===商品真实的总数量==="+shopnum);
        if(shopnum==kk){
            getcarPresenter.IGetcarp(loginuid);
        }
        if(kk==120){
            getcarPresenter.IGetcarp(loginuid);
        }
        System.out.println("===商ddddd===" );
        if(QUANBoss!=null){
            System.out.println("===商fffff===");
            shopmaxboss++;
            System.out.println(shopmaxboss+"======="+shopmaxb);
            if(shopmaxboss==shopmaxb){
                System.out.println(shopmaxboss+"======="+shopmaxb);
                System.out.println("===商rrrrrrrr===");
                getcarPresenter.IGetcarp(loginuid);
            }
        }
    }

    @Override
    public void ShopCarUpdateFail(String code, String msg) {

    }

    @Override
    public void ShopCarUpdateError(Call call, IOException e) {

    }

    @Override
    public void XXmoneysum(String uid, String sellerid, String pid, String num, String selected) {
        System.out.println("=="+uid+"=="+selected+"=="+pid+"=="+num+"=="+selected);
        kk=120;
        shopCarUpdatePresenter.IShopCarUpdate(uid,sellerid,pid,num,selected);
        gwprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void JAmoneysum(String uid, String sellerid, String pid, String num, String selected,int shopmax,String QUAN) {
        System.out.println("=="+uid+"=="+selected+"=="+pid+"=="+num+"=="+selected);
        shopmaxb=shopmax;
        QUANBoss=QUAN;
        shopCarUpdatePresenter.IShopCarUpdate(uid,sellerid,pid,num,selected);
        gwprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void Deleteshop(String uid, String pid) {
        System.out.println("=====ppp==="+pid);
        deletecarPresenter.IDeletecarp(uid,pid);
    }

//    @Override
//    public void Xmoneysum(String uid, String sellerid, String pid, String num, String selected) {
//        for (Gw_bean.DataBean dataBean : data) {
//            List<Gw_bean.DataBean.ListBean> list = dataBean.getList();
//            for (Gw_bean.DataBean.ListBean listBean : list) {
//                kk++;
//            }
//        }
//      shopCarUpdatePresenter.IShopCarUpdate(uid,sellerid,pid,num,selected);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mga=null;
    }

    @Override
    public void DeletecarSuccess(String code, String msg) {
        System.out.println("刹车怒成功了"+msg);
        getcarPresenter.IGetcarp(loginuid);
    }

    @Override
    public void DeletecarFail(String code, String msg) {

    }

    @Override
    public void DeletecarError(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_go:
                Intent intent=new Intent(getActivity(), DDActivity.class);
                intent.putExtra("uid",loginuid);
                intent.putExtra("price",format);
                getActivity().startActivity(intent);
                break;
        }
    }

}
