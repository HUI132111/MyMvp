package com.bwie.aizhonghui.mymvp;

import android.content.pm.PackageManager;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CustomScanActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener, View.OnClickListener {
     private Button swichLight,hint1Show,hint2Show;
     private DecoratedBarcodeView mDBV;
     private CaptureManager captureManager;
    private boolean isLightOn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scan);
        initView();
        mDBV.setTorchListener(this);
        if(!hasFlash()){
          swichLight.setVisibility(View.GONE);
        }
        captureManager=new CaptureManager(this,mDBV);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode,event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTorchOn() {
        Toast.makeText(this,"torch on",Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this,"torch off",Toast.LENGTH_LONG).show();
        isLightOn = false;
    }
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }
    private void initView() {
        swichLight= (Button) findViewById(R.id.btn_switch);
        hint1Show= (Button) findViewById(R.id.btn_hint1);
        hint2Show= (Button) findViewById(R.id.btn_hint2);
        mDBV= (DecoratedBarcodeView) findViewById(R.id.dbv_custom);
        swichLight.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_switch:
                if(isLightOn){
                    mDBV.setTorchOff();
                }else{
                    mDBV.setTorchOn();
                }
                break;
        }
    }
}
