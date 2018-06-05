package com.leoren.liehu.Activity.loginandregister.huawei;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.HuaweiApiClient;
import com.leoren.liehu.Activity.loginandregister.MainActivity;
import com.leoren.liehu.R;

public class BaseActivity extends AppCompatActivity implements HuaweiApiClient.ConnectionCallbacks,HuaweiApiClient.OnConnectionFailedListener{

    private static final String TAG = "BaseActivity";

    //华为移动服务Client
    protected HuaweiApiClient client;

    //HuaweiApiAvailability.getInstance().resolveError方法会启动activity，为保证不同时弹出多个相同页面，请参照以下方案：
    //调用HuaweiApiAvailability.getInstance().resolveError的时候设置该标志位为true，在onActivityResult中标志位恢复。
    //应用可以根据业务的实际需要使用。
    //The methodHuaweiApiAvailability.getInstance().resolveError initiates the activity, in order to ensure that multiple identical pages are not ejected at the same time,
    //refer to the following scenario: When calling HuaweiApiAvailability.getInstance().resolveError, set the flag to true and sign bit recovery in onActivityResult.
    //Applications can be used in accordance with the actual needs of the business.
    protected boolean isResolve = false;

    protected static final int REQUEST_HMS_RESOLVE_ERROR = 1000;

    //如果开发者在onConnectionFailed调用了resolveError接口，那么错误结果会通过onActivityResult返回,具体的返回码通过该字段获取
    //If the developer calls the Resolveerror interface in onconnectionfailed, the error result is returned via onActivityResult,
    //and the specific return code is obtained through the field
    protected static final String EXTRA_RESULT = "intent.extra.RESULT";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        client.disconnect();
    }



    @Override
    public void onConnected() {
        //华为移动服务client连接成功，在这边处理业务自己的事件
        //Huawei Mobile Service Client connection successful, handle business own event here
        Log.i(TAG, "HuaweiApiClient Connect Successfully!");
        Toast.makeText(this, "华为登陆啊啊啊啊啊啊啊啊啊啊", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onConnectionSuspended(int arg0) {
        //HuaweiApiClient由于异常原因导致断开，如果业务需要继续使用HMS的功能，需要重新连接华为移动服务
        //Huaweiapiclient is disconnected for exceptional reasons and needs to reconnect Huawei mobile service if business needs to continue using HMS Functionality
        Log.i(TAG, "HuaweiApiClient Disconnected!");
        client.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult arg0) {
        Log.i(TAG, "HuaweiApiClient Connect Failed!  Error code：" + arg0.getErrorCode());

        if(isResolve) {
            //如果解决错误的接口已经被调用，并且没有处理完毕，不要重复调用。
            //Do not repeat the call if the interface that resolved the error has been invoked and has not been processed.
            return;
        }
        if(HuaweiApiAvailability.getInstance().isUserResolvableError(arg0.getErrorCode())) {
            Log.e(TAG, "resolveError");
            HuaweiApiAvailability.getInstance().resolveError(this, arg0.getErrorCode(), REQUEST_HMS_RESOLVE_ERROR);
            isResolve = true;
        } else {
            //其他错误码以及处理方法请参见开发指南-HMS 通用错误码及处理方法。
            //Other error codes and processing methods see Development Guide-HMS Common error codes and processing methods.
        }
    }
}
