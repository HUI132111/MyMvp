package com.bwie.aizhonghui.mymvp.fragmentftwo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.aizhonghui.mymvp.Adapter.MyFlFraAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Fl_fra;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/9.
 */

@SuppressLint("ValidFragment")
public class fragmentTy extends Fragment implements LoginView{
    private RecyclerView refra;
    private View view;
    private LoginPresenter loginpresenter;
    private List<Fl_fra.DataBean> data;
    private String kk;
    private List<Fl_fra.DataBean> dataBeen;

    public fragmentTy(String kk) {
        this.kk = kk;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_fl_jd,null);
//        Bundle arguments = getArguments();
//        String string = arguments.getString("");
        initView();
        initOk();
        return view;
    }

    private void initOk() {
        loginpresenter = new LoginPresenter(getActivity(),this);
        loginpresenter.fenfra(kk);
    }

    private void initView() {
        refra=view.findViewById(R.id.re_fra);

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
        dataBeen = GsonJie(msg);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        refra.setLayoutManager(linearLayoutManager);
        if(dataBeen !=null){
            refra.setAdapter(new MyFlFraAdapter(getActivity(), dataBeen));
        }

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
