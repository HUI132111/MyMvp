package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Fl_fra;
import com.bwie.aizhonghui.mymvp.R;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/10.
 */

public class MyFlFraTwoAdapter extends RecyclerView.Adapter<MyFlFraTwoAdapter.MyViewHolderfltwo>{
    private Context context;
    private List<Fl_fra.DataBean.ListBean> list;
    private OnItemOnclicklistener onItemOnclicklistener;

    public MyFlFraTwoAdapter(Context context, List<Fl_fra.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolderfltwo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_fl_jd_recy_recy,null);
        MyViewHolderfltwo myviewHolderfra=new MyViewHolderfltwo(view);
        return myviewHolderfra;
    }

    @Override
    public void onBindViewHolder(MyViewHolderfltwo holder, int position) {
        Glide.with(context)
                .load(list.get(position).getIcon())
                .into(holder.ivre);
       holder.tvname.setText(list.get(position).getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               onItemOnclicklistener.onItemonclicklistener();
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolderfltwo extends RecyclerView.ViewHolder{
        private ImageView ivre;
        private TextView tvname;
        public MyViewHolderfltwo(View itemView) {
            super(itemView);
               ivre=itemView.findViewById(R.id.iv_fl_re);
              tvname=itemView.findViewById(R.id.tv_fl_re_name);
        }
    }
    public void setOnItemclick(OnItemOnclicklistener onItemclick){
        this.onItemOnclicklistener=onItemclick;
    }

    public interface OnItemOnclicklistener{
        void onItemonclicklistener();
    }
}
