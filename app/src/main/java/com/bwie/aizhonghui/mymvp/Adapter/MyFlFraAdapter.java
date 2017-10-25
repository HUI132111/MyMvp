package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Bean.Fl_fra;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.SearchXqActivity;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/9.
 */

public class MyFlFraAdapter extends RecyclerView.Adapter<MyFlFraAdapter.MyViewHolderfl>{
    private RecyclerView mRecyclerView;
    private Context context;
    private List<Fl_fra.DataBean> list;


    public MyFlFraAdapter(Context context, List<Fl_fra.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolderfl onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_fl_jd_recy,null);
        MyViewHolderfl myViewHolderfl=new MyViewHolderfl(view);
        return myViewHolderfl;
    }

    @Override
    public void onBindViewHolder(MyViewHolderfl holder, final int position) {
          holder.tvname.setText(list.get(position).getName());
          final List<Fl_fra.DataBean.ListBean> list = this.list.get(position).getList();
          GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
          holder.refltwo.setLayoutManager(gridLayoutManager);
          MyFlFraTwoAdapter myFlFraTwoAdapter=new MyFlFraTwoAdapter(context,list);
          holder.refltwo.setAdapter(myFlFraTwoAdapter);
          myFlFraTwoAdapter.setOnItemclick(new MyFlFraTwoAdapter.OnItemOnclicklistener() {
              @Override
              public void onItemonclicklistener() {
//                  Toast.makeText(context,"sddsd"+list.get(position).getPscid(),Toast.LENGTH_SHORT).show();
                  Intent in=new Intent(context, SearchXqActivity.class);
                  in.putExtra("Pscid",list.get(position).getPscid()+"");
                  context.startActivity(in);
              }
          });
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolderfl extends RecyclerView.ViewHolder{
        private RecyclerView refltwo;
        private TextView tvname;
        public MyViewHolderfl(View itemView) {
            super(itemView);
            refltwo= itemView.findViewById(R.id.re_fl_two);
            tvname=itemView.findViewById(R.id.tv_fl_name);
        }
    }
}
