package com.leoren.liehu.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.Activity.FoodView.BreakfastActivity;
import com.leoren.liehu.Activity.FoodView.DinnerActicity;
import com.leoren.liehu.Activity.FoodView.LunchActivity;
import com.leoren.liehu.Activity.FoodView.MybodyState;
import com.leoren.liehu.Activity.FoodView.MyplanActivity;
import com.leoren.liehu.R;
import com.leoren.liehu.util.Adapter.FoodviewAdapter;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private static final String TAG = "FoodActivity";

    private ViewPager viewPager;

    //顶部代替按钮的TextView
    private TextView myBody_btn;
    private TextView myPlan_btn;
    private TextView breakfast_btn;
    private TextView lunch_btn;
    private TextView dinner_btn;

    //作为指示标签的按钮
    private ImageView cursor;

    //标志指示标签的横坐标
    private int cursorX = 0;

    //所有按钮的宽度
    private int[] widthArgs;

    //所有标题按钮的数组
    private TextView[] textViews;

    //fragment的集合，对应每个子页面
    private ArrayList<Fragment> fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Log.i(TAG, "onCreate: swwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");

        initView();


    }

    private void initView(){

        Log.i(TAG, "initView: dsadsdsededwefdwe");

        viewPager = findViewById(R.id.food_viewpager);

        myBody_btn = findViewById(R.id.food_myBody_btn);
        myPlan_btn = findViewById(R.id.food_myPlan_btn);
        breakfast_btn = findViewById(R.id.food_breakfast_btn);
        lunch_btn = findViewById(R.id.food_lunch_btn);
        dinner_btn = findViewById(R.id.food_dinner_btn);

        textViews = new TextView[]{myBody_btn, myPlan_btn, breakfast_btn, lunch_btn, dinner_btn};

        //设置标签背景颜色
        cursor = findViewById(R.id.cursor_btn);
        cursor.setBackgroundColor(Color.parseColor("#2c2c2c"));

        myBody_btn.setOnClickListener(this);
        myPlan_btn.setOnClickListener(this);
        breakfast_btn.setOnClickListener(this);
        lunch_btn.setOnClickListener(this);
        dinner_btn.setOnClickListener(this);

        viewPager.setOnPageChangeListener(this);

        //先重置所有颜色
        resetButtonColor();

        //默认第一个字体为红色
        myBody_btn.setTextColor(Color.parseColor("#f22300"));

        fragments = new ArrayList<>();
        fragments.add(new MybodyState());
        fragments.add(new MyplanActivity());
        fragments.add(new BreakfastActivity());
        fragments.add(new LunchActivity());
        fragments.add(new DinnerActicity());

        FoodviewAdapter adapter = new FoodviewAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(adapter);




    }

    /**
     * 重置所有按钮颜色
     */
    private void resetButtonColor(){
        myBody_btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
        myPlan_btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
        breakfast_btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
        lunch_btn.setBackgroundColor(Color.parseColor("#DCDCDC"));
        dinner_btn.setBackgroundColor(Color.parseColor("#DCDCDC"));

        myBody_btn.setTextColor(Color.parseColor("#2c2c2c"));
        myPlan_btn.setTextColor(Color.parseColor("#2c2c2c"));
        breakfast_btn.setTextColor(Color.parseColor("#2c2c2c"));
        lunch_btn.setTextColor(Color.parseColor("#2c2c2c"));
        dinner_btn.setTextColor(Color.parseColor("#2c2c2c"));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.food_myBody_btn:
                viewPager.setCurrentItem(0);
                cursorAnim(0);
                break;
            case R.id.food_myPlan_btn:
                viewPager.setCurrentItem(1);
                cursorAnim(1);
                break;
            case R.id.food_breakfast_btn:
                viewPager.setCurrentItem(2);
                cursorAnim(2);
                break;
            case R.id.food_lunch_btn:
                viewPager.setCurrentItem(3);
                cursorAnim(3);
                break;
            case R.id.food_dinner_btn:
                viewPager.setCurrentItem(4);
                cursorAnim(4);
                break;
            default:
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        if(widthArgs == null){
            widthArgs = new int[]{myBody_btn.getWidth()
                        ,myPlan_btn.getWidth()
                        ,breakfast_btn.getWidth()
                        ,lunch_btn.getWidth()
                        ,dinner_btn.getWidth()};
        }
        //每次滑动先重置所有颜色
        resetButtonColor();
        textViews[position].setTextColor(Color.parseColor("#f22300"));
        cursorAnim(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void cursorAnim(int curItem){
        //每次调用都设为0初始值
        cursorX = 0;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();

        lp.width = widthArgs[curItem] - textViews[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        for(int i = 0; i < curItem; i++){
            cursorX = cursorX + textViews[i].getWidth();
        }

        cursor.setX(cursorX + textViews[curItem].getPaddingLeft());

    }
}
