package com.leoren.liehu.Activity.MainFunctionView.FoodView;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leoren.liehu.Helper.FoodHelper;
import com.leoren.liehu.R;

import java.util.ArrayList;
import java.util.List;

public class Breakfast extends AppCompatActivity {


    //食物列表  名称和能量对应
    private List<List<FoodHelper.FoodEnergy>> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_breakfast);


    }







}
