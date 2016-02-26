package com.song.newsreader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.song.newsreader.R;
import com.song.newsreader.holder.BaseHolder;
import com.song.newsreader.holder.MoreHolder;
import com.song.newsreader.holder.NewsNoPicHolder;
import com.song.newsreader.holder.NewsOnePicHolder;
import com.song.newsreader.holder.NewsThreePicHolder;
import com.song.newsreader.http.NewsHttpHelper;
import com.song.newsreader.http.NewsReponse;
import com.song.newsreader.utils.LogUtils;
import com.song.newsreader.widget.LoadingPage;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class NewsListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int ITEM_NO_PIC = 0;
    private static final int ITEM_ONE_PIC = 1;
    private static final int ITEM_THREE_PIC = 2;
    private static final int ITEM_VIEW_MORE = 3;

    private String mParam1;
    private String mParam2;
    private ListView lvList;
    private List<NewsReponse.DataEntity> mDatas;

    public NewsListFragment() {
        // Required empty public constructor
    }

    public static NewsListFragment newInstance(String param1, String param2) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        lvList = (ListView) view.findViewById(R.id.lv_list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData(){
        new NewsHttpHelper().loadNews(new NewsHttpHelper.NewsCallBack() {

            @Override
            public void onSuccess(NewsReponse reponse) {
                LogUtils.d("访问网络成功");
            }

        });
    }

    private void initDataBak() {
        String url = "http://cr.m.liebao.cn/news/fresh?model=vivo+X5S+L&count=10&mcc=460&osv=4.4.4&ctype=0x1EB&pid=3&display=0xCF&aid=a5ea9d8185da00af&net=wifi&nmnc=00&mode=1&appv=3.27.12&v=3&pf=android&scenario=0x00000101&app_lan=zh_CN&lan=zh_CN&action=0x0f&brand=vivo&nmcc=460&mnc=02&uuid=edzt45hov5yilamsb5getlfligww&ch=20000027&offset=&act=3";

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<NewsReponse>() {

            @Override
            public void onSuccess(NewsReponse response) {
                mDatas = response.getData();
                if (null != mDatas) {
                    processResult();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void processResult() {
        Myadapter adapter = new Myadapter();
        lvList.setAdapter(adapter);
    }

    class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public NewsReponse.DataEntity getItem(int position) {
            return mDatas.get(position);
        }

        @Override
        public int getItemViewType(int position) {
                NewsReponse.DataEntity item = getItem(position);
                int itemType = 0;
                switch (item.getImages().size()) {
                    case 0:
                        itemType = ITEM_NO_PIC;
                        break;
                    case 1:
                    case 2:
                        itemType = ITEM_ONE_PIC;
                        break;
                    case 3:
                        itemType = ITEM_THREE_PIC;
                }
                return itemType;
        }

        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 2;
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
                switch (getItemViewType(position)){
                    case ITEM_NO_PIC:
                        holder = new NewsNoPicHolder();
                        break;
                    case ITEM_ONE_PIC:
                        holder = new NewsOnePicHolder();
                        break;
                    case ITEM_THREE_PIC:
                        holder = new NewsThreePicHolder();
                        break;
                    default:
                        holder = new NewsNoPicHolder();
                        break;
                }
            }
            holder.setData(getItem(position));
            return holder.getmRootView();
        }
    }

}
