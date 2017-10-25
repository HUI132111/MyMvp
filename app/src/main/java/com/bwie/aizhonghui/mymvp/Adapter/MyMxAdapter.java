package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Sy_MS;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/8.
 */

public class MyMxAdapter extends RecyclerView.Adapter<MyMxAdapter.MyViewHolder>{
    private Context context;
    private List<Sy_MS.MiaoshaBean.ListBeanX> list;
    private OnItemClickListener onItemClickListener;

    public MyMxAdapter(Context context, List<Sy_MS.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_mx_iv,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String[] strs = images.split("\\|");
        Glide.with(context)
                .load(strs[0].toString())
                .into(holder.ivimg);
        holder.tvmx.setText("¥"+list.get(position).getBargainPrice()+"");
        holder.tvyuan.setText("¥"+list.get(position).getSalenum()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(position,view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivimg;
        private TextView tvmx;
        private TextView tvyuan;
        public MyViewHolder(View itemView) {
            super(itemView);
            ivimg=itemView.findViewById(R.id.iv_mxiv);
            tvmx=itemView.findViewById(R.id.tv_mx);
            tvyuan=itemView.findViewById(R.id.tv_yuan);
            tvyuan.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void onItemClickListener(int pos,View view);
    }
}
