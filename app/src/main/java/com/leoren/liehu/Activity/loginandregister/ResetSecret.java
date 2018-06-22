package com.leoren.liehu.Activity.loginandregister;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.Helper.HttpContent;
import com.leoren.liehu.R;
import com.leoren.liehu.Util.JsonParse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResetSecret extends AppCompatActivity{

    public EditText password;
    public EditText repassword;

    public TextView wrongPasswrod;
    public TextView wrongRepasswrod;

    private String passwordText;
    private String rePasswordText;

    public Button sureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_secret);

        password = findViewById(R.id.reset_password_text);
        repassword = findViewById(R.id.reset_repassword_text);

        wrongPasswrod = findViewById(R.id.reset_wrong_password);
        wrongRepasswrod = findViewById(R.id.reset_wrong_repassword);

        sureBtn = findViewById(R.id.reset_secret_btn);

        sureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordText = password.getText().toString().trim();
                rePasswordText = repassword.getText().toString().trim();

                boolean flag = isvaildOfpassword(passwordText);
                boolean flag1 = isvaildOfrepassword(rePasswordText);

                if(flag && flag1){
                    Toast.makeText(ResetSecret.this, "修改密码成功", Toast.LENGTH_LONG).show();
                    Intent lastIntent = getIntent();
                    String phone = lastIntent.getStringExtra("phone");
                    updatePasswd(phone);
                    Intent intent = new Intent(ResetSecret.this, MainFunction.class);
                    startActivity(intent);
                }
            }
        });

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(ResetSecret.this,"修改密码成功！！！", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(ResetSecret.this,"修改密码失败，请检查网络连接或密码！！！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    private void updatePasswd(String phone){
        final String path = HttpContent.REQUEST_PATH + "user_resetPasswd.action?phone=" + phone + "&password=" + rePasswordText.toString().trim();
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
                    int isSuccess = JsonParse.parseResetPasswd(sb.toString());
                    Message message = handler.obtainMessage();
                    message.what = isSuccess;
                    handler.sendMessage(message);
                }catch (Exception e){
                    e.printStackTrace();
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

    private boolean isvaildOfpassword(String passwordText){
        if(passwordText != ""){
            if(passwordText.length()>=6 && passwordText.length()<=16){
                return true;
            }else{
                wrongPasswrod.setVisibility(View.VISIBLE);
            }
        }else{
            wrongPasswrod.setVisibility(View.VISIBLE);
        }
        return false;
    }

    private boolean isvaildOfrepassword(String repasswordText){
        if(repasswordText != ""){
            if(repasswordText.equals(passwordText)){
                return true;
            }else{
                wrongRepasswrod.setVisibility(View.VISIBLE);
            }
        }else{
            wrongRepasswrod.setVisibility(View.VISIBLE);
        }
        return false;
    }

}
