package com.leoren.liehu.Activity.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.Send_ConfirmCode;
import com.leoren.liehu.R;

public class ForgetSecret extends AppCompatActivity implements View.OnClickListener{

    private EditText phoneNumber;
    private EditText confirmNumber;

    private Button sendConfirm;
    private Button sureBtn;

    private String phoneNumberText;
    private String confirmNumberText;
    private String code;

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
                getConfirmCode();
                break;
            case R.id.sure_sendConfirm:
                startResetActivity();
                break;
            default:
                break;
        }
    }

    private void getConfirmCode(){
        phoneNumberText = phoneNumber.getText().toString().trim();
        if(phoneNumberText == null || phoneNumberText.length() != 11){
            Toast.makeText(ForgetSecret.this, "手机号有误，请重新输入！！！", Toast.LENGTH_LONG).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                code = Send_ConfirmCode.getResult(phoneNumberText);

            }
        }).start();
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
    }

    private void startResetActivity(){
        confirmNumberText = confirmNumber.getText().toString().trim();
        if(confirmNumberText != null && confirmNumberText == code){
            Intent intent = new Intent(this, ResetSecret.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "验证码输入有误,请稍候重新获取" ,Toast.LENGTH_LONG).show();
        }

    }

}
