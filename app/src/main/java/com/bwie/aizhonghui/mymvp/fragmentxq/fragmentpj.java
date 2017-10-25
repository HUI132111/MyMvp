package com.bwie.aizhonghui.mymvp.fragmentxq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.aizhonghui.mymvp.R;

/**
 * Created by DANGEROUS_HUI on 2017/10/17.
 */

public class fragmentpj extends Fragment{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.item_shop_xq_pj,null);
        return view;
    }
}
