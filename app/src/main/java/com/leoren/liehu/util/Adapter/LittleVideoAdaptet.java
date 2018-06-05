package com.leoren.liehu.util.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoren.liehu.R;
import com.leoren.liehu.User.Appuser;
import com.leoren.liehu.User.BaseUserInfo.UserHappy.LittleVideo;
import com.leoren.liehu.User.BaseUserInfo.UserHappy.VideoComment;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * happy模块对第一个功能视频的适配器
 * @Author Leoren
 * @Date 2018/5/29 14:34
 */
public class LittleVideoAdaptet {

    private Context context;

    private List<LittleVideo> list;

    static class ViewHolder extends RecyclerView.ViewHolder{


        TextView subtitleView;
        TextView timeView;

        public ViewHolder(View itemView) {
            super(itemView);
            subtitleView = itemView.findViewById(R.id.chatroomList_content);
            timeView = itemView.findViewById(R.id.chatroomList_time);
        }
    }


}
