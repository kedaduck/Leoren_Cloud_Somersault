package com.leoren.liehu.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leoren.liehu.MyView.CircleImageView;
import com.leoren.liehu.R;

public class MainFunction extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private CircleImageView headIcon;
    private NavigationView personal_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main_function);

        drawerLayout = findViewById(R.id.drawer_layout);
        headIcon = findViewById(R.id.main_headIcon);
        personal_view = findViewById(R.id.per_view);

        headIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
