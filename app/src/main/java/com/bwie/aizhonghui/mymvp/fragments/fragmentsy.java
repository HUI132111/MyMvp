package com.bwie.aizhonghui.mymvp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.aizhonghui.mymvp.Adapter.MyAdapter;
import com.bwie.aizhonghui.mymvp.Adapter.MyMxAdapter;
import com.bwie.aizhonghui.mymvp.Adapter.MyReadapter;
import com.bwie.aizhonghui.mymvp.Adapter.MySyGdAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Fl_li;
import com.bwie.aizhonghui.mymvp.Bean.Sy_MS;
import com.bwie.aizhonghui.mymvp.Bean.Sy_tou;
import com.bwie.aizhonghui.mymvp.Bean.Tj_bean;
import com.bwie.aizhonghui.mymvp.CustomScanActivity;
import com.bwie.aizhonghui.mymvp.R;
import com.bwie.aizhonghui.mymvp.SearchActivity;
import com.bwie.aizhonghui.mymvp.ShopXqActivity;
import com.bwie.aizhonghui.mymvp.common.Api;
import com.bwie.aizhonghui.mymvp.presenter.LoginPresenter;
import com.bwie.aizhonghui.mymvp.view.LoginView;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.library.ChooseEditText;
import com.library.OnChooseEditTextListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import view.xlistview.XListView;

/**
 * Created by DANGEROUS_HUI on 2017/10/3.
 */

public class fragmentsy extends Fragment implements LoginView, ViewPager.OnPageChangeListener, View.OnClickListener {
    private View view;
    private RecyclerView resy;
    private List<Integer> list;
    private LoginPresenter loginPresenter;
    private List<Sy_tou> toulist;
    private List<Tj_bean> tjlist;
    private MyAdapter ma;
    private MyMxAdapter mxma;
    private List<ImageView> limg;
    private List<ImageView> limggd;
    private ImageView ivsao;
    private List<Sy_tou> sy_tous;
    private ViewPager vptou;
    private LinearLayout lly;
    private ViewPager vptoutwo;
    private RecyclerView remx;
    private LinearLayout llytwo;
    private View headview;
    private View twoview;
    private View headmx;
    private List<View> gdvilist;
    private List<Fl_li> gdlist;
    private List<Fl_li> gdlisttwo;
    private int MX_Time;
    private List<Fl_li> syfllist;
    private SwipeRefreshLayout swiperefre;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            int current=vptou.getCurrentItem();
            current++;
            vptou.setCurrentItem(current);
//            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();
                if (mHour<10){
                    tvHour.setText("0"+mHour+"");
                }else {
                    tvHour.setText("0"+mHour+"");
                }
                if (mMin<10){
                    tvMinute.setText("0"+mMin+"");
                }else {
                    tvMinute.setText(mMin+"");
                }
                if (mSecond<10){
                    tvSecond.setText("0"+mSecond+"");
                }else {
                    tvSecond.setText(mSecond+"");
                }
            }
        }
    };
    private List<Tj_bean> tj_been;
    private List<Sy_MS.MiaoshaBean.ListBeanX> mxlist;
    private TextView tv_mitime;
    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvSecond;
    private long mHour = 02;
    private long mMin = 00;
    private long mSecond = 00;
    private boolean isRun = true;
    private List<Fl_li> fl_lis;
    private View headfoot;
    private EditText etsearch;
    private Timer timer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.item_sy,null);
        initView();
        initData();
        initdData();
        initdGdData();
        initMxData();
        initOkVpTou();
        intOkGvF();
        return view;
    }

    private List<Fl_li> intOkGvF() {
        OkHttpClient okHttpClient=new OkHttpClient();
        final Request request=new Request.Builder().get().url(Api.IMG_FL).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null&&response.isSuccessful()){
                    final String result = response.body().string();
                    System.out.println("======"+result);
                    try {
                        JSONObject obj = new JSONObject(result);
                        JSONArray data = obj.optJSONArray("data");
                        if(data!=null&&data.length()>0){
                            syfllist=new ArrayList<Fl_li>();
                            for (int i = 0; i <data.length(); i++) {
                                Fl_li fll=new Fl_li();
                                JSONObject jsj = data.optJSONObject(i);
                                fll.img=jsj.optString("icon");
                                fll.name=jsj.optString("name");
                                syfllist.add(fll);
                            }

                        }
                        System.out.println("================"+result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return syfllist;
    }

    private void initdGdData() {
        limggd=new ArrayList<>();
        for (int i = 0; i <2; i++) {
            ImageView iv=new ImageView(getActivity());
            if(i==0){
                iv.setImageResource(R.drawable.select_);
            }else{
                iv.setImageResource(R.drawable.normal_);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.leftMargin=10;
            params.rightMargin=10;
            llytwo.addView(iv,params);
            limggd.add(iv);
        }
    }

    private void initGData() {
        gdlist=new ArrayList<>();
        for (int i = 0; i <fl_lis.size(); i++) {
           if(i<=9){
               gdlist.add(fl_lis.get(i));
           }
        }
        gdlisttwo=new ArrayList<>();
        for (int i = 0; i <fl_lis.size(); i++) {
            if(i>9){
                gdlisttwo.add(fl_lis.get(i));
            }
        }
        gdvilist=new ArrayList<>();
        for (int i = 0; i <2; i++) {
            GridView gview= (GridView) View.inflate(getActivity(),R.layout.item_sygd,null);
            if(i==0){
                gview.setAdapter(new MySyGdAdapter(getActivity(),gdlist));
                gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(getActivity(),"kkkkk"+gdlist.get(i).name,Toast.LENGTH_SHORT);
                    }
                });
            }else{
                gview.setAdapter(new MySyGdAdapter(getActivity(),gdlisttwo));
                gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getActivity(),"kkkkk"+gdlisttwo.get(i).name,Toast.LENGTH_SHORT);
                    }
                });
            }
            gdvilist.add(gview);
        }


    }

    private void initdData() {
        limg=new ArrayList<>();
        for (int i = 0; i <4; i++) {
            ImageView iv=new ImageView(getActivity());
            if(i==0){
                iv.setImageResource(R.drawable.select_);
            }else{
                iv.setImageResource(R.drawable.normal_);
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            params.leftMargin=10;
            params.rightMargin=10;
            lly.addView(iv,params);
            limg.add(iv);
        }
    }

    private void initSetData() {
        //主RecyclerView
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        resy.setLayoutManager(gridLayoutManager);
        ma = new MyAdapter(tj_been,getActivity());
        resy.setAdapter(ma);
        initStart_End();
        //秒杀RecyclerView
        GridLayoutManager gridLayoutManagermx=new GridLayoutManager(getActivity(),1);
        gridLayoutManagermx.setOrientation(GridLayoutManager.HORIZONTAL);
        remx.setLayoutManager(gridLayoutManagermx);
        mxma=new MyMxAdapter(getActivity(),mxlist);
        remx.setAdapter(mxma);
        mxma.setOnItemClickListener(new MyMxAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int pos, View view) {

            }
        });

        ma.addHeaderView(headview);
        ma.addHeadertwoView(twoview);
        ma.addHeadermx(headmx);
        ma.addFooterView(headfoot);
        vptou.setAdapter(new MyPagerAdapter());
        initTimer();
        initGData();
        initTimeMx();
        vptoutwo.setAdapter(new MyPagerAdaptertwo(gdvilist));

                 ma.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
                     @Override
                     public void onItemClickListener(int pos, View view) {

                         Intent intent=new Intent(getActivity(), ShopXqActivity.class);
                         intent.putExtra("xqpid",tj_been.get(pos).pid+"");
                         getActivity().startActivity(intent);
                     }
                 });


    }

    private void initStart_End() {
        initLoadItem();
        initRefresh();
    }

    private void initRefresh() {
        swiperefre.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loginPresenter.vptousy();
            }
        });
    }

    private void initLoadItem() {
        resy.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==ma.getItemCount()){
                    Toast.makeText(getActivity(),"正在加载",Toast.LENGTH_SHORT).show();
                    tj_been.addAll(tj_been);
                    ma.notifyDataSetChanged();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem=layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initTimeMx() {
    }
    private void initMxData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 59;
                mHour--;
            }
        }
    }

    private void initTimer() {
        timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(0,3000);
            }
        };
        timer.schedule(task,0,3000);

    }

    private void initOkVpTou() {
     loginPresenter.vptousy();
    }

    private void initData() {
        loginPresenter =new LoginPresenter(getActivity(),this);
        list=new ArrayList<>();
        for (int i = 0; i <19 ; i++) {
            list.add(R.drawable.fl_n);
        }
    }

    private void initView() {
        swiperefre=view.findViewById(R.id.swiperesh);
        resy=view.findViewById(R.id.recycler_sy);
        ivsao=view.findViewById(R.id.iv_ew);
        ivsao.setOnClickListener(this);
        //搜索功能
        etsearch = view.findViewById(R.id.et_sy_search);
        etsearch.setOnClickListener(this);
        etsearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                etsearch.setInputType(InputType.TYPE_NULL);
                return false;
            }
        });
        //至此
        //得到头部，分类
        headview=View.inflate(getActivity(),R.layout.item_sytou,null);
        twoview=View.inflate(getActivity(),R.layout.item_sytou,null);
        headmx=View.inflate(getActivity(),R.layout.item_mx,null);
        headfoot = View.inflate(getActivity(), R.layout.item_syfoot,null);
        //头部小点功能
        lly=headview.findViewById(R.id.lly_dian);
        vptou=headview.findViewById(R.id.vp_sytou);
        vptou.setOnPageChangeListener(this);
        //第二头部小点功能
        llytwo=twoview.findViewById(R.id.lly_dian);
        vptoutwo=twoview.findViewById(R.id.vp_sytou);
        vptoutwo.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <2; i++) {
                    if(i==position){
                        limggd.get(i).setImageResource(R.drawable.select_);
                    }else{
                        limggd.get(i).setImageResource(R.drawable.normal_);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //秒杀倒计时
        tv_mitime = headmx.findViewById(R.id.tv_mi);
        tvHour = headmx.findViewById(R.id.tv_hour);
        tvMinute = headmx.findViewById(R.id.tv_minute);
        tvSecond = headmx.findViewById(R.id.tv_second);
        //秒杀商品
        remx=headmx.findViewById(R.id.re_mx);
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
        System.out.println("====="+msg);
          sy_tous= JSONjie(msg);
          mxlist = GsonMx(msg);
          tj_been = JSONtj(msg);
          fl_lis = intOkGvF();
          initSetData();
          swiperefre.setRefreshing(false);

    }

    @Override
    public void updateSuccess(String code, String msg) {

    }

    private List<Tj_bean> JSONtj(String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONObject tuijian = obj.getJSONObject("tuijian");
            String name = tuijian.optString("name");
            JSONArray list = tuijian.optJSONArray("list");
            if(list!=null&&list.length()>0){
                tjlist=new ArrayList<>();
                for (int i = 0; i <list.length(); i++) {
                    JSONObject tjs = list.getJSONObject(i);
                    Tj_bean tj=new Tj_bean();
                    tj.img=tjs.optString("images");
                    tj.title=tjs.optString("title");
                    tj.price=tjs.optInt("bargainPrice");
                    tj.pid=tjs.optInt("pid");
                    tjlist.add(tj);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
      return  tjlist;
    }
    private List<Sy_MS.MiaoshaBean.ListBeanX> GsonMx(String msg) {
        Gson gson=new Gson();
        Sy_MS sy_ms = gson.fromJson(msg, Sy_MS.class);
        Sy_MS.MiaoshaBean miaosha = sy_ms.getMiaosha();
        MX_Time = miaosha.getTime();
        List<Sy_MS.MiaoshaBean.ListBeanX> list = miaosha.getList();
        return list;
    }

    private List<Sy_tou> JSONjie(String msg) {
        try {
            JSONObject obj = new JSONObject(msg);
            JSONArray data = obj.getJSONArray("data");
            if(data!=null&&data.length()>0){
                toulist=new ArrayList<>();
                for (int i = 0; i <data.length(); i++) {
                    Sy_tou st=new Sy_tou();
                    JSONObject js = data.getJSONObject(i);
                    st.icon=js.optString("icon");
                    st.url=js.optString("url");
                    toulist.add(st);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toulist;
    }

    @Override
    public void loginFail(String code, String msg) {

    }

    @Override
    public void failure(Call call, IOException e) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i <4; i++) {
            if(i==position%4){
                limg.get(i).setImageResource(R.drawable.select_);
            }else{
                limg.get(i).setImageResource(R.drawable.normal_);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ew:
                customScan();
                break;
            case R.id.et_sy_search:
                startActivity(new Intent(getActivity(),SearchActivity.class));
                break;
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(getActivity(),R.layout.item_syvpimg,null);
            ImageView ivimg = view.findViewById(R.id.iv_vpimg);
            System.out.println("==="+sy_tous.get(position%4).icon);
            Glide.with(getActivity())
                    .load(sy_tous.get(position%4).icon)
                    .into(ivimg);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }

    class MyPagerAdaptertwo extends PagerAdapter{
        private List<View> tlist;

        public MyPagerAdaptertwo(List<View> tlist) {
            this.tlist = tlist;
        }

        @Override
        public int getCount() {
            return tlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(tlist.get(position));
            return tlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    public void customScan(){
        new IntentIntegrator(getActivity())
                .setOrientationLocked(false)
                .setCaptureActivity(CustomScanActivity.class)
                .initiateScan();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("偶发问哦告诉大家佛诶温暖");
       timer.cancel();
    }
}
