package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Fl_li;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/7.
 */

public class MySyGdAdapter extends BaseAdapter{
    private Context context;
    private List<Fl_li> list;

    public MySyGdAdapter(Context context, List<Fl_li> list) {
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
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.item_sygd_img,null);
            holder.iv=view.findViewById(R.id.iv_sygdimg);
            holder.tv=view.findViewById(R.id.tv_sy_fen);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i).name);
        Glide.with(context)
                .load(list.get(i).img)
                .into(holder.iv);
        return view;
    }
    class ViewHolder{
        public ImageView iv;
        public TextView tv;
    }
}
