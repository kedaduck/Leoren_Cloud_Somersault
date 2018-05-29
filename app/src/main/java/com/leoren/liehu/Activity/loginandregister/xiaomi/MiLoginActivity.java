package com.leoren.liehu.Activity.loginandregister.xiaomi;

import android.Manifest;
import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.ImpContent.MiImpContent;
import com.leoren.liehu.R;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MiLoginActivity extends Activity {
    private static final String TAG = "MiLoginActivity";

    public static final Long appId = MiImpContent.appId;
    public static final String redirectUri = MiImpContent.redirectUri;

    XiaomiOAuthResults results;
    private AsyncTask waitResultTask;

    private Button getInfo;
    private TextView miInfo;
    private Button get_personInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_login);

        getInfo = findViewById(R.id.get_miInfo);
        miInfo = findViewById(R.id.miInfo);
        get_personInfo = findViewById(R.id.get_personInfo);

        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaomiOAuthFuture<XiaomiOAuthResults> future = new XiaomiOAuthorize()
                        .setAppId(appId)
                        .setRedirectUrl(redirectUri)
                        .setKeepCookies(true)
                        .setCustomizedAuthorizeActivityClass(CustomizedAuthorizedActivity.class)
                        .startGetAccessToken(MiLoginActivity.this);
                waitAndShowFutureResult(future);

            }
        });

        get_personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XiaomiOAuthFuture<String> future = new XiaomiOAuthorize()
                        .callOpenApi(MiLoginActivity.this,
                                appId,
                                XiaomiOAuthConstants.OPEN_API_PATH_PROFILE,
                                results.getAccessToken(),
                                results.getMacKey(),
                                results.getMacAlgorithm());
                waitAndShowFutureResult(future);

            }
        });



        requestPermission();

    }

    private void requestPermission(){
        if(PermissionChecker.checkCallingOrSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PermissionChecker.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},1000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && PermissionChecker.checkCallingOrSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PermissionChecker.PERMISSION_DENIED){
            Toast.makeText(this,"权限拒绝",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private <V> void waitAndShowFutureResult(final XiaomiOAuthFuture<V> future){
        waitResultTask = new AsyncTask<Void, Void, V>() {
            Exception e;

            protected void onPreExecute(){
                showResult("waiting for Future result...");
            }

            @Override
            protected V doInBackground(Void... voids) {
                showResult("waiting for Future result getting...");
                V v = null;
                try{
                    v = future.getResult();
                }catch (IOException e1){
                    this.e = e1;
                }catch (OperationCanceledException e2){
                    this.e = e2;
                }catch (XMAuthericationException e3){
                    this.e = e3;
                }
                return v;
            }

            @Override
            protected void onPostExecute(V v) {
                if(v != null){
                    if(v instanceof XiaomiOAuthResults){
                        results = (XiaomiOAuthResults) v;
                    }
                    showResult(v.toString());
                }else if(e != null){
                    showResult(e.toString());
                }else {
                    showResult("done and .. get no result:(");
                }
            }
        }.executeOnExecutor(mExecutor);
    }

    Executor mExecutor = Executors.newCachedThreadPool();

    private void showResult(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String timeFormatted = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date(System.currentTimeMillis()));
                miInfo.setText(timeFormatted + "\n" + text);
                Log.i(TAG, "run: " + text);
            }
        });
    }
}
