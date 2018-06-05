package com.leoren.liehu.Activity.MainFunctionView.HappyView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.leoren.liehu.Activity.MainFunctionView.HappyView.NormalVideo.PlayNormalVideo;
import com.leoren.liehu.R;

public class LittleVideo extends AppCompatActivity{

    private LinearLayout videoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_video);

        videoLayout = findViewById(R.id.item_littlevideo);
        videoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlayNormalVideo.class);
                startActivity(intent);
            }
        });

    }


}
