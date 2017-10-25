package com.bwie.aizhonghui.mymvp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class DhActivity extends AppCompatActivity {
    private Timer timer;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int arg1 = msg.arg1;
            if(arg1==0){
               startActivity(new Intent(DhActivity.this,JdScActivity.class));
                timer.cancel();
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dh);
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        TimerTask task=new TimerTask() {
            int kk=3;
            @Override
            public void run() {
              kk--;
              Message msg=Message.obtain();
                msg.arg1=kk;
              handler.sendMessage(msg);
            }
        };
        timer.schedule(task,1000,1000);
    }
}
