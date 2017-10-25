package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Search_str;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

public class MySearchAdapter extends RecyclerView.Adapter{
    private Context context;
    private List<Search_str.DataBean> list;
    private int Type;
    private OnItemClickListener onItemClickListener;


    public MySearchAdapter(Context context, List<Search_str.DataBean> list, int type) {
        this.context = context;
        this.list = list;
        Type = type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder=null;
        switch (Type){
            case 0:
                view= LayoutInflater.from(context).inflate(R.layout.item_search_xq,null);
                holder=new MyViewHolderList(view);
               break;
            case 1:
                view=LayoutInflater.from(context).inflate(R.layout.item_search_xqg,null);
                holder=new MyViewHolderGrid(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (Type){
            case 0:
                MyViewHolderList holderList= (MyViewHolderList) holder;
                holderList.title.setText(list.get(position).getTitle());
                holderList.price.setText(list.get(position).getBargainPrice()+"");
                String images = list.get(position).getImages();
                String[] split = images.split("\\|");
                Glide.with(context)
                        .load(split[0])
                        .into(holderList.ivimg);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     onItemClickListener.onItemClickListener(position,view);
                    }
                });
                break;
            case 1:
                MyViewHolderGrid holderGrid= (MyViewHolderGrid) holder;
                holderGrid.titleg.setText(list.get(position).getTitle());
                holderGrid.priceg.setText(list.get(position).getBargainPrice()+"");
                String imagesg = list.get(position).getImages();
                String[] splitg = imagesg.split("\\|");
                Glide.with(context)
                        .load(splitg[0])
                        .into(holderGrid.ivimgg);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    onItemClickListener.onItemClickListener(position,view);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolderList extends RecyclerView.ViewHolder{

        public ImageView ivimg;
        public TextView title;
        public TextView price;

        public MyViewHolderList(View itemView) {
            super(itemView);
            ivimg = itemView.findViewById(R.id.iv_search_xq);
            title = itemView.findViewById(R.id.tv_search_title);
            price = itemView.findViewById(R.id.tv_search_price);
        }
    }
    class MyViewHolderGrid extends RecyclerView.ViewHolder{

        private final ImageView ivimgg;
        private final TextView titleg;
        private final TextView priceg;

        public MyViewHolderGrid(View itemView) {
            super(itemView);
            ivimgg = itemView.findViewById(R.id.iv_search_xqg);
            titleg = itemView.findViewById(R.id.tv_search_titleg);
            priceg = itemView.findViewById(R.id.tv_search_priceg);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int pos,View view);
    }

}
