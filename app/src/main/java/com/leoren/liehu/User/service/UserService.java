package com.leoren.liehu.User.service;

import android.os.Bundle;
import android.util.Log;

import com.leoren.liehu.Activity.loginandregister.Register;
import com.leoren.liehu.User.Appuser;
import com.leoren.liehu.User.dao.UserDao;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author Leoren
 * @Date 2018/6/7 16:37
 * 关于用户查询 注册的业务层
 * 既可放置本地查询也可放置网络查询
 */
public class UserService {
    private static final String TAG = "UserService";

    private static String HAS_NAME ;

    public static String getHasName() {
        return HAS_NAME;
    }

    public static void setHasName(String hasName) {
        HAS_NAME = hasName;
    }

    private UserDao userDao = new UserDao();

    public int findByName(String username){
        userDao.findByName(username);
        String str = UserService.getHasName();
        Log.i(TAG, "findByName: " + str);
        if(str == null){
            return Register.WORNG_HTTP_HASNAME;
        }
        try{
            JSONObject obj = new JSONObject(str);
            int status = obj.getInt("hasname");
            if(status == 1){
                return Register.HAS_THIS_NAME;
            }else {
                return Register.NO_HAS_THIS_NAME;
            }
        }catch (JSONException e){

        }
        return Register.WORNG_HTTP_HASNAME;
    }

    public void registDataSave(Bundle bundle){
        userDao.registDataSave(bundle);
    }

}
