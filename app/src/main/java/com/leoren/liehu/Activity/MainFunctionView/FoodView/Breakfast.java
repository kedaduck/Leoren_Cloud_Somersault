package com.leoren.liehu.Activity.MainFunctionView.FoodView;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leoren.liehu.R;
import com.leoren.liehu.User.BaseUserInfo.UserFood.ChooseFoodItem;
import com.leoren.liehu.User.BaseUserInfo.UserFood.Food;
import com.leoren.liehu.util.Adapter.ChooseFoodKindAdapter;

import java.util.ArrayList;
import java.util.List;

public class Breakfast extends AppCompatActivity {

    private ChooseFoodItem[] foodItems = Food.chooseFoodItems;

    private List<ChooseFoodItem> chooseFoodItemList = new ArrayList<>();

    private ChooseFoodKindAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_breakfast);

        initFoodKinds();
        RecyclerView recyclerView = findViewById(R.id.breakfast_choose_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new ChooseFoodKindAdapter(chooseFoodItemList);
        recyclerView.setAdapter(adapter);
    }

    private void initFoodKinds(){
        chooseFoodItemList.clear();
        for(int i = 0 ; i < foodItems.length ; i++){
            chooseFoodItemList.add(foodItems[i]);
        }
    }
}
