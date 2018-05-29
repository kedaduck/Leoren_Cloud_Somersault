package com.leoren.liehu.Activity.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;

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
                    Intent intent = new Intent(ResetSecret.this, MainFunction.class);
                    startActivity(intent);
                }
            }
        });






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
