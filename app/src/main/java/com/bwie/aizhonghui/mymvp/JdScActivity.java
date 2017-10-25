package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bwie.aizhonghui.mymvp.fragments.fragmentfl;
import com.bwie.aizhonghui.mymvp.fragments.fragmentfx;
import com.bwie.aizhonghui.mymvp.fragments.fragmentgw;
import com.bwie.aizhonghui.mymvp.fragments.fragmentsy;
import com.bwie.aizhonghui.mymvp.fragments.fragmentwd;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

public class JdScActivity extends AppCompatActivity implements View.OnClickListener {
    private RadioButton sy;
    private RadioButton fenlei;
    private RadioButton find;
    private RadioButton gou;
    private RadioButton wd;
    private fragmentsy fsy;
    private fragmentfl ffl;
    private fragmentfx ffx;
    private fragmentgw fgw;
    private fragmentwd fwd;
    private long firstTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd_sc);
        initView();
        initDataFra();
    }

    private void initDataFra() {
         fsy=new fragmentsy();
         ffl=new fragmentfl();
         ffx=new fragmentfx();
         fgw=new fragmentgw();
         fwd=new fragmentwd();
        getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,fsy).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.my_fra,fsy).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.my_fra,ffl).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.my_fra,ffx).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.my_fra,fgw).commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.my_fra,fwd).commit();
//        getSupportFragmentManager().beginTransaction().show(fsy).commit();
//        getSupportFragmentManager().beginTransaction().hide(ffl).commit();
//        getSupportFragmentManager().beginTransaction().hide(ffx).commit();
//        getSupportFragmentManager().beginTransaction().hide(fgw).commit();
//        getSupportFragmentManager().beginTransaction().hide(fwd).commit();
    }

    private void initView() {
        sy= (RadioButton) findViewById(R.id.rb_sy);
        fenlei= (RadioButton) findViewById(R.id.rb_fenlei);
        find= (RadioButton) findViewById(R.id.rb_find);
        gou= (RadioButton) findViewById(R.id.rb_gou);
        wd= (RadioButton) findViewById(R.id.rb_wd);
        sy.setOnClickListener(this);
        fenlei.setOnClickListener(this);
        find.setOnClickListener(this);
        gou.setOnClickListener(this);
        wd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rb_sy:
                getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,fsy).commit();
//                getSupportFragmentManager().beginTransaction().show(fsy).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffl).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffx).commit();
//                getSupportFragmentManager().beginTransaction().hide(fgw).commit();
//                getSupportFragmentManager().beginTransaction().hide(fwd).commit();
                 //customScan();
                break;
            case R.id.rb_fenlei:
                getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,ffl).commit();
//                getSupportFragmentManager().beginTransaction().hide(fsy).commit();
//                getSupportFragmentManager().beginTransaction().show(ffl).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffx).commit();
//                getSupportFragmentManager().beginTransaction().hide(fgw).commit();
//                getSupportFragmentManager().beginTransaction().hide(fwd).commit();
                break;
            case R.id.rb_find:
                getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,ffx).commit();
//                getSupportFragmentManager().beginTransaction().hide(fsy).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffl).commit();
//                getSupportFragmentManager().beginTransaction().show(ffx).commit();
//                getSupportFragmentManager().beginTransaction().hide(fgw).commit();
//                getSupportFragmentManager().beginTransaction().hide(fwd).commit();
                break;
            case R.id.rb_gou:
                getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,fgw).commit();
//                getSupportFragmentManager().beginTransaction().hide(fsy).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffl).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffx).commit();
//                getSupportFragmentManager().beginTransaction().show(fgw).commit();
//                getSupportFragmentManager().beginTransaction().hide(fwd).commit();
                break;
            case R.id.rb_wd:
                getSupportFragmentManager().beginTransaction().replace(R.id.my_fra,fwd).commit();
//                getSupportFragmentManager().beginTransaction().hide(fsy).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffl).commit();
//                getSupportFragmentManager().beginTransaction().hide(ffx).commit();
//                getSupportFragmentManager().beginTransaction().hide(fgw).commit();
//                getSupportFragmentManager().beginTransaction().show(fwd).commit();
                break;
        }
    }
    public void customScan(){
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(CustomScanActivity.class)
                .initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(intentResult!=null){
            if(intentResult.getContents()==null){
                Toast.makeText(this,"内容为空",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_SHORT).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
//            if(requestCode==0 && resultCode==1003){
//                String result = data.getStringExtra("result");
//                System.out.println("====传递过来的个人信息属性==="+result);
//                try {
//                    JSONObject obj = new JSONObject(result);
//                    JSONObject dataj = obj.optJSONObject("data");
//                    String icon = dataj.optString("icon");
//                    //ImageLoader.getInstance().displayImage(icon,cv);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
         }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-firstTime>2000){
                Toast.makeText(JdScActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                firstTime=System.currentTimeMillis();
            }else{
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
