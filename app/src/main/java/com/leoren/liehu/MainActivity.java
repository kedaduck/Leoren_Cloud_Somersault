package com.leoren.liehu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.leoren.liehu.Activity.PersonActivity;
import com.leoren.liehu.Content.LiehuAgreeMent;
import com.leoren.liehu.ImpContent.QQImpContent;
import com.leoren.liehu.ResultInformation.QQResultInformation;
import com.leoren.liehu.util.JsonParse;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.AuthActivity;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import static com.leoren.liehu.R.drawable.login_secret_focused_icon;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,View.OnFocusChangeListener{

    //日志标记
    private static final String TAG = "MainActivity";

    //用户名  一栏
    private EditText login_userInfo ;

    //用户密码一栏
    private EditText login_secret ;

    //普通登录按钮
    private Button normal_login ;

    //普通注册按钮
    private Button normal_register ;

    //忘记密码一栏
    private TextView forget_secret;

    //QQ登录按钮
    private ImageView qq_login;

    //微博登录按钮
    private ImageView weibo_login;

    //华为登录按钮
    private ImageView huawei_login;

    //小米登录按钮
    private ImageView xiaomi_login;

    //烈狐协议按钮
    private TextView agreement;

    //调用QQ接口的一个实例
    private static Tencent mTencent;

    private IUiListener qqloginListener;

    private IUiListener userInfoListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_userInfo = findViewById(R.id.login_userinfo);
        login_secret = findViewById(R.id.login_secret);
        normal_login = findViewById(R.id.normal_login);
        normal_register = findViewById(R.id.normal_register);
        forget_secret = findViewById(R.id.forget_secret);
        qq_login = findViewById(R.id.qq_login);
        weibo_login = findViewById(R.id.weibo_login);
        huawei_login = findViewById(R.id.huawei_login);
        xiaomi_login = findViewById(R.id.mi_login);
        agreement = findViewById(R.id.agreement);

        login_userInfo.setOnFocusChangeListener(this);
        login_secret.setOnFocusChangeListener(this);

        normal_login.setOnClickListener(this);
        normal_register.setOnClickListener(this);
        forget_secret.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        weibo_login.setOnClickListener(this);
        huawei_login.setOnClickListener(this);
        xiaomi_login.setOnClickListener(this);
        agreement.setOnClickListener(this);




    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.normal_login:
                normal_login();
                break;
            case R.id.normal_register:
                normal_register();
                break;
            case R.id.forget_secret:
                forget_secret();
                Log.i(TAG, "onClick: forget");
                Intent intent = new Intent(this, PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.qq_login:
                qq_login();
                break;
            case R.id.weibo_login:
                weibo_login();
                break;
            case R.id.huawei_login:
                huawei_login();
                break;
            case R.id.mi_login:
                xiaomi_login();
                break;
            case R.id.agreement:
                agree_LiehuAgreement();
                break;
            default:
                break;
        }

    }

    /**
     * 正常登录   通过软件自己的登录方式注册后的账号信息登录
     */
    private void normal_login(){

    }


    /**
     * 该app自己注册
     */
    private void normal_register(){

    }

    /**
     * 忘记密码也是该APP的账号密码登录
     */
    private void forget_secret(){

    }

    /**
     * 通过第三方接口登录  QQ登录
     */
    private void qq_login(){
        initQQLogin();
        mTencent.login(MainActivity.this, QQImpContent.SCOPE, qqloginListener);
    }

    /**
     * 通过第三方接口登录  微博登录
     */
    private void weibo_login(){

    }

    /**
     * 华为账号登录
     */
    private void huawei_login(){

    }

    /**
     * 小米账号登录
     */
    private void xiaomi_login(){

    }

    /**
     * 同意用户协议
     */
    private void agree_LiehuAgreement(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(null)
                .setTitle(LiehuAgreeMent.title)
                .setMessage(LiehuAgreeMent.content)
                .setNegativeButton(null,null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }

    /**
     * 改变账号   密码  两个EditText被点击时左侧的两个图案颜色   区分聚焦还是非聚焦
     * @param v     控件
     * @param hasFocus   是否聚焦  true 为聚焦   false为非聚焦
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.login_userinfo:
                changeEditColor(login_userInfo, R.drawable.login_user_focused_icon);
                break;
            case R.id.login_secret:
                changeEditColor(login_secret, R.drawable.login_secret_focused_icon);
                break;
        }
    }

    private void changeEditColor(EditText editText, int drawId){

    }

    private void initQQLogin(){
        mTencent = Tencent.createInstance(QQImpContent.APP_ID, this.getApplicationContext());
        qqloginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后调用该方法
                JSONObject object = (JSONObject) o;
                Toast.makeText(MainActivity.this, "登陆成功",Toast.LENGTH_SHORT).show();
                String openID;
                try{
                    openID = object.getString("openid");
                    String accessToken = object.getString("access_token");
                    String expires = object.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken,expires);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败
                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onError: " + uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录
                Toast.makeText(MainActivity.this,"登录取消",Toast.LENGTH_SHORT).show();
            }
        };

        userInfoListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                if(o == null){
                    return;
                }
                JSONObject object = (JSONObject) o;
                JsonParse.parseQQUserInfo(object);
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        };


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQUEST_LOGIN:
                if(resultCode == -1){
                    Tencent.onActivityResultData(requestCode, resultCode, data, qqloginListener);
                    Tencent.handleResultData(data,qqloginListener);
                    UserInfo info = new UserInfo(this,mTencent.getQQToken());
                    info.getUserInfo(userInfoListener);
                }
        }

    }
}
