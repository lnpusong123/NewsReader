package com.song.newsreader.http;

import com.google.gson.Gson;
import com.song.newsreader.utils.LogUtils;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wyouflf on 15/11/5.
 */
public class JsonResponseParser implements ResponseParser {// 如果实现 InputStreamResponseParser, 可实现自定义流数据转换.

    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        // custom check ?
        // get headers ?
    }

    /**
     * 转换result为resultType类型的对象
     *
     * @param resultType  返回值类型(可能带有泛型信息)
     * @param resultClass 返回值类型
     * @param result      字符串数据
     * @return
     * @throws Throwable
     */
    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        // TODO: json to java bean
        if (resultClass == List.class) {
            return null;
        } else {
            Gson gson = new Gson();
            LogUtils.d(result);
            return  gson.fromJson(result, NewsReponse.class);
        }

    }
}
