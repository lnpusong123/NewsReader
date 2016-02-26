package com.song.newsreader.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.song.newsreader.holder.BaseHolder;
import com.song.newsreader.holder.MoreHolder;
import com.song.newsreader.manager.ThreadManager;
import com.song.newsreader.utils.UIUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{

    private List<T> mDatas;

    //表示加载更多
    protected static final int ITEM_MORE_TYPE = 0;
    //表示普通数据类型
    protected static final int ITEM_VIEW_TYPE = 1;
    //表示无图片类型
    protected static final int ITEM_NO_PIC_TYPE = 2;
    //表示有一张图片类型
    protected static final int ITEM_ONE_PIC_TYPE = 3;
    //有三张图片类型
    protected static final int ITEM_THREE_PIC_TYPE = 4;
    //段子类型
    protected static final int ITEM_PIECE_TYPE = 5;
    //视频类型
    protected static final int ITEM_VIDEO_TYPE = 6;

    private MoreHolder moreHolder;

    //加载标志
    private boolean isLoading = false;

    public MyBaseAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> Datas) {
        this.mDatas = Datas;
    }

    @Override
    public int getCount() {
        return mDatas.size() + 1;
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1){
            return ITEM_MORE_TYPE;
        }
        return getInnerItemViewType(position);
    }

    protected int getInnerItemViewType(int position) {
        return ITEM_VIEW_TYPE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder;
        if (convertView!=null){
            holder = (BaseHolder) convertView.getTag();
        }else {
            if (getItemViewType(position) == ITEM_MORE_TYPE){
                holder = getMoreHolder();
            }else {
                holder = getHolder();
            }
        }
        if (getItemViewType(position) == ITEM_VIEW_TYPE){
            holder.setData(getItem(position));
        }
        return holder.getmRootView();
    }

    private BaseHolder getMoreHolder() {
        if (moreHolder == null){
            //moreHolder = new MoreHolder(hasMore(), this);
        }
        return moreHolder;
    }

    protected boolean hasMore() {
        return true;
    }

    protected abstract BaseHolder getHolder();

    public void loadMore(){
        if (!isLoading){
            isLoading = true;
            ThreadManager.getLongPool().execute(new Runnable() {
                @Override
                public void run() {
                    final List list = onLoadMore();
                    UIUtils.runInMainThread(new Runnable() {
                        @Override
                        public void run() {
                            if (null == list){
                                getMoreHolder().setData(MoreHolder.ERROR);
                            }else if (list.size() < 10){
                                getMoreHolder().setData(MoreHolder.NO_MORE);
                            }else {
                                getMoreHolder().setData(MoreHolder.HAS_MORE);
                            }

                            if (null != list){
                                if (mDatas!=null){
                                    mDatas.addAll(list);
                                }else {
                                    setDatas(list);
                                }
                            }
                            notifyDataSetChanged();
                            isLoading = false;

                        }
                    });
                }
            });
        }
    }

    protected abstract List onLoadMore();

}
