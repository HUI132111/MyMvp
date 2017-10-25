package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Adapter.Myshopxq_tab;
import com.bwie.aizhonghui.mymvp.fragmentxq.fragmentpj;
import com.bwie.aizhonghui.mymvp.fragmentxq.fragmentshop;
import com.bwie.aizhonghui.mymvp.fragmentxq.fragmentxq;
import com.bwie.aizhonghui.mymvp.presenter.AddcarPresenter;
import com.bwie.aizhonghui.mymvp.view.AddcarView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ShopXqActivity extends AppCompatActivity implements AddcarView, View.OnClickListener {

    @BindView(R.id.iv_xq_fanhui)
    ImageView ivXqFanhui;
    @BindView(R.id.tl)
    TabLayout tab;
    @BindView(R.id.iv_fenxiang)
    ImageView ivFenxiang;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.kefu)
    LinearLayout kefu;
    @BindView(R.id.dianpu)
    LinearLayout dianpu;
    @BindView(R.id.iv_guanzhu)
    ImageView ivGuanzhu;
    @BindView(R.id.guanzhu)
    LinearLayout guanzhu;
    @BindView(R.id.shapcar)
    LinearLayout shapcar;
    @BindView(R.id.joinshapcar)
    Button joinshapcar;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    private List<Fragment> fragments;
    private Myshopxq_tab msb;
    private String xqpid;
    private SharedPreferences sp;
    private AddcarPresenter addcarPresenter;
    private String loginuid;
    private ImageView imagecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_xq);
        ButterKnife.bind(this);
        sp=getSharedPreferences("LOGIN",MODE_PRIVATE);
        loginuid = sp.getString("LOGIN", null);
        if(loginuid !=null){
            System.out.println("=用户ID为=="+ loginuid);
        }
        initView();
        initgetIntent();
    }

    private void initView() {
        imagecar = (ImageView) findViewById(R.id.iv_shop_xq_car);
        imagecar.setOnClickListener(this);
    }

    private void inittab() {
        fragments = new ArrayList<>();
        fragments.add(new fragmentshop(xqpid));
        fragments.add(new fragmentxq(xqpid));
        fragments.add(new fragmentpj());
        tab.setupWithViewPager(vp);
        initsetData();
    }

    private void initsetData() {
        msb = new Myshopxq_tab(getSupportFragmentManager(), this, fragments);
        vp.setAdapter(msb);
    }

    private void initgetIntent() {
        Intent intent = getIntent();
        xqpid = intent.getStringExtra("xqpid");
        addcarPresenter=new AddcarPresenter(ShopXqActivity.this,this);
        inittab();
    }


    @OnClick({R.id.iv_xq_fanhui, R.id.iv_fenxiang,R.id.joinshapcar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_xq_fanhui:
                finish();
                break;
            case R.id.iv_fenxiang:
                break;
            case R.id.joinshapcar:
                addcarPresenter.IAddcarp(loginuid,xqpid);
                break;
        }
    }

    @Override
    public void AddcarSuccess(String code, String msg) {
        System.out.println("===添加购物车成功了=咚咚咚==="+msg);
        Toast.makeText(this,"添加成功  咚咚咚",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void AddcarFail(String code, String msg) {

    }

    @Override
    public void AddcarError(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {
      startActivity(new Intent(ShopXqActivity.this,BossActivity.class));
    }
}
