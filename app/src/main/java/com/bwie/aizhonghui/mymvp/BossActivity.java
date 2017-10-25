package com.bwie.aizhonghui.mymvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.aizhonghui.mymvp.fragments.fragmentgw;

public class BossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);
        initView();
    }

    private void initView() {
      getSupportFragmentManager().beginTransaction().replace(R.id.boss_fragment,new fragmentgw()).commit();
    }
}
