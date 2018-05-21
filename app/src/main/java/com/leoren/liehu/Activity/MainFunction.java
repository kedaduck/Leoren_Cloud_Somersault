package com.leoren.liehu.Activity;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leoren.liehu.Activity.MainFunctionView.ExeciseFragment;
import com.leoren.liehu.Activity.MainFunctionView.FoodFragment;
import com.leoren.liehu.Activity.MainFunctionView.FriendsFragment;
import com.leoren.liehu.Activity.MainFunctionView.HappyFragment;
import com.leoren.liehu.MyView.CircleImageView;
import com.leoren.liehu.R;
import com.leoren.liehu.util.Adapter.MyFragmentAdapter;

import java.util.ArrayList;

public class MainFunction extends FragmentActivity implements View.OnClickListener , ViewPager.OnPageChangeListener{

    //左拉个人主页布局
    private DrawerLayout drawerLayout;

    //ToolBar上的头像
    private CircleImageView headIcon;

    //左拉个人主页
    private NavigationView personal_view;

    //定义tab左拉右滑的一些工具和容器
    private ViewPager viewPager;
    private ArrayList<View> views;
    private LocalActivityManager manager;


    //下面四个按钮  、 文本的数组
    private ImageView[] imgViews ;
    private TextView[] textViews;

    private static int UNSELECT_COLOR = Color.parseColor("#2c2c2c");
    private static int SELECTED_COLOR = Color.parseColor("#1afa29");



    //主功能页面底部的四个按钮  由他们的点击实现页面滑动
    private ImageView foodImg;
    private ImageView execiseImg;
    private ImageView friendsImg;
    private ImageView happyImg;

    //主功能页面底部的四个按钮下的文字说明
    private TextView foodText;
    private TextView execiseText;
    private TextView friendsText;
    private TextView happyText;

    private TextView maintoolText;




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
        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        initView();
    }

    private void initView(){

        viewPager = findViewById(R.id.maintab_view);

        //初始化底部四个按钮
        foodImg = findViewById(R.id.tab_food_img);
        execiseImg = findViewById(R.id.tab_execise_img);
        friendsImg = findViewById(R.id.tab_friends_img);
        happyImg = findViewById(R.id.tab_happy_img);

        //初始化按钮数组
        imgViews = new ImageView[]{foodImg, execiseImg, friendsImg, happyImg};

        //初始化按钮底部的四个文字
        foodText = findViewById(R.id.tab_food_text);
        execiseText = findViewById(R.id.tab_execise_text);
        friendsText = findViewById(R.id.tab_friends_text);
        happyText = findViewById(R.id.tab_happy_text);

        //初始化文本数组
        textViews = new TextView[]{foodText, execiseText, friendsText, happyText};

        //初始化ToolBar标题
        maintoolText = findViewById(R.id.maintool_text);

        //给四个按钮 、 文本绑定监听器
        foodImg.setOnClickListener(this);
        execiseImg.setOnClickListener(this);
        friendsImg.setOnClickListener(this);
        happyImg.setOnClickListener(this);

        foodText.setOnClickListener(this);
        execiseText.setOnClickListener(this);
        friendsText.setOnClickListener(this);
        happyText.setOnClickListener(this);

        //先重置所有按钮和文本颜色
        resetButtonColor();

        //再将第一个设为默认选中
        foodImg.setImageResource(R.drawable.food_seleced_icon);
        foodText.setTextColor(SELECTED_COLOR);

        views = new ArrayList<View>();



        Intent intent1 = new Intent(MainFunction.this, FoodFragment.class);
        View view1 = manager.startActivity("viewID", intent1).getDecorView();
        Intent intent2 = new Intent(MainFunction.this, ExeciseFragment.class);
        View view2 = manager.startActivity("viewID", intent2).getDecorView();
        Intent intent3 = new Intent(MainFunction.this, FriendsFragment.class);
        View view3 = manager.startActivity("viewID", intent3).getDecorView();
        Intent intent4 = new Intent(MainFunction.this, HappyFragment.class);
        View view4 = manager.startActivity("viewID", intent4).getDecorView();

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        MyFragmentAdapter adapter = new MyFragmentAdapter( views);

        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);


    }

    //重置按钮和文本颜色
    private void resetButtonColor(){
        foodImg.setImageResource(R.drawable.food_icon);
        execiseImg.setImageResource(R.drawable.execise_icon);
        friendsImg.setImageResource(R.drawable.friends_icon);
        happyImg.setImageResource(R.drawable.happy_icon);

        foodText.setTextColor(UNSELECT_COLOR);
        execiseText.setTextColor(UNSELECT_COLOR);
        friendsText.setTextColor(UNSELECT_COLOR);
        happyText.setTextColor(UNSELECT_COLOR);
    }


    @Override
    public void onClick(View v) {
        resetButtonColor();
        switch (v.getId()){
            case R.id.tab_food_img:
            case R.id.tab_food_text:
                viewPager.setCurrentItem(0);
                imgViews[0].setImageResource(R.drawable.food_seleced_icon);
                textViews[0].setTextColor(SELECTED_COLOR);
                maintoolText.setText("饮食");
                break;
            case R.id.tab_execise_img:
            case R.id.tab_execise_text:
                viewPager.setCurrentItem(1);
                imgViews[1].setImageResource(R.drawable.execise_selected_icon);
                textViews[1].setTextColor(SELECTED_COLOR);
                maintoolText.setText("运动");
                break;
            case R.id.tab_friends_img:
            case R.id.tab_friends_text:
                viewPager.setCurrentItem(2);
                imgViews[2].setImageResource(R.drawable.friends_selected_icon);
                textViews[2].setTextColor(SELECTED_COLOR);
                maintoolText.setText("社交");
                break;
            case R.id.tab_happy_img:
            case R.id.tab_happy_text:
                viewPager.setCurrentItem(3);
                imgViews[3].setImageResource(R.drawable.happy_selected_icon);
                textViews[3].setTextColor(SELECTED_COLOR);
                maintoolText.setText("娱乐");
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        //每次滑动首先重置所有按钮文本颜色
        resetButtonColor();

        //将滑动到的按钮文本设置为指定颜色
        switch (position){
            case 0:
                imgViews[0].setImageResource(R.drawable.food_seleced_icon);
                textViews[0].setTextColor(SELECTED_COLOR);
                maintoolText.setText("饮食");
                break;
            case 1:
                imgViews[1].setImageResource(R.drawable.execise_selected_icon);
                textViews[1].setTextColor(SELECTED_COLOR);
                maintoolText.setText("运动");
                break;
            case 2:
                imgViews[2].setImageResource(R.drawable.friends_selected_icon);
                textViews[2].setTextColor(SELECTED_COLOR);
                maintoolText.setText("社交");
                break;
            case 3:
                imgViews[3].setImageResource(R.drawable.happy_selected_icon);
                textViews[3].setTextColor(SELECTED_COLOR);
                maintoolText.setText("娱乐");
                break;
            default:
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
