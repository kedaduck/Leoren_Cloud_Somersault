package com.leoren.liehu.Util.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.R;
import com.leoren.liehu.Function.Friends.FriendChatroom;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @Author Leoren
 * @Date 2018/5/26 14:50
 */
public class ChatroomAdapter extends RecyclerView.Adapter<ChatroomAdapter.ViewHolder>{

    private Context context;

    private List<FriendChatroom> roomList;



    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout chatroomList;
        CircleImageView headImg;
        TextView titleView;
        TextView subtitleView;
        TextView timeView;

        public ViewHolder(View itemView) {
            super(itemView);
            chatroomList = itemView.findViewById(R.id.chatroomList);
            headImg = itemView.findViewById(R.id.chatroomList_headImg);
            titleView = itemView.findViewById(R.id.chatroomList_title);
            subtitleView = itemView.findViewById(R.id.chatroomList_content);
            timeView = itemView.findViewById(R.id.chatroomList_time);
        }
    }

    public ChatroomAdapter(List<FriendChatroom> list){
        roomList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FriendChatroom chatroom = roomList.get(position);
        holder.headImg.setImageResource(chatroom.getHeadId());
        holder.titleView.setText(chatroom.getFriendName());
        holder.subtitleView.setText(chatroom.getLastChatContent());
        holder.timeView.setText(chatroom.getLastChatTime());
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }




}
