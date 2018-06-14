package com.leoren.liehu.Activity.loginandregister;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.leoren.liehu.R;
import com.leoren.liehu.User.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Register extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private static final String TAG = "Register";


    private ImageView back ;
    private EditText nickname;
    private TextView wrongnickname;
    private TextView hasthisname;
    private EditText password;
    private TextView wrongpassword;
    private EditText repassword;
    private TextView wrongrepassword;
    private Button coun_register;

    private String nicknameText;
    private String passwordText;
    private String repasswordText;

    public final static int HAS_THIS_NAME = 1;
    public final static int NO_HAS_THIS_NAME = -1;
    public static int IS_HAS_NAME = 0;
    public static final int WORNG_HTTP_HASNAME = 5;

    private UserService userService = new UserService();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back = findViewById(R.id.from_register_back);
        nickname = findViewById(R.id.register_nickname);
        hasthisname = findViewById(R.id.has_thisname);
        wrongnickname = findViewById(R.id.wrong_nickname);
        password = findViewById(R.id.register_password);
        wrongpassword = findViewById(R.id.wrong_password);
        repassword = findViewById(R.id.register_repassword);
        wrongrepassword = findViewById(R.id.wrong_repassword);
        coun_register = findViewById(R.id.coun_register);

        back.setOnClickListener(this);
        coun_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.from_register_back:
                finish();
                break;
            case R.id.coun_register:
                isHasThisName(nickname.getText().toString());
                break;
            case R.id.register_password:
                if(hasthisname.getVisibility()==View.VISIBLE){
                    hasthisname.setVisibility(View.GONE);
                }
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.register_nickname:
                if(hasFocus && (wrongnickname.getVisibility() == View.VISIBLE )){
                    wrongnickname.setVisibility(View.GONE);
                }
                if(hasFocus){
                    hasthisname.setVisibility(View.GONE);
                }
                break;
            case R.id.register_password:
                if(hasFocus && (wrongpassword.getVisibility() == View.VISIBLE)){
                    wrongpassword.setVisibility(View.GONE);
                }
                break;
            case R.id.register_repassword:
                if(hasFocus && (wrongrepassword.getVisibility() == View.VISIBLE)){
                    wrongrepassword.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }


    private void isHasThisName(String name){
        int status = userService.findByName(name);
        switch (status){
            case NO_HAS_THIS_NAME:
                counRegister();
                break;
            case HAS_THIS_NAME:
                hasthisname.setVisibility(View.VISIBLE);
                break;
            case WORNG_HTTP_HASNAME:
                //Toast.makeText(Register.this, "网络出错，请稍后再试！",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    private void counRegister(){
        nicknameText = nickname.getText().toString().trim();
        passwordText = password.getText().toString().trim();
        repasswordText = repassword.getText().toString().trim();
        boolean flag1 = isvaildOfnickname(nicknameText);
        boolean flag2 = isvaildOfpassword(passwordText);
        boolean flag3 = isvaildOfrepassword(repasswordText);
        if(flag1 && flag2 && flag3){
            Intent intent = new Intent(this, Register_tel.class);
            intent.putExtra("nickname",nicknameText);
            intent.putExtra("password",passwordText);
            startActivity(intent);
        }

    }

    /**
     * 这个方法还没写
     */
    private void saveInfoTOdatabase(Bundle bundle){
        //和手机号一同写入
    }

    private boolean isvaildOfnickname(String nicknameText){
        if(nicknameText != ""){
            if(nicknameText.length()>=2 && nicknameText.length()<=6){
                return true;
            }else{
                wrongnickname.setVisibility(View.VISIBLE);
            }
        }else{
            wrongnickname.setVisibility(View.VISIBLE);
        }
        return false;
    }

    private boolean isvaildOfpassword(String passwordText){
        if(passwordText != ""){
            if(passwordText.length()>=6 && passwordText.length()<=16){
                return true;
            }else{
                wrongpassword.setVisibility(View.VISIBLE);
            }
        }else{
            wrongpassword.setVisibility(View.VISIBLE);
        }
        return false;
    }

    private boolean isvaildOfrepassword(String repasswordText){
        if(repasswordText != ""){
            if(repasswordText.equals(passwordText)){
                return true;
            }else{
                wrongrepassword.setVisibility(View.VISIBLE);
            }
        }else{
            wrongrepassword.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
