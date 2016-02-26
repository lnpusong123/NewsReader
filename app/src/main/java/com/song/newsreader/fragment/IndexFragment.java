package com.song.newsreader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.song.newsreader.R;
import com.song.newsreader.activity.DetailActivity;
import com.song.newsreader.global.Constans;
import com.song.newsreader.holder.BaseHolder;
import com.song.newsreader.holder.MoreHolder;
import com.song.newsreader.holder.NewsNoPicHolder;
import com.song.newsreader.holder.NewsOnePicHolder;
import com.song.newsreader.holder.NewsThreePicHolder;
import com.song.newsreader.http.NewsHttpHelper;
import com.song.newsreader.http.NewsReponse;
import com.song.newsreader.utils.UIUtils;
import com.song.newsreader.widget.LoadingPage;
import com.yalantis.phoenix.PullToRefreshView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class IndexFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    //表示无图片类型
    protected static final int ITEM_NO_PIC_TYPE = 0;
    //表示有一张图片类型
    protected static final int ITEM_ONE_PIC_TYPE = 1;
    //有三张图片类型
    protected static final int ITEM_THREE_PIC_TYPE = 2;
    //表示加载更多
    protected static final int ITEM_MORE_TYPE = 3;
    //表示所属频道参数名
    private static final String SCENARIO = "scenario";
    //所属频道
    private String scenario = "0x00000101";

    private List<NewsReponse.DataEntity> mDatas;

    private BaseHolder moreHolder;
    private IndexAdapter mAdapter;
    private PullToRefreshView mPullToRefreshView;

    private boolean isLoading = false;

    public static IndexFragment newInstance(String scenario) {

        Bundle args = new Bundle();
        args.putString(SCENARIO, scenario);
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            scenario = getArguments().getString(SCENARIO);
        }
    }

    @Override
    protected void load() {
        new NewsHttpHelper(scenario).loadNews(new NewsHttpHelper.NewsCallBack() {
            @Override
            public void onSuccess(NewsReponse response) {
                if (mDatas != null) {
                    mDatas.clear();
                }
                mDatas = response.getData();
                mContentPage.setmState(LoadingPage.SUCCESS);
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
                if (mPullToRefreshView != null && isLoading) {
                    mPullToRefreshView.setRefreshing(false);
                    isLoading = false;
                }
            }
        });
    }


    @Override
    public View createSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.fragment_index, null);
        ListView listView = (ListView) view.findViewById(R.id.lv_list);
        mAdapter = new IndexAdapter();
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);

        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(true);
                        isLoading = true;
                        load();
                    }
                }, 100);
            }
        });

        return view;
    }

    private BaseHolder getHolder(int position) {
        BaseHolder holder;
        switch (mDatas.get(position).getImages().size()) {
            case 0:
                holder = new NewsNoPicHolder();
                break;
            case 1:
                holder = new NewsOnePicHolder();
                break;
            case 3:
                holder = new NewsThreePicHolder();
                break;
            default:
                holder = new NewsNoPicHolder();
                break;
        }
        return holder;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsReponse.DataEntity entity = mDatas.get(position);
        Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
        intent.putExtra("url", entity.getUrl());
        intent.putExtra("title", entity.getTitle());
        UIUtils.startActivity(intent);
    }

    private BaseHolder getMoreHolder() {
        if (moreHolder == null) {
            moreHolder = new MoreHolder(true, mAdapter);
        }
        return moreHolder;
    }

    public class IndexAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size() + 1;
        }

        @Override
        public NewsReponse.DataEntity getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public int getItemViewType(int position) {
            int type;
            if (position == getCount() - 1) {
                type = ITEM_MORE_TYPE;
            } else {
                switch (mDatas.get(position).getImages().size()) {
                    case 0:
                        type = ITEM_NO_PIC_TYPE;
                        break;
                    case 1:
                        type = ITEM_ONE_PIC_TYPE;
                        break;
                    case 3:
                        type = ITEM_THREE_PIC_TYPE;
                        break;
                    default:
                        type = ITEM_NO_PIC_TYPE;
                        break;
                }
            }
            return type;
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 3;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            BaseHolder holder;
            if (convertView != null) {
                holder = (BaseHolder) convertView.getTag();
            } else {
                if (getItemViewType(position) == ITEM_MORE_TYPE) {
                    holder = getMoreHolder();
                } else {
                    holder = getHolder(position);
                }
            }
            if (getItemViewType(position) != ITEM_MORE_TYPE) {
                holder.setData(getItem(position));
            }

            return holder.getmRootView();
        }

        public void loadMore() {
            new NewsHttpHelper(scenario).loadNews(new NewsHttpHelper.NewsCallBack() {
                @Override
                public void onSuccess(NewsReponse response) {
                    if (mDatas != null) {
                        mDatas.addAll(response.getData());
                        notifyDataSetChanged();
                    } else {
                        mDatas = response.getData();
                    }
                }
            });
        }

    }
}
