package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.Adapter.MySearchAdapter;
import com.bwie.aizhonghui.mymvp.Bean.Search_str;
import com.bwie.aizhonghui.mymvp.itemSpace.SpacesItemDecoration;
import com.bwie.aizhonghui.mymvp.presenter.SearchPresenter;
import com.bwie.aizhonghui.mymvp.presenter.ZiSearchPresenter;
import com.bwie.aizhonghui.mymvp.view.SearchViewh;
import com.bwie.aizhonghui.mymvp.view.ZiSearchViewh;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class SearchXqActivity extends AppCompatActivity implements SearchViewh, ZiSearchViewh, View.OnClickListener {

    @BindView(R.id.tv_search_zh)
    TextView tvSearchZh;
    @BindView(R.id.tv_search_xl)
    TextView tvSearchXl;
    @BindView(R.id.tv_search_jg)
    TextView tvSearchJg;
    @BindView(R.id.tv_search_sx)
    TextView tvSearchSx;
    private EditText etsearch;
    private SearchPresenter searchPresenter;
    private ZiSearchPresenter ziSearchPresenter;
    private RecyclerView rysearch;
    private List<Search_str.DataBean> data;
    private MySearchAdapter ma;
    private MySearchAdapter mag;
    private ImageView searchmenu;
    private SharedPreferences sp;
    private ImageView ivtui;
    private int kkk=0;
    private String name;
    private String pscid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_xq);
        ButterKnife.bind(this);
        initView();
        initgetIntent();
    }

    private void initgetIntent() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        pscid = intent.getStringExtra("Pscid");
        if (name != null) {
            etsearch.setText(name);
            searchPresenter = new SearchPresenter(SearchXqActivity.this, this);
            searchPresenter.ISearch(name,"1","2");
        } else if (pscid != null) {
            ziSearchPresenter = new ZiSearchPresenter(SearchXqActivity.this, this);
            ziSearchPresenter.IziSearch(pscid,"1","2");
        }
    }

    private void initView() {
        sp = getSharedPreferences("SEARCH_MENU", MODE_PRIVATE);
        etsearch = (EditText) findViewById(R.id.et_searchxq);
        searchmenu = (ImageView) findViewById(R.id.iv_search_menu);
        ivtui = (ImageView) findViewById(R.id.iv_searchxq_tui);
        rysearch = (RecyclerView) findViewById(R.id.recycler_search);
        searchmenu.setOnClickListener(this);
        etsearch.setOnClickListener(this);
        ivtui.setOnClickListener(this);
        etsearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                etsearch.setInputType(InputType.TYPE_NULL);
                return false;
            }
        });
    }

    private void initLoadItem() {
        rysearch.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==ma.getItemCount()){
                    Toast.makeText(SearchXqActivity.this,"正在加载",Toast.LENGTH_SHORT).show();
                    data.addAll(data);
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

    @Override
    public void SearchSuccess(String code, String msg) {
        System.out.println("=====" + msg);
        System.out.println("===sdf ==" + msg);
        initGsonstr(msg);
    }

    private void initGsonstr(String msg) {
        Gson gson = new Gson();
        Search_str search_str = gson.fromJson(msg, Search_str.class);
        data = search_str.getData();
        initSetData();
    }

    private void initSetData() {
        boolean reason = sp.getBoolean("reason", false);
        ma = new MySearchAdapter(this, data, 0);
        mag = new MySearchAdapter(this, data, 1);
        initLoadItem();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchXqActivity.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        if(reason){
            rysearch.setLayoutManager(gridLayoutManager);
            rysearch.setAdapter(mag);
            mag.setOnItemClickListener(new MySearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int pos, View view) {
//                    Toast.makeText(SearchXqActivity.this,"水电费"+data.get(pos).getSellerid(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchXqActivity.this, ShopXqActivity.class);
                    intent.putExtra("xqpid",data.get(pos).getPid()+"");
                    startActivity(intent);
                }
            });
        }else {
            rysearch.setLayoutManager(linearLayoutManager);
            rysearch.setAdapter(ma);
            ma.setOnItemClickListener(new MySearchAdapter.OnItemClickListener() {
                @Override
                public void onItemClickListener(int pos, View view) {
                    Toast.makeText(SearchXqActivity.this,"水电费"+data.get(pos).getSellerid(),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SearchXqActivity.this, ShopXqActivity.class);
                    intent.putExtra("xqpid",data.get(pos).getPid()+"");
                    startActivity(intent);
                }
            });
        }


    }

    @Override
    public void SearchFail(String code, String msg) {

    }

    @Override
    public void SearchError(Call call, IOException e) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search_menu:
                boolean reason = sp.getBoolean("reason", false);
                if (reason) {
                    searchmenu.setImageResource(R.drawable.menug);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    rysearch.setLayoutManager(linearLayoutManager);
                    rysearch.setAdapter(ma);
                    sp.edit().putBoolean("reason", false).commit();
                } else {
                    searchmenu.setImageResource(R.drawable.menul);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
                    rysearch.setLayoutManager(gridLayoutManager);
                    rysearch.setAdapter(mag);
                    SpacesItemDecoration decoration = new SpacesItemDecoration(10);
                    rysearch.addItemDecoration(decoration);
                    sp.edit().putBoolean("reason", true).commit();
                }
                break;
            case R.id.et_searchxq:
                startActivity(new Intent(SearchXqActivity.this, SearchActivity.class));
                break;
            case R.id.iv_searchxq_tui:
                sp.edit().clear().commit();
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sp.edit().clear().commit();
    }

    @Override
    public void ziSearchSuccess(String code, String msg) {
        System.out.println("====dddd=" + msg);
        initGsonstr(msg);
    }

    @Override
    public void ziSearchFail(String code, String msg) {

    }

    @Override
    public void ziSearchError(Call call, IOException e) {

    }

    @OnClick({R.id.tv_search_zh, R.id.tv_search_xl, R.id.tv_search_jg, R.id.tv_search_sx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_search_zh:
                break;
            case R.id.tv_search_xl:
                if (name != null) {
                    etsearch.setText(name);
                    searchPresenter = new SearchPresenter(SearchXqActivity.this, this);
                    searchPresenter.ISearch(name,"1","1");
                } else if (pscid != null) {
                    ziSearchPresenter = new ZiSearchPresenter(SearchXqActivity.this, this);
                    ziSearchPresenter.IziSearch(pscid,"1","1");
                }
                break;
            case R.id.tv_search_jg:
//                kkk++;
//                Collections.sort(data, new Comparator<Search_str.DataBean>() {
//                    @Override
//                    public int compare(Search_str.DataBean dataBean, Search_str.DataBean t1) {
//                        int k =((int)dataBean.getPrice() - (int)t1.getPrice());
//                        if(kkk%2==1){
//                            if(k>0){
//                                return 0;
//                            }else{
//                                return -1;
//                            }
//                        }else{
//                            if(k>0){
//                                return -1;
//                            }else{
//                                return 0;
//                            }
//
//                        }
//                    }
//                });
//                ma.notifyDataSetChanged();
                if (name != null) {
                    etsearch.setText(name);
                    searchPresenter = new SearchPresenter(SearchXqActivity.this, this);
                    searchPresenter.ISearch(name,"1","2");
                } else if (pscid != null) {
                    ziSearchPresenter = new ZiSearchPresenter(SearchXqActivity.this, this);
                    ziSearchPresenter.IziSearch(pscid,"1","2");
                }
                break;
            case R.id.tv_search_sx:
                break;
        }
    }
}
