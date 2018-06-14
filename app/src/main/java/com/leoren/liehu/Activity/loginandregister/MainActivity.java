package com.leoren.liehu.Activity.loginandregister;

import android.Manifest;
import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.hwid.HuaweiId;
import com.huawei.hms.support.api.hwid.HuaweiIdSignInOptions;
import com.huawei.hms.support.api.hwid.HuaweiIdStatusCodes;
import com.huawei.hms.support.api.hwid.SignInHuaweiId;
import com.huawei.hms.support.api.hwid.SignInResult;
import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.Activity.MainFunctionView.ExeciseView.Exercise;
import com.leoren.liehu.Activity.PersonActivity;
import com.leoren.liehu.Activity.loginandregister.huawei.BaseActivity;
import com.leoren.liehu.Activity.loginandregister.xiaomi.CustomizedAuthorizedActivity;
import com.leoren.liehu.Content.LiehuAgreeMent;
import com.leoren.liehu.Helper.HttpContent;
import com.leoren.liehu.ImpContent.MiImpContent;
import com.leoren.liehu.ImpContent.QQImpContent;
import com.leoren.liehu.ImpContent.WeiboImpContent;
import com.leoren.liehu.R;
import com.leoren.liehu.User.Appuser;
import com.leoren.liehu.User.ResultInformation.HuaweiResultInfo;
import com.leoren.liehu.util.JsonParse;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener ,View.OnFocusChangeListener{

    public static int LOGIN_MODE = -1;
    private static List<String> text = new ArrayList<String>();
    private static boolean isRestart = false;

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

    //普通登录的参数
    private final static int NORMAL_LOGIN_SUCC = 1;
    private final static int NORMAL_LOGIN_NOUSER = 0;
    private final static int NORMAL_LOGIN_WRONGPASS = 2;

    private TextView no_user ;
    private TextView wrong_pass;


    //调用QQ接口的一个实例
    private static Tencent mTencent;

    private IUiListener qqloginListener;

    private IUiListener userInfoListener;

    //调用小米登录的一些ID   回调地址等
    public static final Long appId = MiImpContent.appId;
    public static final String redirectUri = MiImpContent.redirectUri;
    XiaomiOAuthResults results;
    private AsyncTask waitResultTask;


    //调用微博登录的一些类
    private Oauth2AccessToken mAccessToken;
    private SsoHandler mSsoHandler;

    //华为登录
    private static final int REQUEST_SIGN_IN_AUTH = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_userInfo = findViewById(R.id.login_userinfo);
        login_secret = findViewById(R.id.login_secret);
        no_user = findViewById(R.id.no_user);
        wrong_pass = findViewById(R.id.wrong_pass);
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

        requestPermission();

        /*==============================weibo==========================*/
        WbSdk.install(this, new AuthInfo(this, WeiboImpContent.APP_KEY, WeiboImpContent.REDIRECT_URL, WeiboImpContent.SCOPE));

        //创建微博实例
        mSsoHandler = new SsoHandler(MainActivity.this);

        /*===========================huawei=============================*/
        huaweiLoginPerAction();



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

    private void startMainFunction(){
        Intent intent = new Intent(MainActivity.this, MainFunction.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * 正常登录   通过软件自己的登录方式注册后的账号信息登录
     */

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case NORMAL_LOGIN_SUCC://正常登录
                    startMainFunction();
                    break;
                case NORMAL_LOGIN_NOUSER://没有此用户
                    no_user.setVisibility(View.VISIBLE);
                    break;
                case NORMAL_LOGIN_WRONGPASS://用户名错误
                    wrong_pass.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
    };
    private void normal_login(){
        LOGIN_MODE = Appuser.NORMAL_LOGIN_MODE;

        final String username = login_userInfo.getText().toString().trim();
        final String password = login_secret.getText().toString().trim();
        final String path = isType(username,password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = null;
                BufferedReader reader = null;
                try{
                    client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(path)
                            .build();
                    Response response = client.newCall(request).execute();
                    InputStream in = response.body().byteStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line ;
                    final StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    int flag = JsonParse.parseNormalLogin(sb.toString())[0];
                    int userid = JsonParse.parseNormalLogin(sb.toString())[1];
                    Message message = new Message();
                    message.what = flag;
                    if(flag == NORMAL_LOGIN_SUCC){
                        saveLocalFile(userid);
                    }
                    handler.sendMessage(message);
                }catch (Exception e){

                }finally {
                    if(reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

    }

    private void saveLocalFile(int userid){
        SharedPreferences.Editor editor = getSharedPreferences("currentUser", MODE_PRIVATE).edit();
        editor.putInt("userid", userid);
        editor.putInt("loginmode", Appuser.NORMAL_LOGIN_MODE);
        editor.apply();
    }

    private String isType( String username, String password){
        int type = 0;
        String emailRegex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
            String phoneRegex = "0?(13|14|15|18|17)[0-9]{9}";
        String userRegex = "[A-Za-z0-9_\\\\-\\\\u4e00-\\\\u9fa5]+";
        Pattern r1 = Pattern.compile(emailRegex);
        Pattern r2 = Pattern.compile(phoneRegex);
        Pattern r3 = Pattern.compile(userRegex);
        Matcher m1 = r1.matcher(username);
        Matcher m2 = r2.matcher(username);
        Matcher m3 = r3.matcher(username);
        if(m1.matches()){
            type = 1;
        }else if(m2.matches()){
            type =  2;
        }else {
            type =  3;
        }

        String path = null;
        switch (type) {
            case 1:
                path = HttpContent.REQUEST_PATH + "user_login.action?email=" + username + "&password=" + password + "&logintype=" + type;
                break;
            case 2:
                path = HttpContent.REQUEST_PATH + "user_login.action?phone=" + username + "&password=" + password + "&logintype=" + type;
                break;
            case 3:
                path = HttpContent.REQUEST_PATH + "user_login.action?username=" + username + "&password=" + password + "&logintype=" + type;
                break;
            default:
                path = null;
        }
        return path;
    }


    @Override
    protected void onRestart() {
        isRestart = true;
        super.onRestart();
        Log.i(TAG, "onRestart: ");
        getAllMiUserInfo();
    }

    /**
     * 该app自己注册
     */
    private void normal_register(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    /**
     * 忘记密码也是该APP的账号密码登录
     */
    private void forget_secret(){
        Intent intent = new Intent(MainActivity.this, ForgetSecret.class);
        startActivity(intent);
    }

    /**
     * 通过第三方接口登录  QQ登录
     */
    private void qq_login(){
        LOGIN_MODE = Appuser.QQ_LOGIN_MODE;
        initQQLogin();
        mTencent.login(MainActivity.this, QQImpContent.SCOPE, qqloginListener);
    }

    /**
     * 通过第三方接口登录  微博登录
     */
    private void weibo_login(){
        LOGIN_MODE = Appuser.WEIBO_LOGIN_MODE;
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    /**
     * 华为账号登录
     */
    private void huawei_login(){
        LOGIN_MODE = Appuser.HUAWEI_LOGIN_MODE;
        signIn();
        Toast.makeText(MainActivity.this, "华为登录", Toast.LENGTH_LONG).show();
    }



    /**
     * 小米账号登录
     */
    private void xiaomi_login(){
        LOGIN_MODE = Appuser.XIAOMI_LOGIN_MODE;
        XiaomiOAuthFuture<XiaomiOAuthResults> future = new XiaomiOAuthorize()
                .setAppId(appId)
                .setRedirectUrl(redirectUri)
                .setKeepCookies(true)
                .setCustomizedAuthorizeActivityClass(CustomizedAuthorizedActivity.class)
                .startGetAccessToken(MainActivity.this);
        waitAndShowFutureResult(future);

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
                no_user.setVisibility(View.GONE);
                wrong_pass.setVisibility(View.GONE);
                break;
            case R.id.login_secret:
                changeEditColor(login_secret, R.drawable.login_secret_focused_icon);
                no_user.setVisibility(View.GONE);
                wrong_pass.setVisibility(View.GONE);
                break;
        }
    }

    private void changeEditColor(EditText editText, int drawId){

    }

    /*===================================================================授权===============================================================================*/

    private void requestPermission(){
        if(PermissionChecker.checkCallingOrSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PermissionChecker.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1000);
        }
    }


    /*================================================================回调 接收第三方登录后信息================================================================*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case Constants.REQUEST_LOGIN:
                if(resultCode == -1){
                    Tencent.onActivityResultData(requestCode, resultCode, data, qqloginListener);
                    Tencent.handleResultData(data,qqloginListener);
                    UserInfo info = new UserInfo(this,mTencent.getQQToken());
                    info.getUserInfo(userInfoListener);
                    startMainFunction();
                }
        }
        if(requestCode == 1000 && PermissionChecker.checkCallingOrSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PermissionChecker.PERMISSION_DENIED){
            Toast.makeText(this,"权限拒绝",Toast.LENGTH_LONG).show();
            finish();
        }


    }

    /*==========================================================QQ登录所用方法和私有类======================================================*/

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








    /*==============================================小米账号所使用方法============================================*/

    private void getAllMiUserInfo(){
        getMiUserInfo();
        getMiPhone();

    }

    private void getMiUserInfo(){
        XiaomiOAuthFuture<String> future = new XiaomiOAuthorize()
                .callOpenApi(MainActivity.this,
                        appId,
                        XiaomiOAuthConstants.OPEN_API_PATH_PROFILE,
                        results.getAccessToken(),
                        results.getMacKey(),
                        results.getMacAlgorithm());
        waitAndShowFutureResult(future);
    }

    private void getMiPhone(){
        XiaomiOAuthFuture<String> future = new XiaomiOAuthorize()
                .callOpenApi(MainActivity.this,
                        appId,
                        XiaomiOAuthConstants.OPEN_API_PATH_PHONE,
                        results.getAccessToken(),
                        results.getMacKey(),
                        results.getMacAlgorithm());
        waitAndShowFutureResult(future);
    }

    @SuppressLint("StaticFieldLeak")
        private <V> void waitAndShowFutureResult(final XiaomiOAuthFuture<V> future) {
        int i = 1;
        waitResultTask = new AsyncTask<Void, Void, V>() {
            Exception e;

            @Override
            protected void onPreExecute() {
                //showResult("waiting for Future result...");
            }

            @Override
            protected V doInBackground(Void... params) {
                V v = null;
                try {
                    v = future.getResult();
                } catch (IOException e1) {
                    this.e = e1;
                } catch (OperationCanceledException e1) {
                    this.e = e1;
                } catch (XMAuthericationException e1) {
                    this.e = e1;
                }
                return v;
            }

            @Override
            protected void onPostExecute(V v) {
                if (v != null) {
                    if (v instanceof XiaomiOAuthResults) {
                        results = (XiaomiOAuthResults) v;
                    }
                    showResult(v.toString());
                }
            }
        }.executeOnExecutor(mExecutor);

    }

    Executor mExecutor = Executors.newCachedThreadPool();

    private void showResult(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "result:" + text);
                if(isRestart){
                    Log.i(TAG, "run: 添加了");
                    MainActivity.text.add(text);
                    if(MainActivity.text.size() == 2){
                        JSONObject obj1 = null;
                        JSONObject obj2 = null;
                        try {
                            obj1 = new JSONObject(MainActivity.text.get(0));
                            obj2 = new JSONObject(MainActivity.text.get(1));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonParse.parseXiaoMiUserInfo(obj2, obj1);
                        actionDelayed();
                        startMainFunction();
                        Log.i(TAG, "getAllMiUserInfo: 1111111111111");
                    }
                }
            }
        });
    }

    private void actionDelayed(){
        final PopupWindow window = new PopupWindow();
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setFocusable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.popup, null);
        window.setContentView(view);
        window.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0 ,0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                window.dismiss();
            }
        },3500);

    }









    /*==============================================微博登录所用私有类和方法=========================================*/

    private class SelfWbAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener{

        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if(mAccessToken.isSessionValid()){
                        //保存token到SharedPreferences
                        AccessTokenKeeper.writeAccessToken(MainActivity.this, mAccessToken);
                        Toast.makeText(MainActivity.this,"微博授权成功！！！", Toast.LENGTH_LONG).show();
                        getWeiboUserInfo();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(MainActivity.this,"用户取消授权！！！", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(MainActivity.this,wbConnectErrorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void getWeiboUserInfo(){
        final String baseUrl = "https://api.weibo.com/2/users/show.json"+mAccessToken;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(baseUrl)
                        .build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    JsonParse.parseWeiboUserInfo(new JSONObject(responseData));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
















    /*=================================华为账号开发所用私有类和方法============================*/

    private void huaweiLoginPerAction(){

        //创建基础权限的登录参数options
        HuaweiIdSignInOptions signInOptions = new HuaweiIdSignInOptions.Builder(HuaweiIdSignInOptions.DEFAULT_SIGN_IN).build();

        //创建华为移动服务client实例以登录华为账号
        client = new HuaweiApiClient.Builder(this)
                .addApi(HuaweiId.SIGN_IN_API, signInOptions)
                .addScope(HuaweiId.HUAEWEIID_BASE_SCOPE)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        client.connect();

    }

    /**
     * 登录华为账号
     */
    private void signIn() {
        if(!client.isConnected()){
            Log.i(TAG, "Login failed！Reason: Huaweiapiclient not connected！");
            client.connect();
            return;
        }
        PendingResult<SignInResult> signInResult = HuaweiId.HuaweiIdApi.signIn(client);
        signInResult.setResultCallback(new SignInResultCallback());
    }

    /**
     * 登录结果回调
     */
    private class SignInResultCallback implements ResultCallback<SignInResult>{

        @Override
        public void onResult(SignInResult result) {
           if(result == null){
               //异常场景，接口调用失败
               return;
           }

           if(result.isSuccess()){
               //登录成功  可在此获取账号的各种信息
               SignInHuaweiId account = result.getSignInHuaweiId();

               if(account != null){
                   String nickName = account.getDisplayName();
                   String openId = account.getOpenId();
                   String accessToken = account.getAccessToken();
                   String headUrl = account.getPhotoUrl();
                   String userId = account.getUid();
                   int gender = account.getGender();
                   Toast.makeText(MainActivity.this, "华为登陆"+nickName, Toast.LENGTH_LONG).show();
                   HuaweiResultInfo.setHuaweiResultInfo(nickName,headUrl,openId,userId,gender,accessToken);

               }
           }else {
               //当未授权，回调的result中包含处理该种异常的intent，开发者需要通过getData将对应异常的intent获取出来
               //并通过startActivityForResult启动对应的异常处理界面。
               if(result.getStatus().getStatusCode() == HuaweiIdStatusCodes.SIGN_IN_AUTH){
                   Intent intent = result.getData();
                   if(intent != null){
                       startActivityForResult(intent, REQUEST_SIGN_IN_AUTH);
                   }else{
                       //异常场景，请作为登录失败处理
                       //Abnormal scenarios, Failed to invoke the interface
                       Toast.makeText(MainActivity.this, "华为账号登录失败，请稍后登录", Toast.LENGTH_LONG).show();
                   }
               }else if (result.getStatus().getStatusCode() == HuaweiIdStatusCodes.SIGN_IN_NETWORK_ERROR){
                   //网络异常,建议检查网络
                   Toast.makeText(MainActivity.this, "华为账号登录失败，请检查网络重新登录", Toast.LENGTH_LONG).show();
               }else{
                   Toast.makeText(MainActivity.this, "华为账号登录失败，请稍后登录", Toast.LENGTH_LONG).show();
               }
           }
        }
    }


}
