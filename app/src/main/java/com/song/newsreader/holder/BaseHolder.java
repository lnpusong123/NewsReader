package com.song.newsreader.holder;

import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public abstract class BaseHolder<T> {

    private View mRootView;

    private T mData;

    public BaseHolder() {
        mRootView = initView();
        mRootView.setTag(this);
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        this.mData = data;
        updateView();
    }

    protected abstract View initView();

    protected abstract void updateView();

    public View getmRootView() {
        return mRootView;
    }
}
