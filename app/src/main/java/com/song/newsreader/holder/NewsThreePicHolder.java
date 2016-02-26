package com.song.newsreader.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.newsreader.R;
import com.song.newsreader.http.NewsReponse;
import com.song.newsreader.utils.UIUtils;

/**
 * Created by Administrator on 2016/2/24.
 */
public class NewsThreePicHolder extends BaseHolder<NewsReponse.DataEntity> {
    private TextView tvTitle;
    private TextView tvSource;
    private ImageView iv_icon1;
    private ImageView iv_icon2;
    private ImageView iv_icon3;

    @Override
    protected View initView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.item_listview_3, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvSource = (TextView) view.findViewById(R.id.tv_source);
        iv_icon1 = (ImageView) view.findViewById(R.id.iv_icon1);
        iv_icon2 = (ImageView) view.findViewById(R.id.iv_icon2);
        iv_icon3 = (ImageView) view.findViewById(R.id.iv_icon3);
        return view;
    }

    @Override
    protected void updateView() {
        NewsReponse.DataEntity item = getData();
        tvTitle.setText(item.getTitle());
        tvSource.setText(item.getSource());
        Glide.with(UIUtils.getContext()).load(item.getImages().get(0))
                .centerCrop().placeholder(R.drawable.image_holder)
                .crossFade().into(iv_icon1);
        Glide.with(UIUtils.getContext()).load(item.getImages().get(1))
                .centerCrop().placeholder(R.drawable.image_holder)
                .crossFade().into(iv_icon2);
        Glide.with(UIUtils.getContext()).load(item.getImages().get(2))
                .centerCrop().placeholder(R.drawable.image_holder)
                .crossFade().into(iv_icon3);
    }
}
