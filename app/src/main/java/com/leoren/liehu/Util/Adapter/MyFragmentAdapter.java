package com.leoren.liehu.Util.Adapter;


import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/5/21 9:56
 */
public class MyFragmentAdapter extends PagerAdapter {

    private List<View> list;

    public MyFragmentAdapter(ArrayList<View> list){
        this.list = list;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(list.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = list.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
