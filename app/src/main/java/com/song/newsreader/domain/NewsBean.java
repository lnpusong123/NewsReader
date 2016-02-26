package com.song.newsreader.domain;

import com.song.newsreader.http.NewsReponse;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class NewsBean {
    public int ret;
    public String msg;
    public String stime;
    public List<News> data;

    @Override
    public String toString() {
        return data.toString();
    }

    public class News{
        public String contentid;
        public String ctype;
        public String title;
        public String author;
        public String summary;
        public String bodysize;
        public String pubtime;
        public List<String> images;
        public String display;
        public String source;
        public String originalurl;
        public String url;
        public String action;

        @Override
        public String toString() {
            return contentid + "-" + title;
        }
    }
}
