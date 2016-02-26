package com.song.newsreader.http;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/2/24.
 */
public class NewsHttpHelperTest {

    @Test
    public void testLoadNews() throws Exception {
        new NewsHttpHelper().loadNews(new NewsHttpHelper.NewsCallBack() {
            @Override
            public void onSuccess(NewsReponse reponse) {
                Log.d("song","访问成功");
            }
        });
    }
}