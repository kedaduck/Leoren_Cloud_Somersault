package com.leoren.liehu.MyView.SerachView;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * 自定义搜索框
 * @Author Leoren
 * @Date 2018/5/24 11:21
 */
public class SearchActivity extends Activity {

    //搜索的结果列表
    private ListView lvResults;

    //搜索的view
    private SearchView searchView;

    //热搜框列表adapter
    private ArrayAdapter<String> hintAdapter;

    //自动补全列表
    private ArrayAdapter<String> autoCompleteAdapter;

    //搜索结果列表adapter
    private SearchAdapter resultAdapter;

    private List<SearchBean> dbData;

    //热搜数据
    private List<String> hintData;

    //默认提示框显示项个数
    private static int DEFAULT_HINT_SIZE = 4;

    //提示框显示项格式
    private static int hintSize = DEFAULT_HINT_SIZE;

    public static void setHintSize(int hintSize){
        SearchActivity.hintSize = hintSize;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setCon
    }
}
