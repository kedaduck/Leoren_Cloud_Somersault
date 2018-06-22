package com.leoren.liehu.Activity.loginandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;
import com.leoren.liehu.User.service.UserService;
import com.leoren.liehu.Util.SendComfrimCode;

public class Register_tel extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "Register_tel";

    private ImageView backfrom_finishRegister;
    private TextView regi_phoneNumber;
    private Button get_confirmCode;
    private TextView confirmCode;
    private Button finishRegister;
    private String phoneNumber;
    private String randNum;


    private UserService userService = new UserService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tel);

        backfrom_finishRegister = findViewById(R.id.backfrom_finishRegister);
        regi_phoneNumber = findViewById(R.id.regi_phoneNumber);
        get_confirmCode = findViewById(R.id.get_confirmCode);
        confirmCode = findViewById(R.id.confirmCode);
        finishRegister = findViewById(R.id.finishRegister);

        backfrom_finishRegister.setOnClickListener(this);
        finishRegister.setOnClickListener(this);
        get_confirmCode.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backfrom_finishRegister:
                finish();
                break;
            case R.id.get_confirmCode:
                get_confirmCode();
                break;
            case R.id.finishRegister:
                finishRegister();
                break;
            default:
                break;
        }
    }

    private boolean get_confirmCode(){
        phoneNumber = regi_phoneNumber.getText().toString().trim();
        if(phoneNumber != null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    randNum = SendComfrimCode.Send_Message_Code(phoneNumber);
                }
            }).start();
            Toast.makeText(this,"发送成功！！！", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this,"发送验证码失败！！！,请检查网络连接", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void finishRegister(){
        String inputComfrimCode = confirmCode.getText().toString();
        if(randNum.equals(inputComfrimCode)){
            String username = getIntent().getStringExtra("nickname");
            String password = getIntent().getStringExtra("password");
            saveDataToDB(username, password, regi_phoneNumber.getText().toString().trim());
            Intent intent = new Intent(this, MainFunction.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "验证码有误，请重新输入",Toast.LENGTH_SHORT).show();
        }
    }

    private void saveDataToDB(String username, String password,String phone){
        Bundle bundle = new Bundle();
        bundle.putString("nickname",username);
        bundle.putString("password",password);
        bundle.putString("phone",phone);
        userService.registDataSave(bundle);
    }
}
