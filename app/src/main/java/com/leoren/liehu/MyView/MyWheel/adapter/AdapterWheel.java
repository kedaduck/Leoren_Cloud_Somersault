package com.leoren.liehu.MyView.MyWheel.adapter;

import android.content.Context;

/**
 * @Author Leoren
 * @Date 2018/5/26 20:10
 */
public class AdapterWheel extends AbstractWheelTextAdapter {

    //private Wheel

    protected AdapterWheel(Context context) {
        super(context);
    }

    public AdapterWheel(Context context, int itemResource) {
        super(context, itemResource);
    }

    public AdapterWheel(Context context, int itemResource, int itemTextResource, int currentIndex, int maxSize, int minSize) {
        super(context, itemResource, itemTextResource, currentIndex, maxSize, minSize);
    }

    @Override
    protected CharSequence getItemText(int index) {
        return null;
    }
}
