package com.bwie.aizhonghui.mymvp;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANGEROUS_HUI on 2017/9/28.
 */

public class MyApp extends Application{
    public static List<String> listsearch;
    @Override
    public void onCreate() {
        super.onCreate();
        listsearch=new ArrayList<>();
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
