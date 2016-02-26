package com.song.newsreader.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.song.newsreader.manager.ThreadManager;
import com.song.newsreader.utils.UIUtils;

/**
 * Created by Administrator on 2016/2/23.
 */
public abstract class LoadingPage extends FrameLayout{

    // 默认状态
    public static final int UN_LOADING = 1;
    // 加载状态
    public static final int LOADING = 2;
    // 加载失败状态
    public static final int ERROR = 3;
    // 加载成功,服务器没有返回数据
    public static final int EMPTY = 4;
    // 加载成功的状态
    public static final int SUCCESS = 5;
    // 用来记录某种状态
    private int mState;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private View mSuccessView;


    public LoadingPage(Context context) {
        super(context);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mState = UN_LOADING;

        mLoadingView = createLoadingView();
        if (null != mLoadingView){
            addView(mLoadingView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        mErrorView = createErrorView();
        if (null != mErrorView){
            addView(mErrorView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }

        mEmptyView = createEmptyView();
        if (null != mErrorView){
            addView(mEmptyView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }

        showSafePage();

    }

    private void showSafePage() {
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    public void showPage() {
        if (null != mLoadingView){
            mLoadingView.setVisibility(mState == UN_LOADING || mState == LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mErrorView){
            mErrorView.setVisibility(mState == ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != mEmptyView){
            mEmptyView.setVisibility(mState == EMPTY ? View.VISIBLE : View.INVISIBLE);
        }

        if (mState == SUCCESS && mSuccessView == null){
            mSuccessView = createSuccessView();
            if (null != mSuccessView){
                addView(mSuccessView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }

        if (null != mSuccessView){
            mSuccessView.setVisibility(mState == SUCCESS ? View.VISIBLE : View.INVISIBLE);
        }

    }

    public void show(){
        if (mState == ERROR || mState == EMPTY){
            mState = UN_LOADING;
        }
        if (mState == UN_LOADING){
            mState = LOADING;
            //此处调用发起网络请求代码
            Load();
        }
        showPage();
    }

    public void setmState(int mState) {
        this.mState = mState;
        showPage();
    }

    protected abstract void Load();

    public enum LoadResult{
        ERROR(3),EMPTY(4),SUCCESS(5);
        int value;
        LoadResult(int value){
            this.value = value;
        }
        public int getValue(){
            return value;
        }
    }

    public abstract View createSuccessView();

    private View createEmptyView() {
        return null;
    }

    private View createErrorView() {
        return null;
    }

    private View createLoadingView() {
        return null;
    }
}
