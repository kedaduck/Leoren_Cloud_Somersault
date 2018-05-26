package com.leoren.liehu.MyView.MyWheel.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author Leoren
 * @Date 2018/5/26 16:26
 */
public class AbstractWheelAdapter implements WheelViewAdapter{

    private List<DataSetObserver> dataSetObservers;

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        if(dataSetObservers == null){
            dataSetObservers = new LinkedList<DataSetObserver>();
        }
        dataSetObservers.add(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if(dataSetObservers != null){
            dataSetObservers.remove(observer);
        }
    }

    /**
     * 告诉发生了数据更改
     */
    protected void notifyDataChangeEvent(){
        if(dataSetObservers != null){
            for(DataSetObserver observer :  dataSetObservers){
                observer.onChanged();
            }
        }
    }

    /**
     * 数据出错
     */
    protected void notifyDataInvalidatedEvent(){
        if(dataSetObservers != null){
            for(DataSetObserver observer : dataSetObservers){
                observer.onInvalidated();
            }
        }
    }


}
