package com.bwie.aizhonghui.mymvp.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

public class Myshopxq_tab extends FragmentPagerAdapter{
    private Context context;
    private List<Fragment> list;
    private String[] title={"商品","详情","评价"};

    public Myshopxq_tab(FragmentManager fm, Context context, List<Fragment> list) {
        super(fm);
        this.context = context;
        this.list = list;
    }

    public Myshopxq_tab(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
