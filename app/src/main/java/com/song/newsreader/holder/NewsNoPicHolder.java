package com.song.newsreader.holder;

import android.view.View;
import android.widget.TextView;

import com.song.newsreader.R;
import com.song.newsreader.http.NewsReponse;
import com.song.newsreader.utils.UIUtils;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/2/24.
 */
public class NewsNoPicHolder extends BaseHolder<NewsReponse.DataEntity> {

    private TextView tvTitle;
    private TextView tvSource;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_listview_0,null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvSource = (TextView) view.findViewById(R.id.tv_source);
        return view;
    }

    @Override
    protected void updateView() {
        NewsReponse.DataEntity item = getData();
        tvTitle.setText(item.getTitle());
        tvSource.setText(item.getSource());
    }
}
