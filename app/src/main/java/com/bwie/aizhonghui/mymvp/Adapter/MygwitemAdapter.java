package com.bwie.aizhonghui.mymvp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Gw_bean;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.presenter.ShopCarUpdatePresenter;
import com.bwie.aizhonghui.mymvp.view.ShopCarUpdateView;
import com.bwie.aizhonghui.mymvp.viewfreedom.AmmountView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/18.
 */

public class MygwitemAdapter extends RecyclerView.Adapter<MygwitemAdapter.MyViewHolder> implements ShopCarUpdateView{
    private Context context;
    private List<Gw_bean.DataBean.ListBean> list;
    private ShopCarUpdatePresenter shopCarUpdatePresenter;
    private SharedPreferences sp;
    private String loginuid;
    private Moneysum moneysum;
    private OnRecyclerItemLongListener mOnItemLong=null;

    public MygwitemAdapter(Context context,List<Gw_bean.DataBean.ListBean> list) {
        this.context = context;
        this.list=list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        shopCarUpdatePresenter=new ShopCarUpdatePresenter((Activity) context,this);
        sp=context.getSharedPreferences("LOGIN",context.MODE_PRIVATE);
        loginuid= sp.getString("LOGIN", null);
        View view=View.inflate(context, R.layout.item_gw_ry_item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }
    public void IMoneysum(Moneysum moneysum){
        this.moneysum=moneysum;
    }

    public interface Moneysum{
        void  Xmoneysum(String uid,String sellerid,String pid,String num,String selected);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        int selected =list.get(position).getSelected();
        System.out.println("+popopopopoo++"+selected);
        if(selected==0){
            holder.ckbsdan.setChecked(false);
        }else if(selected==1){
            holder.ckbsdan.setChecked(true);
        }
        holder.title.setText(list.get(position).getTitle());
      holder.nei.setText(list.get(position).getSubhead());
      holder.price.setText(list.get(position).getBargainPrice()+"");
      holder.mAmountView.setGoods_storage(50);
      holder.mAmountView.setAmount(list.get(position).getNum());
      holder.mAmountView.setOnAmountChangeListener(new AmmountView.OnAmountChangeListener() {
          @Override
          public void onAmountChange(View view, int amount) {
              loginuid = sp.getString("LOGIN", null);
                Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();

              if(loginuid!=null){
                  System.out.println("=="+loginuid+"=="+list.get(position).getSellerid()+""+"=="+list.get(position).getPid()+""+"=="+amount+""+"=="+list.get(position).getSelected()+"");
                  moneysum.Xmoneysum(loginuid,list.get(position).getSellerid()+"",list.get(position).getPid()+"",amount+"",list.get(position).getSelected()+"");
              }
          }
      });
       // System.out.println("fs555578455"+list.get(position).getImages());
        String images = list.get(position).getImages();
        String[] strs = images.split("\\|");
        Glide.with(context)
                .load(strs[0])
                .into(holder.ivimg);
        holder.ckbsdan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    System.out.println("sfegdeggggsef "+loginuid+list.get(position).getSellerid()+""+list.get(position).getPid()+""+list.get(position).getNum()+""+1+"");
                     moneysum.Xmoneysum(loginuid,list.get(position).getSellerid()+"",list.get(position).getPid()+"",list.get(position).getNum()+"",1+"");
                }else {
                    moneysum.Xmoneysum(loginuid,list.get(position).getSellerid()+"",list.get(position).getPid()+"",list.get(position).getNum()+"",0+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void ShopCarUpdateSuccess(String code, String msg) {
        System.out.println("==msg=ddd="+msg);
    }

    @Override
    public void ShopCarUpdateFail(String code, String msg) {

    }

    @Override
    public void ShopCarUpdateError(Call call, IOException e) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private final ImageView ivimg;
        private final TextView title;
        private final TextView nei;
        private final TextView price;
        private final AmmountView mAmountView;
        private final CheckBox ckbsdan;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivimg = itemView.findViewById(R.id.iv_gw_shop_image);
            title = itemView.findViewById(R.id.tv_gw_shop_title);
            nei = itemView.findViewById(R.id.tv_gw_shop_nei);
            price = itemView.findViewById(R.id.tv_gw_shop_price_dan);
            ckbsdan = itemView.findViewById(R.id.ckb_gw_shop_dan);
            mAmountView = itemView.findViewById(R.id.amount_view);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            if(mOnItemLong!=null){
                mOnItemLong.onItemLongClick(v,getPosition());
            }
            return true;
        }
    }

    public void setOnItemLongClickListener(OnRecyclerItemLongListener listener){
       this.mOnItemLong=listener;
    }

    public interface OnRecyclerItemLongListener{
        void onItemLongClick(View view,int position);
    }
}
