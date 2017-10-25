package com.bwie.aizhonghui.mymvp.fragments;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Adapter.MyFlAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Fl_li;
import com.bwie.aizhonghui.mymvp.Bean.Sy_tou;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentJD;

import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentMAN;
import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentQQ;
import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentSM;
import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentTy;
import com.bwie.aizhonghui.mymvp.fragmentftwo.fragmentWMAN;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by DANGEROUS_HUI on 2017/10/3.
 */
public class fragmentfl extends Fragment implements LoginView, AdapterView.OnItemClickListener {
    private LoginPresenter loginPresenter;
    private View view;
    private ListView lv;
    private List<Fl_li> list;
    private MyFlAdapter mla;
    private List<Fl_li> fl_lis;
    private fragmentTy  ty;
    private List<Fragment> fragmentList;
    private SharedPreferences sp;
    private static int totalHeight = 0;//ListView高度

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.item_fl,null);
        sp=getActivity().getSharedPreferences("HUI",getActivity().MODE_PRIVATE);
        initView();
        initData();
        initOk();
        return view;
    }

    private void initData() {
        loginPresenter=new LoginPresenter(getActivity(),this);
    }

    private void initOk() {
       loginPresenter.fenlie();
    }

    private void initView() {
        lv=view.findViewById(R.id.lv_fl);

    }

    private void initaddFra() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fra_fl,new fragmentTy(fl_lis.get(0).cid)).commit();
//        fragmentList=new ArrayList<>();
//        for (int i = 0; i <19; i++) {
//            ty=new fragmentTy(fl_lis.get(i).cid);
//            fragmentList.add(ty);
//        }
//        for (int i = 0; i <fragmentList.size(); i++) {
//            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fra_fl,fragmentList.get(i)).commit();
//            if(i==0){
//                getActivity().getSupportFragmentManager().beginTransaction().show(fragmentList.get(i)).commit();
//            }else{
//                getActivity().getSupportFragmentManager().beginTransaction().hide(fragmentList.get(i)).commit();
//            }
       // }
    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressbar() {

    }

    @Override
    public void nameError(String msg) {

    }

    @Override
    public void passError(String msg) {

    }

    @Override
    public void loginSuccess(String code, String msg) {
        System.out.println("==sdfsdfdsfdsfdsfdsfdsfsf==="+msg);
        fl_lis = JSONjie(msg);
        initSetData();
    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    private void initSetData() {
        initaddFra();
      mla=new MyFlAdapter(getActivity(),fl_lis);
      lv.setAdapter(mla);
      lv.setOnItemClickListener(this);
    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }
    private List<Fl_li> JSONjie(String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONArray data = obj.getJSONArray("data");
            if(data!=null&&data.length()>0){
                list=new ArrayList<>();
                for (int i = 0; i <data.length(); i++) {
                    Fl_li fl=new Fl_li();
                    JSONObject js = data.getJSONObject(i);
                    fl.name=js.optString("name");
                    fl.cid=js.optString("cid");
                    list.add(fl);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        Toast.makeText(getActivity(),"吐司"+fl_lis.get(i).cid+fl_lis.get(i).name,Toast.LENGTH_SHORT).show();
        MyFlAdapter.mCurrentPos=i;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fra_fl,new fragmentTy(fl_lis.get(i).cid)).commit();
//        fragmentTy fragmentTy = new fragmentTy("");
//        Bundle bundle = new Bundle();
//        bundle.putString("","");
//        fragmentTy.setArguments(bundle);

        totalHeight = lv.getMeasuredHeight()-120;
        lv.smoothScrollToPositionFromTop(MyFlAdapter.mCurrentPos,totalHeight/2,50);
            mla.notifyDataSetChanged();
    }
}
