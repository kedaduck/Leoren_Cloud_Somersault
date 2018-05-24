package com.leoren.liehu.Activity.MainFunctionView;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExeciseFragment extends FragmentActivity implements View.OnClickListener{

    private static final String TAG = "ExeciseFragment";

    private CircleImageView exe_head;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execise_fragment);
        
        exe_head = findViewById(R.id.exe_head);
        exe_head.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exe_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
}
