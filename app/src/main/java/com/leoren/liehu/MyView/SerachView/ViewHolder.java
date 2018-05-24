package com.leoren.liehu.MyView.SerachView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Author Leoren
 * @Date 2018/5/24 13:29
 */
public class ViewHolder {

    private SparseArray<View> views;
    private Context context;
    private View view;
    private int position;

    public ViewHolder(Context context, int layoutId, ViewGroup parent, int position){
        view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        views = new SparseArray<>();
        position = position;
        view.setTag(this);
    }

    public static ViewHolder getHolder(Context context, View converView, int layoutId, ViewGroup parent, int position){
        if(converView == null){
            return new ViewHolder(context, layoutId, parent, position);
        }else{
            ViewHolder holder = (ViewHolder) converView.getTag();
            holder.position = position;
            return holder;
        }
    }

    public View getConverView(){
        return view;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = view.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text){
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap){
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

}
