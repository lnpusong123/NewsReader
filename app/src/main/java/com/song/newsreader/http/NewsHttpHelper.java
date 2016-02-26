package com.song.newsreader.http;

import com.song.newsreader.global.Constans;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/2/23.
 */
public class NewsHttpHelper {

    private String scenario = "0x00000101";

    public NewsHttpHelper(){

    }

    public NewsHttpHelper(String scenario){
        setScenario(scenario);
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public interface NewsCallBack{
        void onSuccess(NewsReponse reponse);
    }

    private NewsCallBack newsCallBack;

    public void setOnSuccess(NewsCallBack newsCallBack){
        this.newsCallBack = newsCallBack;
    }

    public void loadNews(NewsCallBack newsCallBack){
        setOnSuccess(newsCallBack);
        RequestParams params = new RequestParams(Constans.HOST);
        params.addQueryStringParameter("model","MI 2S");
        params.addQueryStringParameter("count","10");
        params.addQueryStringParameter("mcc","460");
        params.addQueryStringParameter("osv","5.0.2");
        params.addQueryStringParameter("ctype","0x1EB");
        params.addQueryStringParameter("pid","3");
        params.addQueryStringParameter("display","0xCF");
        params.addQueryStringParameter("aid","f39d35db689d880b");
        params.addQueryStringParameter("net","wifi");
        params.addQueryStringParameter("nmnc","00");
        params.addQueryStringParameter("mode","1");
        params.addQueryStringParameter("appv","3.27.12");
        params.addQueryStringParameter("v","3");
        params.addQueryStringParameter("pf","android");
        params.addQueryStringParameter("scenario",getScenario());
        params.addQueryStringParameter("app_lan","zh_CN");
        params.addQueryStringParameter("lan","zh_CN");
        params.addQueryStringParameter("action","0x0f");
        params.addQueryStringParameter("brand","Xiaomi");
        params.addQueryStringParameter("nmcc","460");
        params.addQueryStringParameter("mnc","02");
        params.addQueryStringParameter("uuid","e083c227b1fd4f8fb3825fe0ceea4fe9");
        params.addQueryStringParameter("ch","20000027");
        params.addQueryStringParameter("offset","");
        params.addQueryStringParameter("act","3");


        x.http().get(params, new Callback.CommonCallback<NewsReponse>() {

            @Override
            public void onSuccess(NewsReponse response) {
                if (null !=response){
                    NewsHttpHelper.this.newsCallBack.onSuccess(response);
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

}
