package com.leoren.liehu.MyView.MyWheel.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.print.PrinterId;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @Author Leoren
 * @Date 2018/5/26 16:38
 */
public abstract class AbstractWheelTextAdapter extends AbstractWheelAdapter {

    public static final int TEXT_VIEW_ITEM_RESOURCE = -1;

    protected static final int NO_RESOURCE = 0;

    public static final int DEFAULT_TEXT_COLOR = 0xFF101010;

    public static final int LABEL_COLOR = 0xFF700070;

    public static final int DEFAULT_TEXT_SIZE = 24;

    private  int textColor = DEFAULT_TEXT_COLOR;
    private int textSize = DEFAULT_TEXT_SIZE;

    protected Context context;
    protected LayoutInflater inflater;

    protected int itemResourceId;
    protected int itemTextResourceId;

    protected int emptyItemResourceId;

    private int currentIndex = 0;
    private static int maxSize = 24;
    private static int minSize = 14;
    private ArrayList<View> arrayList = new ArrayList<View>();


    protected AbstractWheelTextAdapter(Context context){
        this(context, TEXT_VIEW_ITEM_RESOURCE);
    }

    public AbstractWheelTextAdapter(Context context, int itemResource) {
        this(context, itemResource, NO_RESOURCE, 0, maxSize, minSize);
    }

    public AbstractWheelTextAdapter(Context context, int itemResource, int itemTextResource, int currentIndex, int maxSize, int minSize) {
        this.context = context;
        itemResourceId = itemResource;
        itemTextResourceId = itemTextResourceId;
        this.currentIndex = currentIndex;
        this.maxSize = maxSize;
        this.minSize = minSize;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public int getItemResourceId() {
        return itemResourceId;
    }

    public void setItemResourceId(int itemResourceId) {
        this.itemResourceId = itemResourceId;
    }

    public int getItemTextResourceId() {
        return itemTextResourceId;
    }

    public void setItemTextResourceId(int itemTextResourceId) {
        this.itemTextResourceId = itemTextResourceId;
    }

    public int getEmptyItemResourceId() {
        return emptyItemResourceId;
    }

    public void setEmptyItemResourceId(int emptyItemResourceId) {
        this.emptyItemResourceId = emptyItemResourceId;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public static int getMaxSize() {
        return maxSize;
    }

    public static void setMaxSize(int maxSize) {
        AbstractWheelTextAdapter.maxSize = maxSize;
    }

    public static int getMinSize() {
        return minSize;
    }

    public static void setMinSize(int minSize) {
        AbstractWheelTextAdapter.minSize = minSize;
    }

    public ArrayList<View> getTestViews() {
        return arrayList;
    }

    public void setArrayList(ArrayList<View> arrayList) {
        this.arrayList = arrayList;
    }

    protected abstract CharSequence getItemText(int index);

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        if(index >= 0 && index < getItemCount()){
            if(convertView == null){
                convertView = getView(itemResourceId,parent);
            }
            TextView textView = getTextView(convertView, itemTextResourceId);
            if(!arrayList.contains(textView)){
                arrayList.add(textView);
            }
            if(textView != null){
                CharSequence text = getItemText(index);
                if(text == null){
                    text = "";
                }
                textView.setText(text);
                if(index == currentIndex){
                    textView.setTextSize(maxSize);
                }else{
                    textView.setTextSize(minSize);
                }

                if(itemResourceId == TEXT_VIEW_ITEM_RESOURCE){
                    configureTextView(textView);
                }
            }
            return convertView;
        }
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = getView(emptyItemResourceId, parent);
        }
        if(emptyItemResourceId == TEXT_VIEW_ITEM_RESOURCE && convertView instanceof TextView ){
            configureTextView((TextView) convertView);
        }
        return convertView;
    }

    protected void configureTextView(TextView view){
        view.setTextColor(textColor);
        view.setGravity(Gravity.CENTER);
        view.setTextSize(textSize);
        view.setLines(1);
        view.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
    }

    private TextView getTextView(View view, int textResource){
        TextView text = null;
        try{
            if(textResource == NO_RESOURCE && view instanceof TextView){
                text = (TextView) view;
            }else if(textResource != NO_RESOURCE){
                text = view.findViewById(textResource);
            }
        }catch (ClassCastException e){
            throw new IllegalStateException("AbstractWheelAdapter requires the resource ID to be a TextView" ,e);
        }
        return text;
    }

    private View getView(int resource, ViewGroup parent){
        switch (resource){
            case NO_RESOURCE:
                return null;
            case TEXT_VIEW_ITEM_RESOURCE:
                return new TextView(context);
            default:
                return inflater.inflate(resource, parent, false);
        }
    }
}
