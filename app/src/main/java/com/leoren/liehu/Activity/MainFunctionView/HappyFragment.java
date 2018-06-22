package com.leoren.liehu.Activity.MainFunctionView;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.Activity.MainFunctionView.HappyView.LittleEasy;
import com.leoren.liehu.Activity.MainFunctionView.HappyView.LittleGame;
import com.leoren.liehu.Activity.MainFunctionView.HappyView.LittleVideo;
import com.leoren.liehu.Activity.MainFunctionView.HappyView.MyAttention;
import com.leoren.liehu.R;
import com.leoren.liehu.Util.Adapter.MyFragmentAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HappyFragment extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private CircleImageView happy_head;

    //存放五个功能模块的容器工具
    private ArrayList<View> views;
    private ViewPager viewPager;
    private LocalActivityManager manager;

    //顶部四个按钮
    private TextView littleVideo;
    private TextView littleEasy;
    private TextView myAttention;
    private TextView littleGame;

    //存放四个按钮的数组
    private TextView[] buttons;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_fragment);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        happy_head = findViewById(R.id.happy_head);
        happy_head.setOnClickListener(this);

        initView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.happy_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.exercise_video:
                viewPager.setCurrentItem(0);
                break;
            case R.id.easy_aLittle:
                viewPager.setCurrentItem(1);
                break;
            case R.id.happy_attention:
                viewPager.setCurrentItem(2);
                break;
            case R.id.little_game:
                viewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }

    private void initView(){
        viewPager = findViewById(R.id.happy_viewpager);
        viewPager.setOnPageChangeListener(this);

        //初始化四个按钮
        littleVideo = findViewById(R.id.exercise_video);
        littleEasy = findViewById(R.id.easy_aLittle);
        myAttention = findViewById(R.id.happy_attention);
        littleGame = findViewById(R.id.little_game);

        buttons = new TextView[]{littleVideo,littleEasy,myAttention,littleGame};

        //为四个按钮绑定监听事件
        littleVideo.setOnClickListener(this);
        littleEasy.setOnClickListener(this);
        myAttention.setOnClickListener(this);
        littleGame.setOnClickListener(this);

        views = new ArrayList<View>();

        Intent intent1 = new Intent(HappyFragment.this, LittleVideo.class);
        View view1 = manager.startActivity("viewID", intent1).getDecorView();
        Intent intent2 = new Intent(HappyFragment.this, LittleEasy.class);
        View view2 = manager.startActivity("viewID", intent2).getDecorView();
        Intent intent3 = new Intent(HappyFragment.this, MyAttention.class);
        View view3 = manager.startActivity("viewID", intent3).getDecorView();
        Intent intent4 = new Intent(HappyFragment.this, LittleGame.class);
        View view4 = manager.startActivity("viewID", intent4).getDecorView();

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        MyFragmentAdapter adapter = new MyFragmentAdapter(views);
        viewPager.setAdapter(adapter);

        resetButtonColor();

        //默认第一个为选中
        littleVideo.setTextSize(17);
        littleVideo.setTextColor(getResources().getColor(R.color.white));
        littleVideo.setBackground(getResources().getDrawable(R.drawable.happy_item_shape));

    }

    //初始化四个按钮颜色
    private void resetButtonColor(){
        for(int i = 0 ; i < buttons.length; i++){
            buttons[i].setBackground(getResources().getDrawable(R.color.white));
            buttons[i].setTextColor(getResources().getColor(R.color.dark));
            buttons[i].setTextSize(15);
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //每次滑动时重置所有按钮颜色
        resetButtonColor();

        //将滑动到的当前按钮颜色变为红色
        buttons[position].setTextColor(getResources().getColor(R.color.white));
        buttons[position].setTextSize(17);
        buttons[position].setBackground(getResources().getDrawable(R.drawable.happy_item_shape));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
