package com.song.newsreader.application;

import android.app.Application;
import android.os.Handler;

import com.song.newsreader.BuildConfig;

import org.xutils.x;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2016/2/23.
 */
public class BaseApplication extends Application {

    private static BaseApplication mContext;
    private static Handler mMainThreadHandler;
    private static Thread mMainThread;
    private static int mMainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        BaseApplication.mContext = this;
        BaseApplication.mMainThreadHandler = new Handler();
        BaseApplication.mMainThread = Thread.currentThread();
        BaseApplication.mMainThreadId = android.os.Process.myTid();

        ShareSDK.initSDK(this);

    }


    public static BaseApplication getApplication(){
        return mContext;
    }

    public static Handler getMainThreadHandler(){
        return mMainThreadHandler;
    }

    public static Thread getMainThread(){
        return mMainThread;
    }

    public static int getMainThreadId(){
        return mMainThreadId;
    }

}
