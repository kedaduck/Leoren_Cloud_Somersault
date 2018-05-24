package com.leoren.liehu.MyView.SerachView;

import android.content.Context;

import com.leoren.liehu.R;

import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/5/24 13:53
 */
public class SearchAdapter extends CommonAdapter<SearchBean>{


    public SearchAdapter(Context context, List<SearchBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, int position) {
        holder.setText(R.id.item_search_title, mData.get(position).getTitle())
                .setText(R.id.item_search_context, mData.get(position).getContent())
                .setText(R.id.item_search_tv_comments,mData.get(position).getComments());
    }
}
