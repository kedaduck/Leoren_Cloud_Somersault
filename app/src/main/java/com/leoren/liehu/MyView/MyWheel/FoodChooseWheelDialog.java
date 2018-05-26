package com.leoren.liehu.MyView.MyWheel;

import android.app.Dialog;
import android.content.Context;

/**
 * 食物选择滑轮对话框
 * @Author Leoren
 * @Date 2018/5/26 16:12
 */
public class FoodChooseWheelDialog extends Dialog {

    /**
     * 食物选择滑轮
     */
    private WheelView foodWheelView;

    /**
     *
     * @param context
     * @param themeResId
     */
    public FoodChooseWheelDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}
