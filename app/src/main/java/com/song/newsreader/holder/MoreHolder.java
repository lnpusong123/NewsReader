package com.song.newsreader.holder;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.song.newsreader.R;
import com.song.newsreader.adapter.MyBaseAdapter;
import com.song.newsreader.fragment.IndexFragment;
import com.song.newsreader.utils.UIUtils;

/**
 * Created by Administrator on 2016/2/23.
 */
public class MoreHolder extends BaseHolder<Integer> implements View.OnClickListener {

    public static final int HAS_MORE = 1;
    public static final int NO_MORE = 2;
    public static final int ERROR = 3;

    private RelativeLayout rl_more_loading;
    private RelativeLayout rl_more_error;

    private IndexFragment.IndexAdapter adapter;

    public MoreHolder(boolean hasMore, IndexFragment.IndexAdapter adapter) {
        setData(hasMore ? HAS_MORE : NO_MORE);
        this.adapter = adapter;
    }

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_listview_more_loading, null);
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        rl_more_error.setOnClickListener(this);
        return view;
    }

    @Override
    protected void updateView() {
        Integer data = getData();
        rl_more_error.setVisibility(data == ERROR ? View.VISIBLE : View.INVISIBLE);
        rl_more_loading.setVisibility(data == HAS_MORE ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public View getmRootView() {
        if (getData() == HAS_MORE){
            adapter.loadMore();
        }
        return super.getmRootView();
    }

    @Override
    public void onClick(View v) {
        adapter.loadMore();
    }
}
