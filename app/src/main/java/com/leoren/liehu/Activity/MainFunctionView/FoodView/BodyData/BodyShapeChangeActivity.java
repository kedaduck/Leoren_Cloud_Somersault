package com.leoren.liehu.Activity.MainFunctionView.FoodView.BodyData;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leoren.liehu.Function.Food.MyBody.BodyShapeShow;
import com.leoren.liehu.R;
import com.leoren.liehu.Util.Adapter.FoodAdapter.BodyShapeAdapter;
import com.leoren.liehu.Util.MyDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 形体展示页面
 */
public class BodyShapeChangeActivity extends AppCompatActivity {

    private List<BodyShapeShow> showList = new ArrayList<>();
    private BodyShapeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_shape_change);

        initBodyShapeShowList();
        RecyclerView recyclerView = findViewById(R.id.body_shape_list);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        adapter = new BodyShapeAdapter(showList);
        recyclerView.setAdapter(adapter);


    }

    private void initBodyShapeShowList() {
        showList.clear();
        for(int i = 0 ; i < 50 ; i++){
            BodyShapeShow bodyShapeShow = new BodyShapeShow();
            bodyShapeShow.setPhotoTime(MyDate.getChattime(new Date()));
            bodyShapeShow.setImgUrl("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg");
            showList.add(bodyShapeShow);
        }
    }
}
