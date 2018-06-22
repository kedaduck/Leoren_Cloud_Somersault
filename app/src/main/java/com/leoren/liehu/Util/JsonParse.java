package com.leoren.liehu.Util;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.leoren.liehu.User.ResultInformation.MiResultInfo;
import com.leoren.liehu.User.ResultInformation.QQResultInfor;
import com.leoren.liehu.User.ResultInformation.WeiboResultInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 用于解析各种JSON数据的工具类
 * @author Leoren
 * @DATE 2018/5/19 20:08
 */
public class JsonParse extends AppCompatActivity{

    private static final String TAG = "JsonParse";

    public static void parseQQUserInfo(JSONObject object){
        try{
            int ret = object.getInt("ret");
            String nickname = object.getString("nickname");
            Log.i(TAG, "parseQQUserInfo: " + nickname);
            String gender = object.getString("gender");
            String province = object.getString("province");
            String city = object.getString("city");
            String year = object.getString("year");
            String figureurl_qq_1 = object.getString("figureurl_qq_1");
            String figureurl_qq_2 = object.getString("figureurl_qq_2");
            URL url1 = new URL(figureurl_qq_1);
            URL url2 = new URL(figureurl_qq_2);
            QQResultInfor.setQQResultInfor(ret, nickname, gender, province, city, year, url1, url2);

        }catch (JSONException e){
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static boolean parseXiaoMiUserInfo(JSONObject userObject, JSONObject miPhoneObj){
        if(userObject == null && miPhoneObj == null){
            return false;
        }
        try{
            String  result = userObject.getString("result");
            if(!"ok".equals(result)){
                return false;
            }
            JSONObject data = userObject.getJSONObject("data");
            String nickName = data.getString("miliaoNick");
            String userId = data.getString("userId");
            String str1 = data.getString("miliaoIcon_75");
            String str2 = data.getString("miliaoIcon");
            URL url1 = new URL(str1);
            URL url2 = new URL(str2);
            JSONObject data2 = miPhoneObj.getJSONObject("data");
            String phone = data2.getString("phone");
            MiResultInfo.setMiResultInfo(nickName,url1, url2,userId,phone);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static void parseWeiboUserInfo(JSONObject obj1){
        try{
            int userId = obj1.getInt("id");
            String screenName = obj1.getString("screen_name");
            String name = obj1.getString("");
            int province = obj1.getInt("");
            int city = obj1.getInt("");
            String location = obj1.getString("location");
            String url1 = obj1.getString("");
            String url2 = obj1.getString("");
            String gender = obj1.getString("");
            WeiboResultInfo.setWeiboUserInfo(userId,screenName,name,province,city,location,url1,gender,url2);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static int[] parseNormalLogin(String str){
        int flag[] = new int[2];
        try{
            JSONObject obj = new JSONObject(str);
            int status = obj.getInt("status");
            int userid = obj.getInt("userid");
            Log.i(TAG, "parseNormalLogin: " + status);
            flag[0] = status;
            flag[1] = userid;
        }catch (JSONException e){

        }
        return flag;
    }

    public static int parseHasPhone(String s) {
        int has = 0;
        try{
            JSONObject obj = new JSONObject(s);
            has = obj.getInt("hasphone");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return has;
    }

    public static int parseResetPasswd(String s) {
        int isSuccess = 0;
        try{
            JSONObject obj = new JSONObject(s);
            isSuccess = obj.getInt("isSuccess");
        }catch (JSONException e){
            e.printStackTrace();
        }
        return isSuccess;
    }








}
