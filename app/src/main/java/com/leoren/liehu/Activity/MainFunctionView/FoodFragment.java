package com.leoren.liehu.Activity.MainFunctionView;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Breakfast;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Dinner;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Lunch;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Mybody;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.Myplan;
import com.leoren.liehu.R;
import com.leoren.liehu.Util.Adapter.MyFragmentAdapter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * food模块的Activity
 *
 */
public class FoodFragment extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    private CircleImageView food_head;


    //存放五个功能模块的容器工具
    private ArrayList<View> views;
    private ViewPager viewPager;
    private LocalActivityManager manager;

    //界面顶部的五个按钮
    private TextView myBody_btn;
    private TextView myPlan_btn;
    private TextView breakfast_btn;
    private TextView lunch_btn;
    private TextView dinner_btn;

    //五个按钮底部的横线    用来做标记
    private ImageView cursor;

    //标志指示标签的横坐标
    float cursorX = 0;

    //所有顶部按钮的数组
    private TextView[] buttons;

    //所有按钮宽度的数组
    private int[] btn_widths;

    //两种颜色   选中和未选中
    private static int COLOR_UNSELET = Color.parseColor("#2c2c2c");
    private static int COLOR_SELETED = Color.parseColor("#f22300");



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_fragment);

        manager = new LocalActivityManager(this, true);
        manager.dispatchCreate(savedInstanceState);

        food_head = findViewById(R.id.food_head);
        food_head.setOnClickListener(this);



        initView();
    }

    /**
     * 初始化布局
     */
    private void initView(){

        viewPager = findViewById(R.id.foodtab_view);

        //初始化顶部的五个按钮
        myBody_btn = findViewById(R.id.food_myBody_btn);
        myPlan_btn = findViewById(R.id.food_myPlan_btn);
        breakfast_btn = findViewById(R.id.food_breakfast_btn);
        lunch_btn = findViewById(R.id.food_lunch_btn);
        dinner_btn = findViewById(R.id.food_dinner_btn);

        //初始化按钮数组
        buttons = new TextView[]{myBody_btn, myPlan_btn, breakfast_btn, lunch_btn, dinner_btn};

        //初始化按钮底部的标签
        cursor = findViewById(R.id.cursor_btn);

        //设置标签为红色
        cursor.setBackgroundColor(COLOR_SELETED);

        viewPager.setOnPageChangeListener(this);
        //为顶部的五个按钮设置点击监听
        myBody_btn.setOnClickListener(this);
        myPlan_btn.setOnClickListener(this);
        breakfast_btn.setOnClickListener(this);
        lunch_btn.setOnClickListener(this);
        dinner_btn.setOnClickListener(this);


        views = new ArrayList<View>();

        Intent intent1 = new Intent(FoodFragment.this, Mybody.class);
        View view1 = manager.startActivity("viewID", intent1).getDecorView();
        Intent intent2 = new Intent(FoodFragment.this, Myplan.class);
        View view2 = manager.startActivity("viewID", intent2).getDecorView();
        Intent intent3 = new Intent(FoodFragment.this, Breakfast.class);
        View view3 = manager.startActivity("viewID", intent3).getDecorView();
        Intent intent4 = new Intent(FoodFragment.this, Lunch.class);
        View view4 = manager.startActivity("viewID", intent4).getDecorView();
        Intent intent5 = new Intent(FoodFragment.this, Dinner.class);
        View view5 = manager.startActivity("viewID", intent5).getDecorView();

        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);

        MyFragmentAdapter adapter = new MyFragmentAdapter(views);

        viewPager.setAdapter(adapter);

        //先重置所有颜色为未选中
        resetButtonColor();

        //默认第一个按钮字体为选中
        myBody_btn.setTextColor(COLOR_SELETED);
        myBody_btn.setTextSize(16);

    }


    private void resetButtonColor(){
        myBody_btn.setTextColor(COLOR_UNSELET);
        myPlan_btn.setTextColor(COLOR_UNSELET);
        breakfast_btn.setTextColor(COLOR_UNSELET);
        lunch_btn.setTextColor(COLOR_UNSELET);
        dinner_btn.setTextColor(COLOR_UNSELET);

        myBody_btn.setTextSize(14);
        myPlan_btn.setTextSize(14);
        breakfast_btn.setTextSize(14);
        lunch_btn.setTextSize(14);
        dinner_btn.setTextSize(14);
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
            case R.id.food_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
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
        if(btn_widths == null){
            btn_widths = new int[]{myBody_btn.getWidth(), myPlan_btn.getWidth(), breakfast_btn.getWidth(), lunch_btn.getWidth(), dinner_btn.getWidth()};

        }
        //每次滑动时重置所有按钮颜色
        resetButtonColor();

        //将滑动到的当前按钮颜色变为红色
        buttons[position].setTextColor(COLOR_SELETED);
        buttons[position].setTextSize(16);
        cursorAnim(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 改变标签的位置
     */
    private void cursorAnim(int curItem){
        //每次调用将横坐标设为0
        cursorX = 0;
        //再根据当前的curItem来设置指示器的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        lp.width = btn_widths[curItem] - buttons[0].getPaddingLeft() * 2;
        cursor.setLayoutParams(lp);
        for(int i = 0 ; i < curItem; i++ ){
            cursorX = cursorX + buttons[i].getWidth();
        }

        cursor.setX(cursorX + buttons[curItem].getPaddingLeft());
    }
}
