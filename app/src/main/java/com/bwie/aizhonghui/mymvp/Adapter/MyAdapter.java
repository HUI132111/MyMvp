package com.bwie.aizhonghui.mymvp.Adapter;

/**
 * Created by DANGEROUS_HUI on 2017/10/5.
 */
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Bean.Tj_bean;
import com.bwie.aizhonghui.mymvp.R;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsy on 2016/8/4.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private RecyclerView mRecyclerView;
    private OnItemClickListener onItemClickListener;
    private List<Tj_bean> list;
    private Context mContext;

    private View VIEW_FOOTER;
    private View VIEW_HEADER;
    private View VIEW_HEADERTWO;
    private View VIEW_HEADERMX;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;
    private int TYPE_HEADERTWO = 1003;
    private int TYPE_HEADERMX=1004;


    public MyAdapter(List<Tj_bean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public MyAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new MyHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new MyHolder(VIEW_HEADER);
        }else if (viewType == TYPE_HEADERTWO) {
            return new MyHolder(VIEW_HEADERTWO);
        }else if(viewType==TYPE_HEADERMX){
            return new MyHolder(VIEW_HEADERMX);
        }
        else {
            return new MyHolder(getLayout(R.layout.item_sy_lv));
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)&& !isHeadertwoView(position)&& !isHeadermx(position)) {
            if (haveHeaderView()) position--;
            if (haveHeadertwoView()) position--;
            if (haveHeadermx()) position--;
            ImageView iviv=holder.itemView.findViewById(R.id.iv_syzi);
            TextView title=holder.itemView.findViewById(R.id.tv_title);
            TextView price=holder.itemView.findViewById(R.id.tv_price);
            title.setText(list.get(position).title);
            price.setText(list.get(position).price+"");
            String img = list.get(position).img;
            String[] strs = img.split("\\|");
            for (int i = 0; i <strs.length; i++) {
                System.out.println("--------"+strs[i].toString());
            }
            Glide.with(mContext)
                    .load(strs[0].toString())
                    .into(iviv);
            final int finalPosition = position;
           // int pos = holder.getLayoutPosition();---------------------------------------------
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClickListener(finalPosition,view);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = (list == null ? 0 : list.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        if (VIEW_HEADERTWO!= null) {
            count++;
        }
        if (VIEW_HEADERMX != null) {
            count++;
        }
        return count;
    }
//==============================================================================================================
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        }else if (isHeadertwoView(position)) {
            return TYPE_HEADERTWO;
        }else if(isHeadermx(position)){
            return TYPE_HEADERMX;
        }
        else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            ifGridLayoutManager();
            notifyItemInserted(0);
        }

    }
    //======
    public void addHeadertwoView(View headertwoView) {
        if (haveHeadertwoView()) {
            throw new IllegalStateException("heartwoview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headertwoView.setLayoutParams(params);
            VIEW_HEADERTWO = headertwoView;
            ifGridLayoutManager();
            notifyItemInserted(1);
        }

    }
    //=======
    public void addHeadermx(View headermx){
      if(haveHeadermx()){
          throw new IllegalStateException("hearmx has already exists!");
      }else{
          ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
          headermx.setLayoutParams(params);
          VIEW_HEADERMX = headermx;
          ifGridLayoutManager();
          notifyItemInserted(2);
      }
    }


    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup =
                    ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)|| isHeadertwoView(position)|| isHeadermx(position)) ?
                            ((GridLayoutManager) layoutManager).getSpanCount() :
                            1;
                }
            });
        }
    }

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }
    private boolean haveHeadertwoView() {
        return VIEW_HEADERTWO != null;
    }
    private boolean haveHeadermx() {
        return VIEW_HEADERMX != null;
    }
    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }
    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }
    private boolean isHeadertwoView(int position) {
        return haveHeadertwoView()&& position == 1;
    }
    private boolean isHeadermx(int position) {
        return haveHeadermx()&& position == 2;
    }


    public static class MyHolder extends RecyclerView.ViewHolder {

        public MyHolder(View itemView) {
            super(itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClickListener(int pos,View view);
    }

}


