package com.leoren.liehu.Activity.loginandregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leoren.Send_ConfirmCode;
import com.leoren.liehu.Helper.HttpContent;
import com.leoren.liehu.R;
import com.leoren.liehu.Util.JsonParse;
import com.leoren.liehu.Util.RegexUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgetSecret extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNumber;
    private EditText confirmNumber;

    private Button sendConfirm;
    private Button sureBtn;

    private String phoneNumberText;
    private String confirmNumberText;
    private String code;

    TimeCount timer = new TimeCount(60000,1000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_secret);

        phoneNumber = findViewById(R.id.reset_secret_phoneNumber);
        confirmNumber = findViewById(R.id.reset_confirmCode);
        sendConfirm = findViewById(R.id.reset_get_confirmCode);
        sureBtn = findViewById(R.id.sure_sendConfirm);

        sendConfirm.setOnClickListener(this);
        sureBtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reset_get_confirmCode:
                ishasPhone();
                break;
            case R.id.sure_sendConfirm:
                startResetActivity(code);
                break;
            default:
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1://有此手机号
                    timer.start();
                    getConfirmCode();
                    break;
                case 0://无此手机号
                    AlertDialog dialog = new AlertDialog.Builder(getApplicationContext())
                            .setMessage("无此手机号,请重新输入")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create();
                    dialog.show();
                    break;
            }
        }
    };

    Handler handler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            code = msg.getData().getString("codeCheck");
            Toast.makeText(ForgetSecret.this, code , Toast.LENGTH_SHORT).show();
        }
    };

    private void ishasPhone(){
        final String phonenum = phoneNumber.getText().toString().trim();
        if(!RegexUtil.regexPhone(phonenum)){
            Toast.makeText(ForgetSecret.this, "手机号格式不正确，请重新输入", Toast.LENGTH_SHORT).show();
            return;
        }
        final String path = HttpContent.REQUEST_PATH + "user_ishasPhone.action?phone=" + phonenum;
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
                    int has = JsonParse.parseHasPhone(sb.toString());
                    Message message = handler.obtainMessage();
                    message.what = has;
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

    private void getConfirmCode(){
        phoneNumberText = phoneNumber.getText().toString().trim();
        if(!RegexUtil.regexPhone(phoneNumberText)){
            Toast.makeText(ForgetSecret.this, "手机号格式有误，请重新输入！！！", Toast.LENGTH_LONG).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String codeCheck = Send_ConfirmCode.getResult(phoneNumberText);
                Message message = handler1.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putString("codeCheck", codeCheck);
                message.setData(bundle);
                handler1.sendMessage(message);
            }
        }).start();
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
    }

    private void startResetActivity(String code){
        confirmNumberText = confirmNumber.getText().toString().trim();
        if(confirmNumberText != null && confirmNumberText.equals(code)){
            Intent intent = new Intent(this, ResetSecret.class);
            intent.putExtra("phone", phoneNumberText.toString().trim());
            startActivity(intent);
        }else{
            Toast.makeText(this, "验证码输入有误,请稍候重新获取" ,Toast.LENGTH_LONG).show();
        }
    }

    class TimeCount extends CountDownTimer{

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            sendConfirm.setBackgroundColor(Color.parseColor("#B6B6D8"));
            sendConfirm.setClickable(false);
            sendConfirm.setText((millisUntilFinished / 1000) + "秒后可重新发送" );
            sendConfirm.setTextColor(getColor(R.color.darkgray));
            sendConfirm.setTextSize(14);
        }

        @Override
        public void onFinish() {
            sendConfirm.setText("重新获取验证码");
            sendConfirm.setTextSize(16);
            sendConfirm.setClickable(true);
            sendConfirm.setBackgroundColor(getColor(R.color.food_page_color));
            sendConfirm.setTextColor(getColor(R.color.dark));
        }



    }

}
