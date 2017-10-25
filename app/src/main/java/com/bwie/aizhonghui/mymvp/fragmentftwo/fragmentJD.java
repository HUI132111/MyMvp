package com.bwie.aizhonghui.mymvp.fragmentftwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.aizhonghui.mymvp.Adapter.MyFlFraAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Fl_fra;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/9.
 */

public class fragmentJD extends Fragment implements LoginView{
    private RecyclerView refra;
    private View view;
    private LoginPresenter loginpresenter;
    private List<Fl_fra.DataBean> data;
    private MyFlFraAdapter myFlFraAdapter;
    private View view1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_fl_jd,null);
        initView();
        initOk();
        return view;
    }

    private void initOk() {
        loginpresenter = new LoginPresenter(getActivity(),this);
        loginpresenter.fenfra("1");
    }

    private void initView() {
        refra=view.findViewById(R.id.re_fra);
        view1 = View.inflate(getActivity(), R.layout.item_fl_jd_tou,null);
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void loginSuccess(String code, String msg) {
        System.out.println("====分类子商品数据==="+msg);
        List<Fl_fra.DataBean> dataBeen = GsonJie(msg);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        refra.setLayoutManager(linearLayoutManager);
        myFlFraAdapter = new MyFlFraAdapter(getActivity(), dataBeen);
        refra.setAdapter(myFlFraAdapter);


    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
    private List<Fl_fra.DataBean> GsonJie(String msg) {
        Gson gson=new Gson();
        Fl_fra fl_fra = gson.fromJson(msg, Fl_fra.class);
        data = fl_fra.getData();
        return data;
    }
}
