package com.leoren.liehu.Activity.MainFunctionView.FoodView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.BodyShapeChangeActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.BustDataActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.FatrateDataActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.HipDataActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.MuscleDataActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.WaistDataActivity;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData.WeightDataActivity;
import com.leoren.liehu.MyView.MyWheel.HorizontalWheelView.HeightSelectView;
import com.leoren.liehu.MyView.MyWheel.OtherView.OnWheelChangedListener;
import com.leoren.liehu.MyView.MyWheel.OtherView.OnWheelScrollListener;
import com.leoren.liehu.MyView.MyWheel.OtherView.WheelView;
import com.leoren.liehu.MyView.MyWheel.adapter.NumericWheelAdapter;
import com.leoren.liehu.R;

import java.util.ArrayList;
import java.util.List;

public class Mybody extends AppCompatActivity implements View.OnClickListener{

    //身体变化模块的控件
    private LinearLayout bodyShapeLayout;
    private ImageView bodyShapeImg;
    private TextView bodyChangeText;

    //身高模块的控件
    private LinearLayout height_btn;
    private TextView heightText;


    //体重模块的控件
    private LinearLayout weightLayout;
    private TextView weightText;
    private ImageView weightChangeImg;
    private TextView weightDescript;

    //腰围模块的控件
    private LinearLayout waistlineLayout;
    private TextView waistlineText;
    private ImageView waistlineChangeImg;
    private TextView waistlineDescript;

    //臀围模块的控件
    private LinearLayout hiplineLayout;
    private TextView hiplineText;
    private ImageView hiplineChangeImg;
    private TextView hiplineDescript;

    //胸围围模块的控件
    private LinearLayout bustlineLayout;
    private TextView bustlineText;
    private ImageView bustlineChangeImg;
    private TextView bustlineDescript;


    //体脂率模块的控件
    private LinearLayout fatrateLayout;
    private TextView fatrateText;
    private ImageView fatrateChangeImg;
    private TextView fatrateDescript;

    //肌肉量模块的控件
    private LinearLayout muscleLayout;
    private TextView muscleText;
    private ImageView muscleChangeImg;
    private TextView muscleDescript;

    //各项数据
    private String heightData;
    private String weightData;
    private String bustData;
    private String waistData;
    private String hipData;
    private String fatrateData;
    private String muscleData;

    //画图所需各项数据
    private List<Float> weightList = new ArrayList<>();
    private List<Integer> bustList = new ArrayList<>();
    private List<Integer> waistList = new ArrayList<>();
    private List<Integer> hipList = new ArrayList<>();
    private List<Float> fatrateList = new ArrayList<>();
    private List<Float> muscleList = new ArrayList<>();

    //身高所需的popwindow
    private PopupWindow popupWindow;
    //身高所需的滚轮
    private WheelView heightWheel;

    private LinearLayout layout_main;
    //在滚轮中显示当前所选的身高
    private TextView heightwheel_text;
    //当前所选的位置
    private int curHeight;











    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_mybody);
        layout_main = findViewById(R.id.body_layout_main);


        initViews();
    }

    /**
     * 初始化各种控件
     */
    private void initViews() {
        bodyShapeLayout = findViewById(R.id.bodyShape_layout);
        bodyShapeImg = findViewById(R.id.record_body_shape);
        bodyChangeText = findViewById(R.id.record_body_shape_text);

        height_btn = findViewById(R.id.height_btn);
        heightText = findViewById(R.id.height_isrecord);

        weightLayout = findViewById(R.id.weight_layout);
        weightText = findViewById(R.id.weight_isrecord);
        weightChangeImg = findViewById(R.id.weight_change_icon);
        weightDescript = findViewById(R.id.record_body_weight_text);

        bustlineLayout = findViewById(R.id.bust_layout);
        bustlineText = findViewById(R.id.bust_isrecord);
        bustlineChangeImg = findViewById(R.id.bust_change_icon);
        bustlineDescript = findViewById(R.id.record_bust_text);

        waistlineLayout = findViewById(R.id.waist_layout);
        waistlineText = findViewById(R.id.waistline_isrecord);
        waistlineChangeImg = findViewById(R.id.waistline_change_icon);
        waistlineDescript = findViewById(R.id.record_body_waistline_text);

        hiplineLayout = findViewById(R.id.hip_layout);
        hiplineText = findViewById(R.id.hipline_isrecord);
        hiplineChangeImg = findViewById(R.id.hipline_change_icon);
        hiplineDescript = findViewById(R.id.record_hipline__text);

        fatrateLayout = findViewById(R.id.fatrate_layout);
        fatrateText = findViewById(R.id.fat_rate_isrecord);
        fatrateChangeImg = findViewById(R.id.fat_rate_change_icon);
        fatrateDescript = findViewById(R.id.record_fat_rate_text);

        muscleLayout = findViewById(R.id.muscle_layout);
        muscleText = findViewById(R.id.muscle_isrecord);
        muscleChangeImg = findViewById(R.id.muscle_change_icon);
        muscleDescript = findViewById(R.id.record_muscle_text);

        //绑定监听事件
        bodyShapeLayout.setOnClickListener(this);
        height_btn.setOnClickListener(this);
        weightLayout.setOnClickListener(this);
        bustlineLayout.setOnClickListener(this);
        waistlineLayout.setOnClickListener(this);
        hiplineLayout.setOnClickListener(this);
        fatrateLayout.setOnClickListener(this);
        muscleLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bodyShape_layout:
                bodyShapeEvent();
                //Toast.makeText(this, "体型", Toast.LENGTH_SHORT).show();
                break;
            case R.id.height_btn:
                //Toast.makeText(this, "身高", Toast.LENGTH_SHORT).show();
                heightEvent();
                makeWindowDark();
                break;
            case R.id.weight_layout:
                weightEvent();
                //Toast.makeText(this, "体重", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bust_layout:
                bustEvent();
                //Toast.makeText(this, "胸围", Toast.LENGTH_SHORT).show();
                break;
            case R.id.waist_layout:
                waistEvent();
                //Toast.makeText(this, "腰围", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hip_layout:
                hipEvent();
                //Toast.makeText(this, "臀围", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fatrate_layout:
                fatrateEvent();
                //Toast.makeText(this, "体脂", Toast.LENGTH_SHORT).show();
                break;
            case R.id.muscle_layout:
                muscleEvent();
                //Toast.makeText(this, "肌肉", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void bodyShapeEvent(){
        Intent intent = new Intent(this, BodyShapeChangeActivity.class);
        startActivity(intent);
    }

    private void heightEvent(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popuWindowView = inflater.inflate(R.layout.height_pop_select, null);
        heightwheel_text = popuWindowView.findViewById(R.id.height_wheel_text);
        popupWindow = new PopupWindow(popuWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        initWheelView(popuWindowView);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                makeWindowLight();
            }
        });
        popupWindow.showAtLocation(layout_main, Gravity.CENTER | Gravity.BOTTOM, 0 , 0);

    }

    /**
     * 身高选择的滑动窗口监听器
     */
    OnWheelScrollListener heightScrollListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            int nowHeight = heightWheel.getCurrentItem();
            heightwheel_text.setText(String.valueOf(nowHeight+100));
        }
    };

    /**
     * 初始化身高选择弹窗
     * @param popuWindowView
     */
    private void initWheelView(View view) {
        heightWheel = view.findViewById(R.id.height_select_wheel);
        NumericWheelAdapter heightWheelAdapter = new NumericWheelAdapter(this, 100, 250);
        Button sureHeight_btn = view.findViewById(R.id.height_select_ok);
        heightWheelAdapter.setLabel(" ");
        heightWheel.setViewAdapter(heightWheelAdapter);
        heightWheelAdapter.setTextColor(R.color.dark);
        heightWheelAdapter.setTextSize(20);
        heightWheel.setCyclic(false);
        heightWheel.addScrollingListener(heightScrollListener);
        heightWheel.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                curHeight = newValue;
            }
        });
        sureHeight_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                heightText.setText(heightwheel_text.getText() + "CM");
            }
        });

        heightWheel.setCurrentItem(curHeight);
    }

    private void weightEvent(){
        Intent intent = new Intent(this, WeightDataActivity.class);
        startActivity(intent);
    }

    private void bustEvent(){
        Intent intent = new Intent(this, BustDataActivity.class);
        startActivity(intent);
    }

    private void hipEvent(){
        Intent intent = new Intent(this, HipDataActivity.class);
        startActivity(intent);
    }

    private void waistEvent(){
        Intent intent = new Intent(this, WaistDataActivity.class);
        startActivity(intent);
    }

    private void fatrateEvent(){
        Intent intent = new Intent(this, FatrateDataActivity.class);
        startActivity(intent);
    }

    private void muscleEvent(){
        Intent intent = new Intent(this, MuscleDataActivity.class);
        startActivity(intent);
    }

    /**
     * 让屏幕变暗
     */
    private void makeWindowDark(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.5f;
        window.setAttributes(lp);
    }

    /**
     * 让屏幕变亮
     */
    private void makeWindowLight(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1f;
        window.setAttributes(lp);
    }

}
