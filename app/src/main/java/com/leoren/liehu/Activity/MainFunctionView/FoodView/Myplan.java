package com.leoren.liehu.Activity.MainFunctionView.FoodView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunctionView.FoodView.PlanActivity.AddMuscleActivity1;
import com.leoren.liehu.Activity.MainFunctionView.FoodView.PlanActivity.DeclineFatActivity1;
import com.leoren.liehu.R;

import static android.animation.ObjectAnimator.ofFloat;

public class Myplan extends AppCompatActivity implements View.OnClickListener{

    private Button add_body_btn;
    private Button decline_fat_btn;
    private Button add_muscle_btn;
    private Button real_decline_fat;
    private Button real_add_muscle;

    //按钮是否展开
    private boolean isFlate = false;

    AnimationSet fatanimationSet;
    AnimationSet muscleanimationSet;
    RotateAnimation mainrotate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_myplan);

        initViews();

    }

    private void initViews(){
        add_body_btn = findViewById(R.id.add_body_plan);
        decline_fat_btn = findViewById(R.id.decline_fat_btn);
        add_muscle_btn = findViewById(R.id.add_muscle_btn);
        real_add_muscle = findViewById(R.id.real_add_muscle);
        real_decline_fat = findViewById(R.id.real_decline_fat);

        add_body_btn.setOnClickListener(this);
        decline_fat_btn.setOnClickListener(this);
        add_muscle_btn.setOnClickListener(this);
        real_add_muscle.setOnClickListener(this);
        real_decline_fat.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_body_plan:
                if(isFlate == false){
                    //打开动画
                    real_decline_fat.setEnabled(true);
                    real_add_muscle.setEnabled(true);
                    isFlate = true;
                    startAnimation();
                }else {
                    isFlate = false;
                    closeAnimation();
                    real_decline_fat.setEnabled(false);
                    real_add_muscle.setEnabled(false);
                    real_add_muscle.setVisibility(View.VISIBLE);
                    real_decline_fat.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.add_muscle_btn:
            case R.id.real_add_muscle:
                //增肌
                real_add_muscle.setVisibility(View.INVISIBLE);
                //Toast.makeText(this, "zengjijiijiji", Toast.LENGTH_SHORT).show();
                addMuscleActivity();
                break;
            case R.id.decline_fat_btn:
            case R.id.real_decline_fat:
                real_decline_fat.setVisibility(View.INVISIBLE);
                //Toast.makeText(this, "减脂", Toast.LENGTH_SHORT).show();
                declineFatActivity();
                break;
            default:
                break;
        }
    }

    //进入增肌页面
    private void addMuscleActivity(){
        Intent intent = new Intent(this, AddMuscleActivity1.class);
        startActivity(intent);
    }

    //进入减脂页面
    private void declineFatActivity() {
        Intent intent = new Intent(this, DeclineFatActivity1.class);
        startActivity(intent);
    }


    private void startAnimation(){
        fatanimationSet = new AnimationSet(true);
        fatanimationSet.setDuration(1500);
        TranslateAnimation fatTranslate = new TranslateAnimation(0,190,0,190);
        RotateAnimation fatRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        ScaleAnimation fatScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        fatanimationSet.addAnimation(fatTranslate);
        fatanimationSet.addAnimation(fatRotate);
        fatanimationSet.addAnimation(fatScale);
        fatanimationSet.setFillAfter(true);

        muscleanimationSet = new AnimationSet(true);
        muscleanimationSet.setDuration(1500);
        TranslateAnimation muscleTranslate = new TranslateAnimation(0,-190,0,-190);
        RotateAnimation muscleRotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        ScaleAnimation muscleScale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        muscleanimationSet.addAnimation(muscleRotate);
        muscleanimationSet.addAnimation(muscleScale);
        muscleanimationSet.addAnimation(muscleTranslate);
        muscleanimationSet.setFillAfter(true);

        mainrotate = new RotateAnimation(0,90, AlphaAnimation.RELATIVE_TO_SELF, 0.5f, AlphaAnimation.RELATIVE_TO_SELF, 0.5f);
        mainrotate.setDuration(1500);

        add_body_btn.startAnimation(mainrotate);
        decline_fat_btn.startAnimation(fatanimationSet);
        add_muscle_btn.startAnimation(muscleanimationSet);



    }

    private void closeAnimation() {

        fatanimationSet = new AnimationSet(true);
        fatanimationSet.setDuration(1500);
        TranslateAnimation fatTranslate = new TranslateAnimation(0,-190,0,-190);
        RotateAnimation fatRotate = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        ScaleAnimation fatScale = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        fatanimationSet.addAnimation(fatTranslate);
        fatanimationSet.addAnimation(fatRotate);
        fatanimationSet.addAnimation(fatScale);
        fatanimationSet.setFillAfter(true);

        muscleanimationSet = new AnimationSet(true);
        muscleanimationSet.setDuration(1500);
        TranslateAnimation muscleTranslate = new TranslateAnimation(0,190,0,190);
        RotateAnimation muscleRotate = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);
        ScaleAnimation muscleScale = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_SELF);

        muscleanimationSet.addAnimation(muscleRotate);
        muscleanimationSet.addAnimation(muscleScale);
        muscleanimationSet.addAnimation(muscleTranslate);
        muscleanimationSet.setFillAfter(true);

        mainrotate = new RotateAnimation(90,0, AlphaAnimation.RELATIVE_TO_SELF, 0.5f, AlphaAnimation.RELATIVE_TO_SELF, 0.5f);
        mainrotate.setDuration(1500);

        add_body_btn.startAnimation(mainrotate);
        decline_fat_btn.startAnimation(fatanimationSet);
        add_muscle_btn.startAnimation(muscleanimationSet);

    }
}
