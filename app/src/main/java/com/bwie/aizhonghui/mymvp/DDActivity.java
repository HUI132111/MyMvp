package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.pay.PayDemoActivity;
import com.bwie.aizhonghui.mymvp.presenter.CreateOrderPresenter;
import com.bwie.aizhonghui.mymvp.view.CreateOrderView;

import java.io.IOException;

import okhttp3.Call;

public class DDActivity extends AppCompatActivity implements CreateOrderView, View.OnClickListener {

    private CreateOrderPresenter createOrderPresenter;
    private TextView name;
    private TextView dprice;
    private RelativeLayout tjdd;
    private RelativeLayout qxdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dd);
        initView();
        initgetIntent();
    }

    private void initgetIntent() {
        Intent intent = getIntent();
        String uid = intent.getStringExtra("uid");
        String price = intent.getStringExtra("price");
        System.out.println(uid+"=====ppppppp"+price);
        if(uid!=null&&price!=null){
            name.setText(uid);
            dprice.setText(price);
            createOrderPresenter=new CreateOrderPresenter(DDActivity.this,this);
            createOrderPresenter.ICreateOrder(uid,price);
        }
    }

    private void initView() {
        name = (TextView) findViewById(R.id.tv_dd_name);
        dprice = (TextView) findViewById(R.id.tv_dd_price);
        tjdd = (RelativeLayout) findViewById(R.id.re_tjdd);
        qxdd = (RelativeLayout) findViewById(R.id.re_qxdd);
        tjdd.setOnClickListener(this);
        qxdd.setOnClickListener(this);
    }

    @Override
    public void CreateOrderSuccess(String code, String msg) {
        System.out.println("++++订单创建成功了++++"+msg);
    }

    @Override
    public void CreateOrderFail(String code, String msg) {

    }

    @Override
    public void CreateOrderError(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_tjdd:
                startActivity(new Intent(DDActivity.this, PayDemoActivity.class));
                finish();
                break;
            case R.id.re_qxdd:
                startActivity(new Intent(DDActivity.this,MyDdActivity.class));
                finish();
                break;
        }
    }
}
