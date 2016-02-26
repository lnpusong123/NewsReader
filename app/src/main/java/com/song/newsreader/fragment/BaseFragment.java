package com.song.newsreader.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.song.newsreader.R;
import com.song.newsreader.utils.LogUtils;
import com.song.newsreader.utils.UIUtils;
import com.song.newsreader.utils.ViewUtils;
import com.song.newsreader.widget.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    protected LoadingPage mContentPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LogUtils.d("onCreateView");
       if (mContentPage == null){
            mContentPage = new LoadingPage(UIUtils.getContext()) {
                @Override
                protected void Load() {
                    BaseFragment.this.load();
                }

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };
       }else {
           ViewUtils.removeSelfFromParent(mContentPage);
       }
        return mContentPage;
    }

    protected LoadingPage.LoadResult check(Object object){
        if (null == object){
            return LoadingPage.LoadResult.ERROR;
        }
        if (object instanceof List){
            List list = (List) object;
            if (list.size() == 0){
                return LoadingPage.LoadResult.EMPTY;
            }
        }
        return LoadingPage.LoadResult.SUCCESS;
    }

    //在load方法结束时需要设置LoadingPage的状态
    protected abstract void load();

    public abstract View createSuccessView() ;

    public void show() {
        if (null != mContentPage){
            mContentPage.show();
        }
    }

}
