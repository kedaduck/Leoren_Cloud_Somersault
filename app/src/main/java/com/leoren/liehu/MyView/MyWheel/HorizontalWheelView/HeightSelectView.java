package com.leoren.liehu.MyView.MyWheel.HorizontalWheelView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.leoren.liehu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/6/19 17:37
 * 身高选择的滚轮
 */
public class HeightSelectView extends View{

    private Context context;
    //存放身高数据的List
    private List<String> strings = new ArrayList<>();

    //设置可见个数
    private int seeSize = 7;
    private int anInt;//每个字母所占的位置
    private TextPaint textPaint;
    private boolean firstVisable = true;
    private int width;
    private int height;
    private Paint selectedPaint;
    private int n;
    private float downX;
    private float anOffset;
    private float selectedTextSize;
    private int selectedColor;
    private float textSize;
    private int textColor;
    private Rect rect = new Rect();

    private int textWidth = 0;
    private int textHeight = 0;
    private int centerTextHeight = 0;

    public HeightSelectView(Context context) {
        this(context, null);
    }

    public HeightSelectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeightSelectView(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        this.context = context;
        setWillNotDraw(false);
        setClickable(true);

        initAttrs(attrs);
        initPaint();
    }

    /**
     * 初始化属性
     */
    @SuppressLint("RestrictedApi")
    private void initAttrs(AttributeSet attrs){
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.HeightSelectView);
        seeSize = tta.getInteger(R.styleable.HeightSelectView_HeightSelectedViewSeeSize, 7);
        selectedTextSize = tta.getFloat(R.styleable.HeightSelectView_HeightSelectedViewTextSize, 50);
        selectedColor = tta.getColor(R.styleable.HeightSelectView_HeightSelectedViewTextColor, context.getResources().getColor(android.R.color.black));
        textSize = tta.getFloat(R.styleable.HeightSelectView_HeightUnSelectedViewTextSize, 40);
        textColor = tta.getColor(R.styleable.HeightSelectView_HeightUnSelectedViewTextColor, context.getResources().getColor(android.R.color.darker_gray));
    }

    /**
     * 初始化画笔
     */
    private void initPaint(){
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        selectedPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        selectedPaint.setColor(selectedColor);
        selectedPaint.setTextSize(selectedTextSize);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float scrollX = event.getX();

                if(n != 0 && n != strings.size() -1){
                    anOffset = scrollX - downX;
                }else {
                    anOffset = (float) ((scrollX -downX) / 1.5);
                }
                if(scrollX > downX){
                    //向右滑动
                    if(scrollX - downX >= anInt){
                        if(n > 0){
                            anOffset = 0;
                            n = n-1;
                            downX = scrollX;
                        }
                    }
                }else {
                    //向右滑动
                    if (downX - scrollX >= anInt) {

                        if (n < strings.size() - 1) {
                            anOffset = 0;
                            n = n + 1;
                            downX = scrollX;
                        }
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                anOffset = 0;
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(firstVisable){
            width = getWidth();
            height = getHeight();
            anInt = width / seeSize;
            firstVisable = false;
        }
        if(n >= 0 && n <= strings.size() -1){
            String s = strings.get(n);
            /**
             * 得到被选中文字 绘制时所需要的宽高
             */
            selectedPaint.getTextBounds(s, 0, s.length(), rect);
            //3从矩形区域中读出文本内容的宽高
            int centerTextWidth = rect.width();
            centerTextHeight = rect.height();
            canvas.drawText(strings.get(n), getWidth() / 2 - centerTextWidth / 2 + anOffset, getHeight() / 2 + centerTextHeight / 2, selectedPaint);//绘制被选中文字，注意点是y坐标

            for (int i = 0; i < strings.size(); i++) {//遍历strings，把每个地方都绘制出来，
                if (n > 0 && n < strings.size() - 1) {//这里主要是因为strings数据源的文字长度不一样，为了让被选中两边文字距离中心宽度一样，我们取得左右两个文字长度的平均值
                    textPaint.getTextBounds(strings.get(n - 1), 0, strings.get(n - 1).length(), rect);
                    int width1 = rect.width();
                    textPaint.getTextBounds(strings.get(n + 1), 0, strings.get(n + 1).length(), rect);
                    int width2 = rect.width();
                    textWidth = (width1 + width2) / 2;
                }
                if (i == 0) {//得到高，高度是一样的，所以无所谓
                    textPaint.getTextBounds(strings.get(0), 0, strings.get(0).length(), rect);
                    textHeight = rect.height();
                }

                if (i != n) {
                    canvas.drawText(strings.get(i), (i - n) * anInt + getWidth() / 2 - textWidth / 2 + anOffset, getHeight() / 2 + textHeight / 2, textPaint);//画出每组文字
                }
            }
        }

    }

    /**
     * 改变中间可见文字的数目
     *
     * @param seeSizes 可见数
     */
    public void setSeeSize(int seeSizes){
        if (seeSize > 0) {
            seeSize = seeSizes;
            invalidate();
        }
    }

    /**
     * 向左移动一个单元
     */
    public void setAnLeftOffset() {
        if (n < strings.size() - 1) {
            n = n + 1;
            invalidate();
        }

    }

    /**
     * 向右移动一个单元
     */
    public void setAnRightOffset() {
        if (n > 0) {
            n = n - 1;
            invalidate();
        }
    }

    /**
     * 设置个数据源
     *
     * @param strings 数据源String集合
     */
    public void setData(List<String> strings) {
        this.strings = strings;
        n = strings.size() / 2;
        invalidate();
    }

    /**
     * 获得被选中的文本
     *
     * @return 被选中的文本
     */
    public String getSelectedString() {
        if (strings.size() != 0) {
            return strings.get(n);
        }
        return null;
    }


}
