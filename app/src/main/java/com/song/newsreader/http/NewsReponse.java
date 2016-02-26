package com.song.newsreader.http;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class NewsReponse {


    /**
     * ret : 0
     * msg : ok
     * count : 10
     * stime : 1456215904
     * data : [{"contentid":"32529402","ctype":"0x01","title":"手握不动笔洗澡超4小时 29岁女子：丢了工作黄了对象","author":"","summary":"","pubtime":"1456185660","images":[],"display":"0x01","source":"社会频道","originalurl":"http://society.nen.com.cn/system/2016/02/23/018885763.shtml","url":"http://m.news.liebao.cn/detail.html?f=&newsid=32529402&uuid=edzt45hov5yilamsb5getlfligww","action":"0x02","comments":[],"keywords":[],"categories":[],"newsyscore":"0","socialscore":"0","eroticscore":"0","clickcount":"0","likecount":"0","dislikecount":"0","sharecount":"0","commentcount":"0"}]
     */

    private int ret;
    private String msg;
    private int count;
    private String stime;
    /**
     * contentid : 32529402
     * ctype : 0x01
     * title : 手握不动笔洗澡超4小时 29岁女子：丢了工作黄了对象
     * author :
     * summary :
     * pubtime : 1456185660
     * images : []
     * display : 0x01
     * source : 社会频道
     * originalurl : http://society.nen.com.cn/system/2016/02/23/018885763.shtml
     * url : http://m.news.liebao.cn/detail.html?f=&newsid=32529402&uuid=edzt45hov5yilamsb5getlfligww
     * action : 0x02
     * comments : []
     * keywords : []
     * categories : []
     * newsyscore : 0
     * socialscore : 0
     * eroticscore : 0
     * clickcount : 0
     * likecount : 0
     * dislikecount : 0
     * sharecount : 0
     * commentcount : 0
     */

    private List<DataEntity> data;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public String getStime() {
        return stime;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String contentid;
        private String ctype;
        private String title;
        private String author;
        private String summary;
        private String pubtime;
        private String display;
        private String source;
        private String originalurl;
        private String url;
        private String action;
        private String newsyscore;
        private String socialscore;
        private String eroticscore;
        private String clickcount;
        private String likecount;
        private String dislikecount;
        private String sharecount;
        private String commentcount;
        private List<?> images;
        private List<?> comments;
        private List<?> keywords;
        private List<?> categories;

        public void setContentid(String contentid) {
            this.contentid = contentid;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setPubtime(String pubtime) {
            this.pubtime = pubtime;
        }

        public void setDisplay(String display) {
            this.display = display;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setOriginalurl(String originalurl) {
            this.originalurl = originalurl;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public void setNewsyscore(String newsyscore) {
            this.newsyscore = newsyscore;
        }

        public void setSocialscore(String socialscore) {
            this.socialscore = socialscore;
        }

        public void setEroticscore(String eroticscore) {
            this.eroticscore = eroticscore;
        }

        public void setClickcount(String clickcount) {
            this.clickcount = clickcount;
        }

        public void setLikecount(String likecount) {
            this.likecount = likecount;
        }

        public void setDislikecount(String dislikecount) {
            this.dislikecount = dislikecount;
        }

        public void setSharecount(String sharecount) {
            this.sharecount = sharecount;
        }

        public void setCommentcount(String commentcount) {
            this.commentcount = commentcount;
        }

        public void setImages(List<?> images) {
            this.images = images;
        }

        public void setComments(List<?> comments) {
            this.comments = comments;
        }

        public void setKeywords(List<?> keywords) {
            this.keywords = keywords;
        }

        public void setCategories(List<?> categories) {
            this.categories = categories;
        }

        public String getContentid() {
            return contentid;
        }

        public String getCtype() {
            return ctype;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getSummary() {
            return summary;
        }

        public String getPubtime() {
            return pubtime;
        }

        public String getDisplay() {
            return display;
        }

        public String getSource() {
            return source;
        }

        public String getOriginalurl() {
            return originalurl;
        }

        public String getUrl() {
            return url;
        }

        public String getAction() {
            return action;
        }

        public String getNewsyscore() {
            return newsyscore;
        }

        public String getSocialscore() {
            return socialscore;
        }

        public String getEroticscore() {
            return eroticscore;
        }

        public String getClickcount() {
            return clickcount;
        }

        public String getLikecount() {
            return likecount;
        }

        public String getDislikecount() {
            return dislikecount;
        }

        public String getSharecount() {
            return sharecount;
        }

        public String getCommentcount() {
            return commentcount;
        }

        public List<?> getImages() {
            return images;
        }

        public List<?> getComments() {
            return comments;
        }

        public List<?> getKeywords() {
            return keywords;
        }

        public List<?> getCategories() {
            return categories;
        }
    }
}
