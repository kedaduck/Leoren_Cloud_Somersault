package com.leoren.liehu.Activity.MainFunctionView;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HappyFragment extends FragmentActivity implements View.OnClickListener{

    private CircleImageView happy_head;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_fragment);

        happy_head = findViewById(R.id.happy_head);
        happy_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.happy_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
    }
}
