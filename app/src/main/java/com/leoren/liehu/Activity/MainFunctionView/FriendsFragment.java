package com.leoren.liehu.Activity.MainFunctionView;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.leoren.liehu.Activity.MainFunction;
import com.leoren.liehu.R;
import com.leoren.liehu.Function.Friends.FriendChatroom;
import com.leoren.liehu.Util.Adapter.ChatroomAdapter;
import com.leoren.liehu.Util.MyDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsFragment extends FragmentActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{
    private static final String TAG = "FriendsFragment";

    private CircleImageView  fri_head;

    private List<FriendChatroom> roomList = new ArrayList<>();
    private ChatroomAdapter adapter;

    private ImageButton menu_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_fragment);

        fri_head = findViewById(R.id.fri_head);
        fri_head.setOnClickListener(this);
        initChatroomList();
        RecyclerView recyclerView = findViewById(R.id.friends_list);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);
        adapter = new ChatroomAdapter(roomList);
        Log.i(TAG, "onCreate: ==================================");
        recyclerView.setAdapter(adapter);

        menu_icon = findViewById(R.id.friend_menu);
        menu_icon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fri_head:
                MainFunction.drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.friend_menu:
                PopupMenu popupMenu = new PopupMenu(this, v);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.friendsview_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(this);
                popupMenu.show();
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

    private void initChatroomList(){
        roomList.clear();
        for(int i = 0 ; i < 500 ; i++){
            FriendChatroom friendChatroom = new FriendChatroom();
            friendChatroom.setFriendName("Leorenhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            friendChatroom.setLastChatContent("hahahah=============================================================================");
            friendChatroom.setHeadId(R.drawable.head_icon);
            friendChatroom.setLastChatTime(MyDate.getChattime(new Date()));
            roomList.add(friendChatroom);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }
}
