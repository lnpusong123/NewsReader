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
import com.song.newsreader.http.NewsReponse;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class NewsListFragmentBak extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int ITEM_NO_PIC = 0;
    private static final int ITEM_ONE_PIC = 1;
    private static final int ITEM_THREE_PIC = 2;
    private String mParam1;
    private String mParam2;
    private ListView lvList;
    private List<NewsReponse.DataEntity> mDatas;

    public NewsListFragmentBak() {
        // Required empty public constructor
    }

    public static NewsListFragmentBak newInstance(String param1, String param2) {
        NewsListFragmentBak fragment = new NewsListFragmentBak();
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

    private void initData() {
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

    static class BaseViewHolder {
        public TextView tvTitle;
        public TextView tvSource;
    }

    static class OnePicViewHolder extends BaseViewHolder {
        public ImageView iv_icon1;
    }

    static class ThreeePicViewHolder extends OnePicViewHolder {
        public ImageView iv_icon2;
        public ImageView iv_icon3;
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
            BaseViewHolder holder = null;
            OnePicViewHolder onePicViewHolder = null;
            ThreeePicViewHolder threeePicViewHolder = null;
            if (convertView == null) {
                switch (getItemViewType(position)) {
                    case 0:
                        convertView = View.inflate(getActivity(), R.layout.item_listview_0, null);
                        holder = new BaseViewHolder();
                        holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                        holder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                        convertView.setTag(holder);
                        break;
                    case 1:
                        convertView = View.inflate(getActivity(), R.layout.item_listview_1, null);
                        onePicViewHolder = new OnePicViewHolder();
                        onePicViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                        onePicViewHolder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                        onePicViewHolder.iv_icon1 = (ImageView) convertView.findViewById(R.id.iv_icon1);
                        convertView.setTag(onePicViewHolder);
                        break;
                    case 2:
                        convertView = View.inflate(getActivity(), R.layout.item_listview_3, null);
                        threeePicViewHolder = new ThreeePicViewHolder();
                        threeePicViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                        threeePicViewHolder.tvSource = (TextView) convertView.findViewById(R.id.tv_source);
                        threeePicViewHolder.iv_icon1 = (ImageView) convertView.findViewById(R.id.iv_icon1);
                        threeePicViewHolder.iv_icon2 = (ImageView) convertView.findViewById(R.id.iv_icon2);
                        threeePicViewHolder.iv_icon3 = (ImageView) convertView.findViewById(R.id.iv_icon3);
                        convertView.setTag(threeePicViewHolder);
                        break;
                }

            } else {
                switch (getItemViewType(position)) {
                    case 0:
                        holder = (BaseViewHolder) convertView.getTag();
                        break;
                    case 1:
                        onePicViewHolder = (OnePicViewHolder) convertView.getTag();
                        break;
                    case 2:
                        threeePicViewHolder = (ThreeePicViewHolder) convertView.getTag();
                        break;
                }

            }
            NewsReponse.DataEntity item = getItem(position);
            switch (getItemViewType(position)) {
                case 0:
                    holder.tvTitle.setText(item.getTitle());
                    holder.tvSource.setText(item.getSource());
                    break;
                case 1:
                    onePicViewHolder.tvTitle.setText(item.getTitle());
                    onePicViewHolder.tvSource.setText(item.getSource());
                    Glide.with(getActivity()).load(item.getImages().get(0))
                            .centerCrop().placeholder(R.drawable.image_holder)
                            .crossFade().into(onePicViewHolder.iv_icon1);
                    break;
                case 2:
                    threeePicViewHolder.tvTitle.setText(item.getTitle());
                    threeePicViewHolder.tvSource.setText(item.getSource());
                    Glide.with(getActivity()).load(item.getImages().get(0))
                            .centerCrop().placeholder(R.drawable.image_holder)
                            .crossFade().into(threeePicViewHolder.iv_icon1);
                    Glide.with(getActivity()).load(item.getImages().get(1))
                            .centerCrop().placeholder(R.drawable.image_holder)
                            .crossFade().into(threeePicViewHolder.iv_icon2);
                    Glide.with(getActivity()).load(item.getImages().get(2))
                            .centerCrop().placeholder(R.drawable.image_holder)
                            .crossFade().into(threeePicViewHolder.iv_icon3);
                    break;
            }

            return convertView;
        }
    }

}
