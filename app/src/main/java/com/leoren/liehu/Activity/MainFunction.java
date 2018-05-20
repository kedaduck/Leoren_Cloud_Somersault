package com.leoren.liehu.Activity;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.MyView.CircleImageView;
import com.leoren.liehu.R;
import com.leoren.liehu.util.Adapter.PageAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

public class MainFunction extends AppCompatActivity implements View.OnClickListener{

    //左拉个人主页布局
    private DrawerLayout drawerLayout;

    //ToolBar上的头像
    private CircleImageView headIcon;

    //左拉个人主页
    private NavigationView personal_view;

    //定义tab左拉右滑的一些工具和容器
    private ViewPager viewPager;
    private List<View> views = new ArrayList<>();
    private PageAdapter adapter = new PageAdapter(views);

    /**
    //四个主功能 tab
    private FragmentActivity foodActivity;
    private FragmentActivity execiseActivity;
    private FragmentActivity friendsActivity;
    private FragmentActivity happyActivity;
*/
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main_function);

        initView();
        initEvents();

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

    private void initEvents(){
        foodImg.setOnClickListener(this);
        execiseImg.setOnClickListener(this);
        friendsImg.setOnClickListener(this);
        happyImg.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将图片文字默认都选不中
                resetImg();
                int currentItem = viewPager.getCurrentItem();
                switch (currentItem){
                    case 0:
                        foodImg.setImageResource(R.drawable.food_seleced_icon);
                        foodText.setTextColor(Color.rgb(26, 250, 29));
                        break;
                    case 1:
                        execiseImg.setImageResource(R.drawable.execise_selected_icon);
                        execiseText.setTextColor(Color.rgb(26, 250, 29));
                        break;
                    case 2:
                        friendsImg.setImageResource(R.drawable.friends_selected_icon);
                        friendsText.setTextColor(Color.rgb(26, 250, 29));
                        break;
                    case 3:
                        happyImg.setImageResource(R.drawable.happy_selected_icon);
                        happyText.setTextColor(Color.rgb(26, 250, 29));
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //初始化四个页面
    private void initView(){

        viewPager = findViewById(R.id.maintab_view);

        //四个功能tab


        //主功能区底部的四个按钮
        foodImg = findViewById(R.id.tab_food_img);
        execiseImg = findViewById(R.id.tab_execise_img);
        friendsImg = findViewById(R.id.tab_friends_img);
        happyImg = findViewById(R.id.tab_happy_img);

        //主功能区底部的四个按钮下的文字说明
        foodText = findViewById(R.id.tab_food_text);
        execiseText = findViewById(R.id.tab_execise_text);
        friendsText = findViewById(R.id.tab_friends_text);
        happyText = findViewById(R.id.tab_happy_text);

        foodImg.setImageResource(R.drawable.food_seleced_icon);
        foodText.setTextColor(Color.rgb(26, 250, 29));


        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.activity_food, null);
        View view2 = inflater.inflate(R.layout.activity_execise, null);
        View view3 = inflater.inflate(R.layout.activity_friends, null);
        View view4 = inflater.inflate(R.layout.activity_happy, null);

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        viewPager.setAdapter(adapter);
    }



    private void resetImg(){
        foodImg.setImageResource(R.drawable.food_icon);
        execiseImg.setImageResource(R.drawable.execise_icon);
        friendsImg.setImageResource(R.drawable.friends_icon);
        happyImg.setImageResource(R.drawable.happy_icon);
        foodText.setTextColor(Color.rgb(44, 44, 44));
        execiseText.setTextColor(Color.rgb(44, 44, 44));
        friendsText.setTextColor(Color.rgb(44, 44, 44));
        happyText.setTextColor(Color.rgb(44, 44, 44));
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()){
            case R.id.tab_food_img:
                viewPager.setCurrentItem(0);
                foodImg.setImageResource(R.drawable.food_seleced_icon);
                foodText.setTextColor(Color.rgb(26, 250, 29));
                break;
            case R.id.tab_execise_img:
                viewPager.setCurrentItem(1);
                execiseImg.setImageResource(R.drawable.execise_selected_icon);
                execiseText.setTextColor(Color.rgb(26, 250, 29));
                break;
            case R.id.tab_friends_img:
                viewPager.setCurrentItem(2);
                friendsImg.setImageResource(R.drawable.friends_selected_icon);
                friendsText.setTextColor(Color.rgb(26, 250, 29));
                break;
            case R.id.tab_happy_img:
                viewPager.setCurrentItem(3);
                happyImg.setImageResource(R.drawable.happy_selected_icon);
                happyText.setTextColor(Color.rgb(26, 250, 29));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        switch (viewPager.getCurrentItem()){
            case 0:
                getMenuInflater().inflate(R.menu.foodview_menu, menu);
                return true;
            case 1:
                getMenuInflater().inflate(R.menu.execiseview_menu, menu);
                return true;
            case 2:
                getMenuInflater().inflate(R.menu.friendsview_menu, menu);
                return true;
            case 3:
                getMenuInflater().inflate(R.menu.happyview_menu, menu);
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.food_scanCode:
            case R.id.execise_scanCode:
            case R.id.friends_scanCode:
            case R.id.happy_scanCode:
                Toast.makeText(this, "扫一扫", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }
}
