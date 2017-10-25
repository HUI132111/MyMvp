package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.aizhonghui.mymvp.Bean.Fl_li;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/8.
 */

public class MyFlAdapter extends BaseAdapter{
    private Context context;
    private List<Fl_li> list;
    public static int mCurrentPos;

    public MyFlAdapter(Context context, List<Fl_li> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=View.inflate(context, R.layout.item_fl_li,null);
        }
         TextView fen=view.findViewById(R.id.tv_fen);
         RelativeLayout rer=view.findViewById(R.id.re_fl_back);
        fen.setText(list.get(i).name);
        if(mCurrentPos==i){
            rer.setBackgroundColor(Color.GRAY);
            fen.setTextColor(Color.RED);
        }else {
            rer.setBackgroundColor(Color.WHITE);
            fen.setTextColor(Color.GRAY);
        }
        return view;
    }
}
