package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/16.
 */

public class MyScHistory extends BaseAdapter{
    private Context context;
    private List<String> list;

    public MyScHistory(Context context, List<String> list) {
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
            view=View.inflate(context, R.layout.item_search_history,null);
        }
        TextView tvhistory=view.findViewById(R.id.tv_search_tv);
        tvhistory.setText(list.get(i));
        return view;
    }
}
