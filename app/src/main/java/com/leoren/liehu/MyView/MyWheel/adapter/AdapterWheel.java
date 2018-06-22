package com.leoren.liehu.MyView.MyWheel.adapter;

import android.content.Context;

import com.leoren.liehu.MyView.MyWheel.OtherView.WheelAdapter;

/**
 * @Author Leoren
 * @Date 2018/5/26 20:10
 */
public class AdapterWheel extends AbstractWheelTextAdapter {


    // Source adapter
    private WheelAdapter adapter;

    /**
     * Constructor
     * @param context the current context
     * @param adapter the source adapter
     */
    public AdapterWheel(Context context, WheelAdapter adapter) {
        super(context);

        this.adapter = adapter;
    }

    /**
     * Gets original adapter
     * @return the original adapter
     */
    public WheelAdapter getAdapter() {
        return adapter;
    }

    @Override
    public int getItemsCount() {
        return adapter.getItemsCount();
    }

    @Override
    protected CharSequence getItemText(int index) {
        return adapter.getItem(index);
    }
}
