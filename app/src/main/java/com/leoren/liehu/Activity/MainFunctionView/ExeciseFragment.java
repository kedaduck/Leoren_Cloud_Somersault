package com.leoren.liehu.Activity.MainFunctionView;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.Activity.MainFunctionView.ExeciseView.Bike;
import com.leoren.liehu.Activity.MainFunctionView.ExeciseView.Exercise;
import com.leoren.liehu.Activity.MainFunctionView.ExeciseView.Run;
import com.leoren.liehu.Activity.MainFunctionView.ExeciseView.Walk;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Mybody;
import com.leoren.liehu.Content.MainViewColor;
import com.leoren.liehu.R;
import com.leoren.liehu.util.Adapter.MyFragmentAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExeciseFragment extends FragmentActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private static final String TAG = "ExeciseFragment";

    private CircleImageView exe_head;

    //存放五个功能模块的容器工具
    private ArrayList<View> views;
    private ViewPager viewPager;
    private LocalActivityManager manager;

    //界面顶部的四个按钮
    private TextView run_btn;
    private TextView walk_btn;
    private TextView bike_btn;
    private TextView exercise_btn;

    //四个按钮的集合
    private TextView[] buttons;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execise_fragment);
        
        exe_head = findViewById(R.id.exe_head);
        exe_head.setOnClickListener(this);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);


        initView();

    }

    private void initView(){
        //顶部四个功能按钮初始化
        run_btn = findViewById(R.id.exe_run_btn);
        walk_btn = findViewById(R.id.exe_walk_btn);
        bike_btn = findViewById(R.id.exe_bike_btn);
        exercise_btn = findViewById(R.id.exe_exercise_btn);

        buttons = new TextView[]{run_btn, walk_btn, bike_btn, exercise_btn};

        //绑定点击监听
        run_btn.setOnClickListener(this);
        walk_btn.setOnClickListener(this);
        bike_btn.setOnClickListener(this);
        exercise_btn.setOnClickListener(this);

        viewPager = findViewById(R.id.execisetab_view);
        viewPager.setOnPageChangeListener(this);

        views = new ArrayList<View>();

        Intent intent1 = new Intent(ExeciseFragment.this, Run.class);
        View view1 = manager.startActivity("viewID", intent1).getDecorView();
        Intent intent2 = new Intent(ExeciseFragment.this, Walk.class);
        View view2 = manager.startActivity("viewID", intent2).getDecorView();
        Intent intent3 = new Intent(ExeciseFragment.this, Bike.class);
        View view3 = manager.startActivity("viewID", intent3).getDecorView();
        Intent intent4 = new Intent(ExeciseFragment.this, Exercise.class);
        View view4 = manager.startActivity("viewID", intent4).getDecorView();

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);

        MyFragmentAdapter adapter = new MyFragmentAdapter(views);

        viewPager.setAdapter(adapter);

        run_btn.setTextSize(16);
        run_btn.setTextColor(getResources().getColor(R.color.white));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exe_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.exe_run_btn:
                viewPager.setCurrentItem(0);
                break;
            case R.id.exe_walk_btn:
                viewPager.setCurrentItem(1);
                break;
            case R.id.exe_bike_btn:
                viewPager.setCurrentItem(2);
                break;
            case R.id.exe_exercise_btn:
                viewPager.setCurrentItem(3);
            default:
                break;
        }
    }


    private void resetButtonColor(){

        for(int i = 0 ; i < buttons.length ; i++){
            buttons[i].setTextColor(getResources().getColor(R.color.dark));
            buttons[i].setTextSize(14);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //每次滑动时重置所有按钮颜色
        resetButtonColor();

        //将滑动到的当前按钮颜色变为白色
        buttons[position].setTextColor(getResources().getColor(R.color.white));
        buttons[position].setTextSize(16);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
