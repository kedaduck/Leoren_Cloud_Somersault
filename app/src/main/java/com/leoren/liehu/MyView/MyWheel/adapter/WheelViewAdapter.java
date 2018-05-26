package com.leoren.liehu.MyView.MyWheel.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * 自定义滑轮的各种接口
 * @Author Leoren
 * @Date 2018/5/26 16:27
 */
public interface WheelViewAdapter {

    /**
     * 得到滑轮内容数目
     */
    int getItemCount();

    /**
     * 获取每一项
     */
    View getItem(int index, View convertView, ViewGroup parent);

    /**
     * 如果是空则显示...
     */
    View getEmptyItem(View convertView, ViewGroup parent);

    /**
     *
     */
    void registerDataSetObserver(DataSetObserver observer);

    /**
     *
     */
    void unregisterDataSetObserver(DataSetObserver observer);
}
