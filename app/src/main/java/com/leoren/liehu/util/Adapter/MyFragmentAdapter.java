package com.leoren.liehu.util.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/5/21 9:56
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
