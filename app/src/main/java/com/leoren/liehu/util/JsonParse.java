package com.leoren.liehu.util;

import com.leoren.liehu.ResultInformation.QQResultInformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于解析各种JSON数据的工具类
 * @author Leoren
 * @DATE 2018/5/19 20:08
 */
public class JsonParse {

    public static QQResultInformation parseQQUserInfo(JSONObject object){
        QQResultInformation information = null;
        try{
            int ret = object.getInt("ret");
            String nickname = object.getString("nickname");
            String gender = object.getString("gender");
            String province = object.getString("province");
            String city = object.getString("city");
            String year = object.getString("year");
            String figureurl_qq_1 = object.getString("figureurl_qq_1");
            String figureurl_qq_2 = object.getString("figureurl_qq_2");
            URL url1 = new URL(figureurl_qq_1);
            URL url2 = new URL(figureurl_qq_2);
            information = new QQResultInformation(ret, nickname, gender, province, city, year, url1, url2);
        }catch (JSONException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return information;
    }


}
