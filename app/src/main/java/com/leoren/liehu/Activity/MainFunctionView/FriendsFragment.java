package com.leoren.liehu.Activity.MainFunctionView;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsFragment extends FragmentActivity implements View.OnClickListener{

    private CircleImageView  fri_head;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_fragment);

        fri_head = findViewById(R.id.fri_head);
        fri_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fri_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friendsview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.friends_scanCode:
                Toast.makeText(this, "扫一扫", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
}
