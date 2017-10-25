package com.bwie.aizhonghui.mymvp.Adapter;

/**
 * Created by DANGEROUS_HUI on 2017/10/5.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/9/5.
 */

public class MyReadapter extends RecyclerView.Adapter<MyReadapter.MyViewHolder> {
    private Context context;
    private List<Integer> list;
    private OnItemClickListener onItemClickListener;

    public MyReadapter(Context context, List<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_sy_lv,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.ivimg.setImageResource(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
       private ImageView ivimg;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivimg=itemView.findViewById(R.id.iv_syzi);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int pos,View view);
    }
}

