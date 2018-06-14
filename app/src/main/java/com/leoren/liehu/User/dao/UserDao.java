package com.leoren.liehu.User.dao;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.leoren.liehu.Helper.HttpContent;
import com.leoren.liehu.User.Appuser;
import com.leoren.liehu.User.service.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import javax.xml.transform.OutputKeys;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @Author Leoren
 * @Date 2018/6/7 16:37
 *
 * 关于用户查询 注册的持久层
 * 既可放置本地查询也可放置网络查询
 */
public class UserDao {

    private static final String TAG = "UserDao";


    public void findByName(String username) {
        MyAsyncTask task = new MyAsyncTask(username);
        task.execute();
    }

    public void registDataSave(Bundle bundle){
        String username = bundle.getString("nickname");
        String password = bundle.getString("password");
        String phone = bundle.getString("phone");
        String path = HttpContent.REQUEST_PATH  + "user_registDataSave.action?username="+ username + "&password=" + password + "&phone=" + phone;
        SaveDataTask task = new SaveDataTask(path);
        task.execute();
    }
}

class SaveDataTask extends AsyncTask<String, Void, String>{
    String path;
    SaveDataTask(String path){
        this.path = path;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(path).build();
            client.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

class MyAsyncTask extends AsyncTask<String, Void, String>{
    String username;
    public MyAsyncTask(String username){
        this.username = username;
    }

    @Override
    protected String doInBackground(String... strings) {


        final String path = HttpContent.HTTP_DIRECT + HttpContent.HTTP_PORT + HttpContent.PROJECT_NAME
                + "user_register.action?time=" + new Date().getTime()
                + "&username=" + username;
        OkHttpClient client = null;
        BufferedReader reader = null;
        StringBuilder sb = null;
        try{
            client = new OkHttpClient();
            Request request = new Request.Builder().url(path).build();
            Response response = client.newCall(request).execute();
            InputStream in = response.body().byteStream();
            reader = new BufferedReader(new InputStreamReader(in));
            String line ;
            sb = new StringBuilder();
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
        }catch (Exception e){

        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        if(sb == null){
            return null;
        }else{
            return sb.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        UserService.setHasName(s);
    }
}
