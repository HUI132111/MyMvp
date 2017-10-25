package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Bean.Gw_bean;
import com.bwie.aizhonghui.mymvp.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DANGEROUS_HUI on 2017/10/18.
 */

public class MygwAdapter extends RecyclerView.Adapter<MygwAdapter.MyViewHolder> implements MygwitemAdapter.Moneysum{
    private Context context;
    private List<Gw_bean.DataBean> list;
    private MygwitemAdapter mgia;
    private LinearLayoutManager linearLayoutManager;
    private ZMoneysum zMoneysum;
    private SharedPreferences sp;
    private String uid_jia;
    private List<Integer> listja;

    public MygwAdapter(Context context, List<Gw_bean.DataBean> list,List<Integer> listja) {
        this.context = context;
        this.list = list;
        this.listja=listja;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        sp=context.getSharedPreferences("LOGIN",context.MODE_PRIVATE);
        uid_jia = sp.getString("LOGIN", null);
        View view=View.inflate(context, R.layout.item_gw_ry,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvshopjia.setText(list.get(position).getSellerName());
        if(listja.get(position)==9){
            holder.cbja.setChecked(true);
        }else if(listja.get(position)==6){
            holder.cbja.setChecked(false);
        }
        mgia=new MygwitemAdapter(context, this.list.get(position).getList());
        System.out.println("lllkkkkkk"+position);
        linearLayoutManager = new LinearLayoutManager(context);
        holder.rygwry.setLayoutManager(linearLayoutManager);
        holder.rygwry.setAdapter(mgia);
        mgia.IMoneysum(this);
        mgia.setOnItemLongClickListener(new MygwitemAdapter.OnRecyclerItemLongListener() {
            @Override
            public void onItemLongClick(View view, final int pp) {
                System.out.println("长按了但是没有子线程"+list.get(position).getList().get(pp).getPid()+"");
                AlertDialog.Builder ab=new AlertDialog.Builder(context);
                ab.setMessage("确定要删除么");
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        zMoneysum.Deleteshop(uid_jia,list.get(position).getList().get(pp).getPid()+"");
                        Toast.makeText(context,"长按了"+list.get(position).getList().get(pp).getPrice()+"",Toast.LENGTH_SHORT);
                    }
                });
                ab.setNegativeButton("再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ab.create().show();

            }
        });
        holder.cbja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheckbox) {
                if(isCheckbox){
                    List<Gw_bean.DataBean.ListBean> list = MygwAdapter.this.list.get(position).getList();
                    for (Gw_bean.DataBean.ListBean listBean : list) {
                        zMoneysum.JAmoneysum(uid_jia,listBean.getSellerid()+"",listBean.getPid()+"",listBean.getNum()+"",1+"",list.size(),"QUAN");
                    }
                }else {
                    List<Gw_bean.DataBean.ListBean> list = MygwAdapter.this.list.get(position).getList();
                    for (Gw_bean.DataBean.ListBean listBean : list) {
                        zMoneysum.JAmoneysum(uid_jia,listBean.getSellerid()+"",listBean.getPid()+"",listBean.getNum()+"",0+"",list.size(),"QUAN");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void Xmoneysum(String uid, String sellerid, String pid, String num, String selected) {
        System.out.println("滚股概念股概念股概念股概念股给你个");
        zMoneysum.XXmoneysum(uid,sellerid,pid,num,selected);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvshopjia;
        private final RecyclerView rygwry;
        private final CheckBox cbja;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvshopjia = itemView.findViewById(R.id.tv_gw_shopja);
            rygwry = itemView.findViewById(R.id.recycler_gw_ry);
            cbja = itemView.findViewById(R.id.ckb_gw_shopja);
        }
    }
    public void IIMoneysum(ZMoneysum zMoneysum){
        this.zMoneysum=zMoneysum;
    }

  public interface ZMoneysum{
      void XXmoneysum(String uid,String sellerid,String pid,String num,String selected);
      void JAmoneysum(String uid,String sellerid,String pid,String num,String selected,int shopmax,String quan);
      void Deleteshop(String uid,String pid);
  }

}
