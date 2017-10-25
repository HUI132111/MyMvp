package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.aizhonghui.mymvp.Adapter.MyScHistory;

import java.util.ArrayList;
import java.util.List;

import static com.bwie.aizhonghui.mymvp.MyApp.listsearch;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvsearch;
    private EditText etsearch;
    private List<String> list;
    private MyScHistory mh;
    private ListView lv;
    private ImageView ivtui;
    private SharedPreferences sp;
    private StringBuffer sb=new StringBuffer();
    private String shistory;
    private RelativeLayout clearls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initData();
        if(mh==null){
            mh=new MyScHistory(SearchActivity.this,list);
            lv.setAdapter(mh);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(SearchActivity.this,SearchXqActivity.class);
                    intent.putExtra("name",list.get(i));
                    startActivity(intent);
                    finish();
                }
            });
        }else {
            mh.notifyDataSetChanged();
        }
    }

    private void initData() {
        list=new ArrayList<>();
        String searchhistory = sp.getString("searchhistory", null);
        if(searchhistory!=null){
            sb.append(searchhistory);
            String[] split = searchhistory.split(",");
            for (int i = 1; i <split.length; i++) {
                list.add(split[i]);
            }
        }
    }

    private void initView() {
        sp=getSharedPreferences("ScHistory",MODE_PRIVATE);
        clearls = (RelativeLayout) findViewById(R.id.btn_clear_ls);
        tvsearch = (TextView) findViewById(R.id.tv_search);
        etsearch = (EditText) findViewById(R.id.et_search);
        ivtui = (ImageView) findViewById(R.id.iv_search_tui);
        lv = (ListView) findViewById(R.id.lv_search_hostroy);
        tvsearch.setOnClickListener(this);
        ivtui.setOnClickListener(this);
        clearls.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search:
                String s = etsearch.getText().toString();
                 shistory= sb.append("," + s).toString();
                sp.edit().putString("searchhistory",shistory).commit();
                list.add(s);
                if(mh==null){
                    mh=new MyScHistory(SearchActivity.this,list);
                    lv.setAdapter(mh);
                }else {
                    mh.notifyDataSetChanged();
                }
                Intent intent=new Intent(SearchActivity.this,SearchXqActivity.class);
                intent.putExtra("name",s);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_clear_ls:
                sp.edit().clear().commit();
                list.clear();
                mh.notifyDataSetChanged();
                break;
            case R.id.iv_search_tui:
                finish();
                break;
        }
    }
}
