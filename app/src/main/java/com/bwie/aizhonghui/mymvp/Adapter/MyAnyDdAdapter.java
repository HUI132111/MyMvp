package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.aizhonghui.mymvp.Bean.Any_DD_bean;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/22.
 */

public class MyAnyDdAdapter extends BaseAdapter{
    private Context context;
    private List<Any_DD_bean.DataBean> list;
    private TextView hao;
    private TextView price;
    private TextView tai;
    private TextView time;

    public MyAnyDdAdapter(Context context, List<Any_DD_bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context, R.layout.item_my_any_dd,null);
        }
        hao = view.findViewById(R.id.tv_ddhao);
        price = view.findViewById(R.id.tv_ddzj);
        tai = view.findViewById(R.id.tv_ddtai);
        time = view.findViewById(R.id.tv_ddtime);
        hao.setText(list.get(i).getOrderid()+"");
        price.setText(list.get(i).getPrice()+"");
        time.setText(list.get(i).getCreatetime());
        if(list.get(i).getStatus()==0){
            tai.setText("待支付");
        }else if(list.get(i).getStatus()==1) {
            tai.setText("已支付");
        }
        return view;
    }
}
