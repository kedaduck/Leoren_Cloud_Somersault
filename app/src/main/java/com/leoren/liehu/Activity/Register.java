package com.leoren.liehu.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.leoren.liehu.R;

public class Register extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private ImageView back ;
    private EditText nickname;
    private TextView wrongnickname;
    private EditText password;
    private TextView wrongpassword;
    private EditText repassword;
    private TextView wrongrepassword;
    private Button coun_register;

    private String nicknameText;
    private String passwordText;
    private String repasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        back = findViewById(R.id.from_register_back);
        nickname = findViewById(R.id.register_nickname);
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
                counRegister();
                break;
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

    private void counRegister(){
        nicknameText = nickname.getText().toString().trim();
        passwordText = password.getText().toString().trim();
        repasswordText = repassword.getText().toString().trim();
        boolean flag1 = isvaildOfnickname(nicknameText);
        boolean flag2 = isvaildOfpassword(passwordText);
        boolean flag3 = isvaildOfrepassword(repasswordText);
        if(flag1 && flag2 && flag3){
            Bundle bundle = new Bundle();
            bundle.putString("nickname", nicknameText);
            bundle.putString("password", passwordText);
            bundle.putString("repassword", repasswordText);
            saveInfoTOdatabase(bundle);
            Intent intent = new Intent(this, Register_tel.class);
            startActivity(intent);
        }

    }

    /**
     * 这个方法还没写
     */
    private void saveInfoTOdatabase(Bundle bundle){

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
